package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.model.HotelBooking;

@Controller
@RequestMapping("/booking-confirmation")
public class BookingConfirmationPageController {
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@GetMapping
	public String getBookingConfirmation(
			@ModelAttribute("hotelBooking") HotelBooking hotelBooking,
			Model model) {
		
		model.addAttribute("hotelBooking", hotelBooking);
		
		return "booking-confirmation";
		
	}

}
