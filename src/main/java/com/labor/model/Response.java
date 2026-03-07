package com.labor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobPost job;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    private LocalDateTime respondedAt;
    
    // The hiring status of the worker's application
    private String status = "Pending"; // Can be "Pending", "Accepted", "Hired", "Completed"

    // NEW: Rating given by the farmer when the job is completed (e.g., 1 to 5)
    private Integer rating;

    // NEW: Optional text feedback from the farmer
    @Column(length = 500)
    private String feedback;

    public Response() {}
    public Response(Integer jobId, Integer workerId, LocalDateTime respondedAt) {
        this.job = new JobPost();
        this.job.setId(jobId);
        this.worker = new User();
        this.worker.setId(workerId);
        this.respondedAt = respondedAt;
        this.status = "Pending"; // Set default to Pending
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public JobPost getJob() { return job; }
    public void setJob(JobPost job) { this.job = job; }
    
    public User getWorker() { return worker; }
    public void setWorker(User worker) { this.worker = worker; }
    
    public LocalDateTime getRespondedAt() { return respondedAt; }
    public void setRespondedAt(LocalDateTime respondedAt) { this.respondedAt = respondedAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // NEW: Getters and Setters for Rating and Feedback
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}