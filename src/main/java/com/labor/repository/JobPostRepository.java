package com.labor.repository;

import com.labor.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    List<JobPost> findByTownIdAndIsFullFalseOrderByCreatedAtDesc(Integer townId);
    List<JobPost> findByIsFullFalseOrderByCreatedAtDesc();
}
