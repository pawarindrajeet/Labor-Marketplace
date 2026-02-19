package com.labor.repository;

import com.labor.model.WorkerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkerAvailabilityRepository extends JpaRepository<WorkerAvailability, Integer> {
    List<WorkerAvailability> findByWorker_Town_IdOrderByCreatedAtDesc(Integer townId);
    List<WorkerAvailability> findAllByOrderByCreatedAtDesc();
}
