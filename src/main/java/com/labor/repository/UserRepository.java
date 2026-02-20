package com.labor.repository;

import com.labor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Finds a user to log them in
    User findByMobile(String mobile);
    
    // THIS FIXES YOUR RED LINE: Checks if a user already exists
    boolean existsByMobile(String mobile);
}