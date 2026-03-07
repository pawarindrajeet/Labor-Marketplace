package com.labor.controller;

import com.labor.model.Town;
import com.labor.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private TownRepository townRepository;

    // 1. Fetch all distinct states
    @GetMapping("/states")
    public List<String> getStates() {
        return townRepository.findDistinctStates();
    }

    // 2. Fetch districts based on the selected state
    @GetMapping("/districts")
    public List<String> getDistricts(@RequestParam String state) {
        return townRepository.findDistinctDistrictsByState(state);
    }

    // 3. Fetch specific villages/towns based on the selected state and district
    @GetMapping("/towns")
    public List<Town> getTowns(@RequestParam String state, @RequestParam String district) {
        return townRepository.findByStateAndDistrictOrderByNameAsc(state, district);
    }
}