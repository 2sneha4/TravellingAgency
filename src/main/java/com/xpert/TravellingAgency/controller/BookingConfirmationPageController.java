package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpert.TravellingAgency.DAO.HotelBookingDAO;

@Controller
@RequestMapping("/booking-confirmation")
public class BookingConfirmationPageController {
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@GetMapping
	public String getBookingConfirmation(
			Model model) {
		
		return "booking-confirmation";
		
	}

}
