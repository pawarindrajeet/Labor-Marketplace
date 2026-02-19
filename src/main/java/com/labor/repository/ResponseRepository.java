package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labor.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
    boolean existsByJobIdAndWorkerId(Integer jobId, Integer workerId);
    Response findByJobIdAndWorkerId(Integer jobId, Integer workerId);
    java.util.List<Response> findByWorkerId(Integer workerId);
}
