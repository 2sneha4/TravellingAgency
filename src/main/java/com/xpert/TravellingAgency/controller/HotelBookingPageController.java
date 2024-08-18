package com.xpert.TravellingAgency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hotel-booking")
public class HotelBookingPageController {
	
	@GetMapping
	public String getBooking(
			Model model) {
	
		
		return "hotel-booking";
	}

}
