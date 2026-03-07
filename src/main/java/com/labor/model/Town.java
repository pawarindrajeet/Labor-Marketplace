package com.labor.model;

import jakarta.persistence.*;

@Entity
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // This is the Village name
    
    private String district; // NEW
    
    private String state; // NEW

    public Town() {}

    public Town(String name, String district, String state) {
        this.name = name;
        this.district = district;
        this.state = state;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}