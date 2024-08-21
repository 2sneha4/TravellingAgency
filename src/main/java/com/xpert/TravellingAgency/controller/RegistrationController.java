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

    // Display the registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Return the name of the HTML file (register.html)
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String fullName,
            @RequestParam Integer age,
            @RequestParam String gender,
            HttpSession session, // To manage session
            Model model) {
        
        if (userAccountService.usernameExists(username)) {
            model.addAttribute("error", "Username already exists");
            return "register"; // Return to the registration form with error message
        }
        
        UserAccount newUser = new UserAccount();
        newUser.setUsername(username);
        newUser.setPassword(password); // Consider hashing the password in production
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setFullName(fullName);
        newUser.setAge(age);
        newUser.setGender(gender);
        
        userAccountService.save(newUser);

        // Store user in session
        session.setAttribute("user", newUser);
        
        return "redirect:/"; // Redirect to home page after successful registration
    }
}
