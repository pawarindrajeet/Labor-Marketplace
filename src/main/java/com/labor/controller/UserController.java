package com.labor.controller;

import com.labor.model.WorkerAvailability;
import com.labor.model.JobPost;
import com.labor.model.User;
import com.labor.model.Town;
import com.labor.repository.WorkerAvailabilityRepository;
import com.labor.repository.ComplaintRepository;
import com.labor.repository.JobPostRepository;
import com.labor.repository.UserRepository;
import com.labor.repository.TownRepository;
import com.labor.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {

    private static final String TOWNS_ATTRIBUTE = "towns";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    private static final String ROLE_FARMER = "Farmer";
    private static final String ROLE_WORKER = "Worker";
    private static final String ROLE_ADMIN = "Admin"; // NEW: Admin Role

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WorkerAvailabilityRepository workerAvailabilityRepository;
    @Autowired
    private JobPostRepository jobPostRepository;
    @Autowired
    private ComplaintRepository complaintRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        List<Town> towns = townRepository.findAll();
        model.addAttribute(TOWNS_ATTRIBUTE, towns);
        return "register";
    }

    @PostMapping("/register")
    @Transactional
    public String registerUser(@RequestParam String name,
                               @RequestParam String mobile,
                               @RequestParam String password,
                               @RequestParam String role,
                               @RequestParam String gender,
                               @RequestParam Integer townId) {
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setGender(gender);
        user.setIsBanned(false); // Make sure new users are not banned
        Town town = townRepository.findById(townId).orElseThrow();
        user.setTown(town);
        userRepository.save(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null) return REDIRECT_LOGIN;

        // NEW: Check if the user is banned
        if (Boolean.TRUE.equals(user.getIsBanned())) {
            return "redirect:/login?banned=true"; 
        }

        // NEW: Route the Admin to the Admin Controller
        if (ROLE_ADMIN.equals(user.getRole())) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("user", user);
        
        if (ROLE_FARMER.equals(user.getRole())) {
            List<JobPost> jobs = jobPostRepository.findByIsFullFalseOrderByCreatedAtDesc().stream()
                .filter(job -> job.getTown() != null).collect(Collectors.toList());
            List<WorkerAvailability> availabilities = workerAvailabilityRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(availability -> availability.getWorker() != null && availability.getWorker().getTown() != null)
                .collect(Collectors.toList());
            List<Town> towns = townRepository.findAll();
            model.addAttribute("jobs", jobs);
            model.addAttribute("availabilities", availabilities);
            model.addAttribute(TOWNS_ATTRIBUTE, towns);
            return "farmer_dashboard";
        } else {
            List<JobPost> jobs = jobPostRepository.findByIsFullFalseOrderByCreatedAtDesc();
            List<WorkerAvailability> availabilities = workerAvailabilityRepository.findAllByOrderByCreatedAtDesc();
            List<Town> towns = townRepository.findAll();
            List<com.labor.model.Response> myResponses = responseRepository.findByWorkerId(user.getId());
            Set<Integer> respondedJobIds = myResponses.stream()
                .map(response -> response.getJob().getId()).collect(Collectors.toSet());
            model.addAttribute("jobs", jobs);
            model.addAttribute("availabilities", availabilities);
            model.addAttribute(TOWNS_ATTRIBUTE, towns);
            model.addAttribute("myResponses", myResponses);
            model.addAttribute("respondedJobIds", respondedJobIds);
            return "worker_feed";
        }
    }

    @PostMapping("/post-availability")
    @Transactional
    public String postAvailability(@RequestParam String skills, @RequestParam String availability, 
                                   @RequestParam Double wage, @RequestParam String startDate, @RequestParam String endDate,
                                   @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User worker = userRepository.findByMobile(userDetails.getUsername());
        if (worker == null || Boolean.TRUE.equals(worker.getIsBanned())) return REDIRECT_LOGIN; // Prevent banned users from posting

        WorkerAvailability wa = new WorkerAvailability();
        wa.setWorker(worker);
        wa.setSkills(skills);
        wa.setAvailability(availability);
        wa.setWage(wage);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (!startDate.isEmpty()) wa.setStartDate(LocalDateTime.parse(startDate, formatter));
        if (!endDate.isEmpty()) wa.setEndDate(LocalDateTime.parse(endDate, formatter));
        workerAvailabilityRepository.save(wa);
        return REDIRECT_DASHBOARD;
    }

    @GetMapping("/post-availability")
    public String showPostAvailabilityForm(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null || !ROLE_WORKER.equals(user.getRole())) return REDIRECT_DASHBOARD;
        return "post_availability";
    }

    @GetMapping("/post-job")
    public String showPostJobForm(Model model) {
        List<Town> towns = townRepository.findAll();
        model.addAttribute(TOWNS_ATTRIBUTE, towns);
        return "post_job";
    }

    @PostMapping("/post-job")
    @Transactional
    public String postJob(@RequestParam String workType,
                          @RequestParam String description,
                          @RequestParam Double wage,
                          @RequestParam Integer workersNeeded,
                          @RequestParam String skillsRequired,
                          @RequestParam String preferredGender,
                          @RequestParam String startDate,
                          @RequestParam String endDate,
                          @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User farmer = userRepository.findByMobile(userDetails.getUsername());
        if (farmer == null || !ROLE_FARMER.equals(farmer.getRole()) || Boolean.TRUE.equals(farmer.getIsBanned())) return REDIRECT_DASHBOARD; // Prevent banned

        JobPost job = new JobPost();
        job.setFarmer(farmer);
        job.setTown(farmer.getTown());
        job.setWorkType(workType);
        job.setDescription(description);
        job.setWage(wage);
        job.setWorkersNeeded(workersNeeded);
        job.setSkillsRequired(skillsRequired);
        job.setPreferredGender(preferredGender.isEmpty() ? null : preferredGender);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (!startDate.isEmpty()) job.setStartDate(LocalDateTime.parse(startDate, formatter));
        if (!endDate.isEmpty()) job.setEndDate(LocalDateTime.parse(endDate, formatter));
        jobPostRepository.save(job);
        return REDIRECT_DASHBOARD;
    }

    @PostMapping("/mark-job-full")
    public String markJobFull(@RequestParam Integer id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        User farmer = userRepository.findByMobile(userDetails.getUsername());
        if (farmer == null || !ROLE_FARMER.equals(farmer.getRole())) return REDIRECT_DASHBOARD;
        JobPost job = jobPostRepository.findById(id).orElse(null);
        if (job != null && job.getFarmer().equals(farmer)) {
            job.setIsFull(true);
            jobPostRepository.save(job);
        }
        return REDIRECT_DASHBOARD;
    }

    @PostMapping("/delete-job")
    public String deleteJob(@RequestParam Integer id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        User farmer = userRepository.findByMobile(userDetails.getUsername());
        if (farmer == null || !ROLE_FARMER.equals(farmer.getRole())) return REDIRECT_DASHBOARD;
        JobPost job = jobPostRepository.findById(id).orElse(null);
        if (job != null && job.getFarmer().equals(farmer)) {
            jobPostRepository.delete(job);
        }
        return REDIRECT_DASHBOARD;
    }

    @PostMapping("/delete-availability")
    public String deleteAvailability(@RequestParam Integer id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        User worker = userRepository.findByMobile(userDetails.getUsername());
        if (worker == null || !ROLE_WORKER.equals(worker.getRole())) return REDIRECT_DASHBOARD;
        WorkerAvailability wa = workerAvailabilityRepository.findById(id).orElse(null);
        if (wa != null && wa.getWorker().equals(worker)) {
            workerAvailabilityRepository.delete(wa);
        }
        return REDIRECT_DASHBOARD;
    }

    @PostMapping("/submit-complaint")
    public String submitComplaint(@RequestParam String subject, @RequestParam String description,
                                  @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User user = userRepository.findByMobile(userDetails.getUsername());
        
        if (user != null) {
            com.labor.model.Complaint complaint = new com.labor.model.Complaint();
            complaint.setRaisedBy(user);
            complaint.setSubject(subject);
            complaint.setDescription(description);
            complaint.setCreatedAt(LocalDateTime.now());
            complaint.setStatus("Pending");
            complaintRepository.save(complaint);
        }
        return "redirect:/dashboard?complaintSubmitted=true";
    }
}