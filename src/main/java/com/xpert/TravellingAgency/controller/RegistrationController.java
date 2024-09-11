package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; 
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String fullName,
            @RequestParam Integer age,
            @RequestParam String gender,
            HttpSession session, 
            Model model) {
        
        if (userAccountService.usernameExists(username)) {
            model.addAttribute("error", "Username already exists");
            return "register"; 
        }
        
        UserAccount newUser = new UserAccount();
        newUser.setUsername(username);
        newUser.setPassword(password); 
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setFullName(fullName);
        newUser.setAge(age);
        newUser.setGender(gender);
        
        userAccountService.save(newUser);

        session.setAttribute("user", newUser);
        
        return "redirect:/login"; 
    }
}
