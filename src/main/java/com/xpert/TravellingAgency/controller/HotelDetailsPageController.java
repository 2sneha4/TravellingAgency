package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amadeus.resources.HotelOfferSearch;
import com.xpert.TravellingAgency.service.HotelOffers;

@Controller
@RequestMapping("/hotel-details")
public class HotelDetailsPageController {
	
	
	@Autowired
	HotelOffers hotelOffers;
	
	@GetMapping
	public String getHotelDetails(
			@RequestParam("id") String hotelId,
			@RequestParam(name = "checkindate", required = false) String checkInDate,
	        @RequestParam(name = "checkoutdate", required = false) String checkOutDate,
	        @RequestParam(name = "rooms", defaultValue = "1") int rooms,
	        @RequestParam(name = "guests", defaultValue = "1") int guests,
			Model model) {
		
		System.out.println(checkInDate);
		
		HotelOfferSearch[] hotelOfferSearch = hotelOffers.getHotelOffers(hotelId, checkInDate, checkOutDate, rooms, guests);
		
		model.addAttribute("hotel", hotelOfferSearch[0]);
		
		return "hotel-details";
		
	}

}
