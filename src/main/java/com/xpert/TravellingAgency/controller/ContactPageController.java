package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.ContactMessage;
import com.xpert.TravellingAgency.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("contact")
public class ContactPageController {

    private static final Logger logger = LoggerFactory.getLogger(ContactPageController.class);

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @GetMapping
    public String getContact(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping
    public String submitContactForm(@ModelAttribute ContactMessage contactMessage, Model model) {
        try {
            logger.info("Received contact message: {}", contactMessage);
            contactMessageRepository.save(contactMessage);
            model.addAttribute("successMessage", "Your request has been noted. Thank you!");
        } catch (Exception e) {
            logger.error("Error saving contact message", e);
            model.addAttribute("errorMessage", "There was an error processing your request. Please try again.");
        }
        return "contact";
    }
}
