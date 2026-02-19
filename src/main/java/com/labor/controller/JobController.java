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

    // Worker responds to a job
    @PostMapping("/{jobId}/respond")
    @Transactional
    public ResponseEntity<?> respondToJob(@PathVariable Integer jobId, @RequestParam Integer workerId) {
        JobPost job = jobPostRepository.findById(jobId).orElseThrow();
        if (job.getIsFull()) return ResponseEntity.badRequest().body("Job is full");

        // Prevent duplicate responses
        if (responseRepository.existsByJobIdAndWorkerId(jobId, workerId))
            return ResponseEntity.badRequest().body("Already responded");

        // Add response
        Response response = new Response(jobId, workerId, LocalDateTime.now());
        responseRepository.save(response);

        // Increment counter and check if full
        int respondedCount = job.getWorkersResponded() == null ? 0 : job.getWorkersResponded();
        job.setWorkersResponded(respondedCount + 1);
        if (job.getWorkersResponded() >= job.getWorkersNeeded()) {
            job.setIsFull(true);
        }
        jobPostRepository.save(job);

        return ResponseEntity.ok("Responded to job");
    }

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
