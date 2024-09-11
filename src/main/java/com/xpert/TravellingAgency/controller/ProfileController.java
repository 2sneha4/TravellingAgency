package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/profile")
    public ModelAndView showProfilePage(HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("profile"); 
        if (user != null) {
            modelAndView.addObject("user", user); 
        } else {
            modelAndView.setViewName("redirect:/login"); 
        }
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(HttpSession session,
                                @RequestParam String username,
                                @RequestParam(required = false) String password,
                                @RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String phoneNumber,
                                @RequestParam Integer age,
                                @RequestParam String gender) {
        UserAccount user = (UserAccount) session.getAttribute("user");

        if (user != null) {
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAge(age);
            user.setGender(gender);
            
            if (!user.getUsername().equals(username)) {
                user.setUsername(username);
            }
            
            if (password != null && !password.isEmpty()) {
                user.setPassword(password); 
            }

            userAccountService.updateUser(user);

            session.setAttribute("user", user);

            return "redirect:/";
        } else {
            return "redirect:/login"; 
        }
    }
}
