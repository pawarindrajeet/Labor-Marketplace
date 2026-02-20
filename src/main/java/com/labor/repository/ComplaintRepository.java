package com.labor.repository;

import com.labor.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    // Fetches all complaints, showing the newest ones at the very top
    List<Complaint> findAllByOrderByCreatedAtDesc();
}