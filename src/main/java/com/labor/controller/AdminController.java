package com.labor.controller;

import com.labor.model.Complaint;
import com.labor.model.JobPost;
import com.labor.model.WorkerAvailability; 
import com.labor.model.Town;
import com.labor.model.User;
import com.labor.repository.ComplaintRepository;
import com.labor.repository.JobPostRepository;
import com.labor.repository.WorkerAvailabilityRepository; 
import com.labor.repository.TownRepository;
import com.labor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private WorkerAvailabilityRepository workerAvailabilityRepository; 
    
    @Autowired
    private TownRepository townRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        if (userDetails == null) return "redirect:/login";
        User currentUser = userRepository.findByMobile(userDetails.getUsername());
        if (currentUser == null || !"Admin".equals(currentUser.getRole())) return "redirect:/";

        List<User> allUsers = userRepository.findAll();
        List<Complaint> allComplaints = complaintRepository.findAllByOrderByCreatedAtDesc();
        List<JobPost> allJobs = jobPostRepository.findAll();
        List<WorkerAvailability> allAvailabilities = workerAvailabilityRepository.findAll();
        
        // Fetch towns and sort them alphabetically for the dashboard display
        List<Town> allTowns = townRepository.findAll();
        allTowns.sort((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()));
        
        // --- STATS CALCULATIONS ---
        long farmerCount = allUsers.stream().filter(u -> "Farmer".equals(u.getRole())).count();
        long workerCount = allUsers.stream().filter(u -> "Worker".equals(u.getRole())).count();
        long totalJobs = allJobs.size();
        long totalAvailabilities = allAvailabilities.size();
        long pendingComplaints = allComplaints.stream().filter(c -> "Pending".equals(c.getStatus())).count();

        // Sorting
        allJobs.sort((j1, j2) -> {
            if (j1.getCreatedAt() == null && j2.getCreatedAt() == null) return 0;
            if (j1.getCreatedAt() == null) return 1;
            if (j2.getCreatedAt() == null) return -1;
            return j2.getCreatedAt().compareTo(j1.getCreatedAt());
        });

        allAvailabilities.sort((a1, a2) -> {
            if (a1.getCreatedAt() == null && a2.getCreatedAt() == null) return 0;
            if (a1.getCreatedAt() == null) return 1;
            if (a2.getCreatedAt() == null) return -1;
            return a2.getCreatedAt().compareTo(a1.getCreatedAt());
        });
        
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("users", allUsers);
        model.addAttribute("complaints", allComplaints);
        model.addAttribute("jobs", allJobs); 
        model.addAttribute("availabilities", allAvailabilities);
        model.addAttribute("towns", allTowns);
        
        // --- ADD STATS TO MODEL ---
        model.addAttribute("farmerCount", farmerCount);
        model.addAttribute("workerCount", workerCount);
        model.addAttribute("jobCount", totalJobs);
        model.addAttribute("availCount", totalAvailabilities);
        model.addAttribute("pendingComplaints", pendingComplaints);
        
        return "admin_dashboard";
    }

    @PostMapping("/toggle-ban")
    public String toggleBan(@RequestParam Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Boolean currentStatus = user.getIsBanned();
        user.setIsBanned(currentStatus != null ? !currentStatus : true);
        userRepository.save(user);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/resolve-complaint")
    public String resolveComplaint(@RequestParam Integer complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow();
        complaint.setStatus("Resolved");
        complaintRepository.save(complaint);
        return "redirect:/admin/dashboard";
    }
    
    // UPDATED: Handles State, District, and Village with Duplicate Checking
    @PostMapping("/add-town")
    public String addTown(@RequestParam String state, 
                          @RequestParam String district, 
                          @RequestParam String townName, 
                          @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        
        User currentUser = userRepository.findByMobile(userDetails.getUsername());
        
        if (currentUser != null && "Admin".equals(currentUser.getRole()) 
            && townName != null && !townName.trim().isEmpty()
            && state != null && !state.trim().isEmpty()
            && district != null && !district.trim().isEmpty()) {
            
            String cleanState = state.trim();
            String cleanDistrict = district.trim();
            String cleanTown = townName.trim();

            // Check if this exact village already exists
            boolean exists = townRepository.existsByNameIgnoreCaseAndDistrictIgnoreCaseAndStateIgnoreCase(cleanTown, cleanDistrict, cleanState);
            
            if (exists) {
                // Redirect back with an error flag to trigger the popup
                return "redirect:/admin/dashboard?error=exists";
            }

            // Save the new location
            Town newTown = new Town(cleanTown, cleanDistrict, cleanState);
            townRepository.save(newTown);
            
            return "redirect:/admin/dashboard?success=added";
        }
        
        return "redirect:/admin/dashboard";
    }
}