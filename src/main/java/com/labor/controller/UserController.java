package com.labor.controller;

import com.labor.model.WorkerAvailability;
import com.labor.model.JobPost;
import com.labor.model.User;
import com.labor.model.Town;
import com.labor.model.Response;
import com.labor.model.Complaint;
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
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    private static final String ROLE_FARMER = "Farmer";
    private static final String ROLE_WORKER = "Worker";
    private static final String ROLE_ADMIN = "Admin"; 

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
        // CHANGED: Fetching distinct states instead of all towns
        List<String> states = townRepository.findDistinctStates();
        model.addAttribute("states", states);
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
        user.setIsBanned(false); 
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

        if (Boolean.TRUE.equals(user.getIsBanned())) {
            return "redirect:/login?banned=true"; 
        }

        if (ROLE_ADMIN.equals(user.getRole())) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("user", user);
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("currentTime", now); 
        
        // Note: Keeping all towns here for the filter dropdowns on the dashboard
        List<Town> towns = townRepository.findAll();
        model.addAttribute("towns", towns);

        if (ROLE_FARMER.equals(user.getRole())) {
            List<JobPost> activeJobs = jobPostRepository.findByIsFullFalseOrderByCreatedAtDesc().stream()
                .filter(job -> job.getTown() != null)
                .filter(job -> job.getEndDate() == null || job.getEndDate().isAfter(now))
                .collect(Collectors.toList());
            
            List<WorkerAvailability> activeAvailabilities = workerAvailabilityRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(availability -> availability.getWorker() != null && availability.getWorker().getTown() != null)
                .filter(wa -> wa.getEndDate() == null || wa.getEndDate().isAfter(now))
                .collect(Collectors.toList());
            
            List<JobPost> myJobs = jobPostRepository.findAll().stream()
                .filter(job -> job.getFarmer() != null && job.getFarmer().getId().equals(user.getId()))
                .sorted((j1, j2) -> {
                    if (j1.getCreatedAt() == null && j2.getCreatedAt() == null) return 0;
                    if (j1.getCreatedAt() == null) return 1;
                    if (j2.getCreatedAt() == null) return -1;
                    return j2.getCreatedAt().compareTo(j1.getCreatedAt());
                })
                .collect(Collectors.toList());

            model.addAttribute("jobs", activeJobs);
            model.addAttribute("availabilities", activeAvailabilities);
            model.addAttribute("myJobs", myJobs);
            
            return "farmer_dashboard";
            
        } else {
            // WORKER LOGIC
            List<JobPost> activeJobs = jobPostRepository.findByIsFullFalseOrderByCreatedAtDesc().stream()
                .filter(job -> job.getTown() != null)
                .filter(job -> job.getEndDate() == null || job.getEndDate().isAfter(now))
                .collect(Collectors.toList());
            
            List<WorkerAvailability> activeAvailabilities = workerAvailabilityRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(wa -> wa.getWorker() != null && wa.getWorker().getTown() != null)
                .filter(wa -> wa.getEndDate() == null || wa.getEndDate().isAfter(now))
                .collect(Collectors.toList());

            List<Response> myResponses = responseRepository.findByWorkerId(user.getId());
            
            long acceptedCount = myResponses.stream()
                .filter(r -> "Accepted".equals(r.getStatus()))
                .count();
            model.addAttribute("notificationCount", acceptedCount);

            Set<Integer> respondedJobIds = myResponses.stream()
                .map(response -> response.getJob().getId()).collect(Collectors.toSet());

            model.addAttribute("jobs", activeJobs);
            model.addAttribute("availabilities", activeAvailabilities);
            model.addAttribute("myResponses", myResponses);
            model.addAttribute("respondedJobIds", respondedJobIds);
            
            return "worker_feed";
        }
    }

    // --- PROFILE ENDPOINTS ---
    
    @GetMapping("/profile")
    public String showProfile(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null || Boolean.TRUE.equals(user.getIsBanned())) return REDIRECT_LOGIN;
        
        List<Complaint> myComplaints = complaintRepository.findAll().stream()
                .filter(c -> c.getRaisedBy() != null && c.getRaisedBy().getId().equals(user.getId()))
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("myComplaints", myComplaints);
        
        // CHANGED: Fetching distinct states instead of all towns
        List<String> states = townRepository.findDistinctStates();
        model.addAttribute("states", states);
        
        return "profile";
    }

    @PostMapping("/profile/update")
    @Transactional
    public String updateProfile(@RequestParam String name, 
                                @RequestParam String mobile,
                                @RequestParam String gender,
                                @RequestParam Integer townId,
                                @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null) return REDIRECT_LOGIN;

        boolean isMobileChanged = !user.getMobile().equals(mobile);
        
        user.setName(name);
        user.setMobile(mobile);
        user.setGender(gender);
        Town town = townRepository.findById(townId).orElseThrow();
        user.setTown(town);
        
        userRepository.save(user);

        if (isMobileChanged) {
            return "redirect:/logout"; 
        }
        
        return "redirect:/profile?updated=true";
    }

    @GetMapping("/jobs/{jobId}/view-applicants")
    public String viewApplicants(@PathVariable Integer jobId, Model model, 
                                 @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User user = userRepository.findByMobile(userDetails.getUsername());
        JobPost job = jobPostRepository.findById(jobId).orElseThrow();

        if (!job.getFarmer().getId().equals(user.getId())) return REDIRECT_DASHBOARD;

        List<Response> applicants = responseRepository.findByJobId(jobId);
        model.addAttribute("job", job);
        model.addAttribute("applicants", applicants);
        return "view_applicants";
    }

    @PostMapping("/post-availability")
    @Transactional
    public String postAvailability(@RequestParam String skills, @RequestParam String availability, 
                                   @RequestParam Double wage, @RequestParam String startDate, @RequestParam String endDate,
                                   @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User worker = userRepository.findByMobile(userDetails.getUsername());
        if (worker == null || Boolean.TRUE.equals(worker.getIsBanned())) return REDIRECT_LOGIN; 

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
    public String showPostAvailabilityForm(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null || !ROLE_WORKER.equals(user.getRole())) return REDIRECT_DASHBOARD;
        
        // CHANGED: Fetching distinct states instead of all towns
        List<String> states = townRepository.findDistinctStates();
        model.addAttribute("states", states);
        
        return "post_availability";
    }

    @GetMapping("/post-job")
    public String showPostJobForm(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User user = userRepository.findByMobile(userDetails.getUsername());
        
        // Security Check: Only Farmers can see the Post Job form
        if (user == null || !ROLE_FARMER.equals(user.getRole())) return REDIRECT_DASHBOARD;
        
        // CHANGED: Fetching distinct states instead of all towns
        List<String> states = townRepository.findDistinctStates();
        model.addAttribute("states", states);
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
                          @RequestParam Integer townId,
                          @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return REDIRECT_LOGIN;
        User farmer = userRepository.findByMobile(userDetails.getUsername());
        if (farmer == null || !ROLE_FARMER.equals(farmer.getRole()) || Boolean.TRUE.equals(farmer.getIsBanned())) return REDIRECT_DASHBOARD; 

        JobPost job = new JobPost();
        job.setFarmer(farmer);
        Town selectedTown = townRepository.findById(townId).orElse(farmer.getTown());
        job.setTown(selectedTown);
        
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
            Complaint complaint = new Complaint();
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