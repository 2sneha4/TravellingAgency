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

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("hotel-booking")
public class HotelBookingPageController {
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@GetMapping
	public String getBooking(
			@ModelAttribute("hotelBooking") HotelBooking hotelBooking,
			HttpSession session,
			Model model) {		
		
		hotelBookingDAO.saveBookingIntoDB(hotelBooking);
		
		model.addAttribute("hotelBooking", hotelBooking);
		model.addAttribute("navigationPage", "hotel");
		
		return "hotel-booking";
	}

}
