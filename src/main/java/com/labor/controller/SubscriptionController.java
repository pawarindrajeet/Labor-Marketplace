package com.labor.controller;

import com.labor.model.User;
import com.labor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class SubscriptionController {

    @Autowired
    private UserRepository userRepository;

    // 1. Show the Pricing/Upgrade Page
    @GetMapping("/pricing")
    public String showPricingPage(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return "redirect:/login";
        
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null) return "redirect:/login";
        
        model.addAttribute("user", user);
        return "pricing";
    }

    // 2. Process the "Dummy" Payment and Upgrade Account
    @PostMapping("/subscribe")
    public String processSubscription(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        if (userDetails == null) return "redirect:/login";
        
        User user = userRepository.findByMobile(userDetails.getUsername());
        if (user == null) return "redirect:/login";

        // Upgrade Logic: Set to Premium and add 30 days of access!
        user.setSubscriptionPlan("PREMIUM");
        user.setSubscriptionEndDate(LocalDateTime.now().plusDays(30));
        
        userRepository.save(user);

        // Send them back to their dashboard with a success message
        return "redirect:/dashboard?upgraded=true";
    }
}