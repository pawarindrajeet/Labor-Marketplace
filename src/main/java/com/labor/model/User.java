package com.labor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String mobile;
    private String password;
    private String role;
    private String gender;
    
    // So the Admin can ban rule-breakers
    private Boolean isBanned = false; 

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    // --- NEW SUBSCRIPTION FIELDS ---
    @Column(columnDefinition = "varchar(255) default 'FREE'")
    private String subscriptionPlan = "FREE"; // Default is FREE

    private LocalDateTime subscriptionEndDate;

    // Existing Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Town getTown() { return town; }
    public void setTown(Town town) { this.town = town; }
    
    // Getter and Setter for the Ban feature
    public Boolean getIsBanned() { return isBanned; }
    public void setIsBanned(Boolean isBanned) { this.isBanned = isBanned; }

    // --- NEW GETTERS & SETTERS FOR SUBSCRIPTION ---
    public String getSubscriptionPlan() { return subscriptionPlan; }
    public void setSubscriptionPlan(String subscriptionPlan) { this.subscriptionPlan = subscriptionPlan; }

    public LocalDateTime getSubscriptionEndDate() { return subscriptionEndDate; }
    public void setSubscriptionEndDate(LocalDateTime subscriptionEndDate) { this.subscriptionEndDate = subscriptionEndDate; }
    
    // Helper method to easily check if subscription is valid
    public boolean isPremium() {
        return "PREMIUM".equals(this.subscriptionPlan) && 
               this.subscriptionEndDate != null && 
               this.subscriptionEndDate.isAfter(LocalDateTime.now());
    }
}