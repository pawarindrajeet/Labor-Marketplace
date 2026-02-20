package com.labor.controller;

import com.labor.model.Complaint;
import com.labor.model.User;
import com.labor.repository.ComplaintRepository;
import com.labor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    
    // NEW: Inject the Complaint database
    @Autowired
    private ComplaintRepository complaintRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        if (userDetails == null) return "redirect:/login";
        User currentUser = userRepository.findByMobile(userDetails.getUsername());
        if (currentUser == null || !"Admin".equals(currentUser.getRole())) return "redirect:/";

        // Fetch Users and Complaints
        List<User> allUsers = userRepository.findAll();
        List<Complaint> allComplaints = complaintRepository.findAllByOrderByCreatedAtDesc();
        
        model.addAttribute("users", allUsers);
        model.addAttribute("complaints", allComplaints);
        
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
    
    // NEW: Handles resolving complaints
    @PostMapping("/resolve-complaint")
    public String resolveComplaint(@RequestParam Integer complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow();
        complaint.setStatus("Resolved");
        complaintRepository.save(complaint);
        return "redirect:/admin/dashboard";
    }
}