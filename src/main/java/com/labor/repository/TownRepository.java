package com.labor.repository;

import com.labor.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TownRepository extends JpaRepository<Town, Integer> {
    
    // 1. Check if the village already exists in that specific district and state
    boolean existsByNameIgnoreCaseAndDistrictIgnoreCaseAndStateIgnoreCase(String name, String district, String state);

    // 2. Fetch a list of unique states for the first dropdown
    @Query("SELECT DISTINCT t.state FROM Town t WHERE t.state IS NOT NULL ORDER BY t.state ASC")
    List<String> findDistinctStates();

    // 3. Fetch a list of unique districts for a specific state for the second dropdown
    @Query("SELECT DISTINCT t.district FROM Town t WHERE t.state = :state AND t.district IS NOT NULL ORDER BY t.district ASC")
    List<String> findDistinctDistrictsByState(@Param("state") String state);

    // 4. Fetch all villages for a specific state and district
    List<Town> findByStateAndDistrictOrderByNameAsc(String state, String district);
}