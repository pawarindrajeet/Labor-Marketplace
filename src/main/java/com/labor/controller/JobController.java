package com.labor.controller;

import com.labor.model.JobPost;
import com.labor.model.Response;
import com.labor.repository.JobPostRepository;
import com.labor.repository.ResponseRepository;
import com.labor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobPostRepository jobPostRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;

    // Farmer posts a job
    @PostMapping("/post")
    @Transactional
    public ResponseEntity<?> postJob(@RequestBody JobPost jobPost) {
        jobPost.setWorkersResponded(0);
        jobPost.setIsFull(false);
        jobPost.setCreatedAt(LocalDateTime.now());
        jobPostRepository.save(jobPost);
        return ResponseEntity.ok("Job posted");
    }

    // Worker gets jobs by town
    @GetMapping("/town/{townId}")
    public List<JobPost> getJobsByTown(@PathVariable Integer townId) {
        return jobPostRepository.findByTownIdAndIsFullFalseOrderByCreatedAtDesc(townId);
    }

    // Get all applicants for a specific job (Used by Farmer)
    @GetMapping("/{jobId}/applicants")
    public List<Response> getApplicants(@PathVariable Integer jobId) {
        return responseRepository.findByJobId(jobId);
    }

    // Farmer accepts a specific worker for a job
    @PostMapping("/responses/{responseId}/accept")
    @Transactional
    public ResponseEntity<?> acceptWorker(@PathVariable Integer responseId) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        
        response.setStatus("Accepted");
        responseRepository.save(response);
        
        return ResponseEntity.ok("Worker accepted successfully");
    }

    // Worker responds to a job
    @PostMapping("/{jobId}/respond")
    @Transactional
    public ResponseEntity<?> respondToJob(@PathVariable Integer jobId, @RequestParam Integer workerId) {
        JobPost job = jobPostRepository.findById(jobId).orElseThrow();
        if (job.getIsFull()) return ResponseEntity.badRequest().body("Job is full");

        if (responseRepository.existsByJobIdAndWorkerId(jobId, workerId))
            return ResponseEntity.badRequest().body("Already responded");

        Response response = new Response(jobId, workerId, LocalDateTime.now());
        responseRepository.save(response);

        int respondedCount = job.getWorkersResponded() == null ? 0 : job.getWorkersResponded();
        job.setWorkersResponded(respondedCount + 1);
        if (job.getWorkersResponded() >= job.getWorkersNeeded()) {
            job.setIsFull(true);
        }
        jobPostRepository.save(job);

        return ResponseEntity.ok("Responded to job");
    }

    // Worker acknowledges hire (clears notification bell)
    @PostMapping("/responses/{responseId}/acknowledge")
    @Transactional
    public ResponseEntity<?> acknowledgeAcceptance(@PathVariable Integer responseId) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        
        response.setStatus("Hired"); 
        responseRepository.save(response);
        
        return ResponseEntity.ok("Notification cleared");
    }

    // Farmer marks worker as finished and gives a rating (0-5)
    @PostMapping("/responses/{responseId}/complete")
    @Transactional
    public ResponseEntity<?> completeJobAssignment(@PathVariable Integer responseId, 
                                                   @RequestParam Integer rating,
                                                   @RequestParam(required = false) String feedback) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        
        response.setStatus("Completed");
        response.setRating(rating); // Saves the star rating
        
        if (feedback != null && !feedback.trim().isEmpty()) {
            response.setFeedback(feedback.trim()); // Saves optional text feedback
        }
        
        responseRepository.save(response);
        
        return ResponseEntity.ok("Job marked as completed and rated");
    }

    // Worker cancels their response/application
    @DeleteMapping("/{jobId}/respond")
    @Transactional
    public ResponseEntity<?> removeResponse(@PathVariable Integer jobId, @RequestParam Integer workerId) {
        JobPost job = jobPostRepository.findById(jobId).orElseThrow();
        Response existing = responseRepository.findByJobIdAndWorkerId(jobId, workerId);
        if (existing == null) {
            return ResponseEntity.badRequest().body("No response found");
        }

        responseRepository.delete(existing);

        int respondedCount = job.getWorkersResponded() == null ? 0 : job.getWorkersResponded();
        int newCount = Math.max(0, respondedCount - 1);
        job.setWorkersResponded(newCount);
        if (job.getIsFull() && newCount < job.getWorkersNeeded()) {
            job.setIsFull(false);
        }
        jobPostRepository.save(job);

        return ResponseEntity.ok("Response removed");
    }
}