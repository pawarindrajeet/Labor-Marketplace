package com.labor.repository;

import com.labor.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
    
    boolean existsByJobIdAndWorkerId(Integer jobId, Integer workerId);

    Response findByJobIdAndWorkerId(Integer jobId, Integer workerId);

    List<Response> findByWorkerId(Integer workerId);
    
    // NEW: Fetch all applications for a specific job so the farmer can view applicants
    List<Response> findByJobId(Integer jobId);
}