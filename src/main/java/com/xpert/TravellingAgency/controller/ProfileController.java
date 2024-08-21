package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/profile")
    public String showProfile(@RequestParam String id, Model model) {
        UserAccount user = userAccountService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @RequestParam String id,
            @RequestParam String username,
            @RequestParam(required = false) String password,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String fullName,
            @RequestParam Integer age,
            @RequestParam String gender,
            Model model) {

        UserAccount user = userAccountService.findById(id);
        if (user != null) {
            user.setUsername(username);
            if (password != null && !password.isEmpty()) {
                user.setPassword(password); 
            }
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setFullName(fullName);
            user.setAge(age);
            user.setGender(gender);
            
            userAccountService.save(user);
            model.addAttribute("message", "Profile updated successfully");
        } else {
            model.addAttribute("error", "User not found");
        }
        return "profile";
    }
}
