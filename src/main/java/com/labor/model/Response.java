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

    public Response() {}
    public Response(Integer jobId, Integer workerId, LocalDateTime respondedAt) {
        this.job = new JobPost();
        this.job.setId(jobId);
        this.worker = new User();
        this.worker.setId(workerId);
        this.respondedAt = respondedAt;
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
}
