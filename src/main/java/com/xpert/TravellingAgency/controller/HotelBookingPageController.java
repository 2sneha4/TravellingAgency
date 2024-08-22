package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.model.HotelBooking;


@Controller
@RequestMapping("hotel-booking")
public class HotelBookingPageController {
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@GetMapping
	public String getBooking(
			@RequestParam(name = "id", required = false) String hotelId,
			@RequestParam(name = "checkindate", required = false) String checkInDate,
	        @RequestParam(name = "checkoutdate", required = false) String checkOutDate,
	        @RequestParam(name = "guests") int guests,
	        @RequestParam(name = "hotelName", defaultValue = "Hotel") String hotelName,
			Model model) {
		
		HotelBooking hotelBooking = new HotelBooking();
		
		hotelBooking.setCheckInDate(checkInDate);
		hotelBooking.setCheckOutDate(checkOutDate);
		hotelBooking.setHotelName(hotelName);
		
		model.addAttribute("guests", guests);
		model.addAttribute("hotelBooking", hotelBooking);
		
		
		return "hotel-booking";
	}

}
