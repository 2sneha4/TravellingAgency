package com.xpert.TravellingAgency.controller;

import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.repository.MyBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyBookingController {
	 @Autowired
	    private MyBookingRepository bookingRepository;

	 @GetMapping("/mybookings")
	 public String showUserBookings(HttpSession session, Model model) {
	     UserAccount userAccount = (UserAccount) session.getAttribute("user"); 
	     
	     if (userAccount != null) {
	         String username = userAccount.getUsername();
	         
	         List<HotelBooking> userBookings = bookingRepository.findByUsername(username);
	         model.addAttribute("bookings", userBookings);
	     }
	     
	     return "mybookings"; 
	 }

	}