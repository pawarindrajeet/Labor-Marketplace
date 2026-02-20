package com.labor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Complaint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // The user (Farmer or Worker) who is filing the complaint
    @ManyToOne
    @JoinColumn(name = "raised_by_user_id")
    private User raisedBy;

    // A short title, e.g., "Payment Issue" or "Worker Behavior"
    private String subject;
    
    // The full story
    @Column(columnDefinition = "TEXT")
    private String description;

    // Tracks if the Admin has fixed it yet
    private String status = "Pending"; // "Pending" or "Resolved"
    
    private LocalDateTime createdAt;

    // --- Getters and Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public User getRaisedBy() { return raisedBy; }
    public void setRaisedBy(User raisedBy) { this.raisedBy = raisedBy; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}