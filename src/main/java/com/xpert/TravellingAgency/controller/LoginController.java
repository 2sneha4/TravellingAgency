package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";  
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, 
                                                     @RequestParam String password, 
                                                     HttpSession session) {
        UserAccount user = userAccountService.findByUsername(username);
        Map<String, String> response = new HashMap<>();

        if (user != null && user.getPassword().equals(password)) {  
            session.setAttribute("user", user); 

            String redirectUrl = (String) session.getAttribute("redirectUrl");
            session.removeAttribute("redirectUrl"); 

            response.put("message", "Login successful");
            response.put("redirectUrl", (redirectUrl != null) ? redirectUrl : "/");  

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  
        return "redirect:/";  
    }
}
