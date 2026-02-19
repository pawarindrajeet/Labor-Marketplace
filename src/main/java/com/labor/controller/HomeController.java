package com.labor.controller;

import com.labor.model.WorkerAvailability;
import com.labor.model.JobPost;
import com.labor.model.Town;
import com.labor.repository.WorkerAvailabilityRepository;
import com.labor.repository.JobPostRepository;
import com.labor.repository.TownRepository;
import com.labor.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private WorkerAvailabilityRepository workerAvailabilityRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<JobPost> jobs = jobPostRepository.findByIsFullFalseOrderByCreatedAtDesc().stream()
            .filter(job -> job.getTown() != null)
            .collect(Collectors.toList());
        List<WorkerAvailability> availabilities = workerAvailabilityRepository.findAllByOrderByCreatedAtDesc().stream()
            .filter(availability -> availability.getWorker() != null && availability.getWorker().getTown() != null)
            .collect(Collectors.toList());
        List<Town> towns = townRepository.findAll();
        model.addAttribute("jobs", jobs);
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("towns", towns);
        long totalInterests = responseRepository.count();
        model.addAttribute("totalInterests", totalInterests);
        return "landing";
    }
}
