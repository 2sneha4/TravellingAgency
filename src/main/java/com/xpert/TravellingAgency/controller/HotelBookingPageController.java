package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.service.UserAccountService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("hotel-booking")
public class HotelBookingPageController {
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@Autowired
	UserAccountService userAccountService;
	
	@GetMapping
	public String getBooking(
			@ModelAttribute("hotelBooking") HotelBooking hotelBooking,
			HttpSession session,
			Model model) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		if(user == null) {
			model.addAttribute("hotelBooking", hotelBooking);
			return "login-booking";
		}
		
		hotelBookingDAO.saveBookingIntoDB(hotelBooking);
		
		model.addAttribute("hotelBooking", hotelBooking);
		model.addAttribute("navigationPage", "hotel");
		
		return "hotel-booking";
	}
	
	@GetMapping("/login-booking")
	public String loginBooking(@ModelAttribute("hotelBooking") HotelBooking hotelBooking,
			HttpSession session,
			Model model) {
		
		UserAccount user = userAccountService.findByUsername(hotelBooking.getUsername());
		
		if (user != null && user.getPassword().equals(hotelBooking.getPassword())) {
			
			hotelBookingDAO.saveBookingIntoDB(hotelBooking);
            // Successful login
            session.setAttribute("user", user);
            model.addAttribute("hotelBooking", hotelBooking);
    		model.addAttribute("navigationPage", "hotel");
    		return "hotel-booking";
            
        }
		else {
			model.addAttribute("hotelBooking", hotelBooking);
			model.addAttribute("message", "Invalid User or Password");
			return "login-booking";
		}
		
	}

}
