package com.labor.config;

import com.labor.model.User;
import com.labor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if an Admin already exists
        if (!userRepository.existsByMobile("admin")) {
            User admin = new User();
            admin.setName("Super Admin");
            admin.setMobile("admin"); // The username to login
            admin.setPassword(passwordEncoder.encode("admin123")); // The secret password
            admin.setRole("Admin");
            admin.setGender("Other");
            admin.setIsBanned(false);
            
            userRepository.save(admin);
            System.out.println("✅ SUPER ADMIN CREATED: Username: 'admin' | Password: 'admin123'");
        }
    }
}