package com.xpert.TravellingAgency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpert.TravellingAgency.DAO.HotelListDAO;
import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.model.Hotel;
import com.xpert.TravellingAgency.service.HotelList;

@Controller
@RequestMapping("/hotel")
public class HotelPageController {
	
	@Autowired
	HotelList hotelList;
	
	@Autowired
	HotelListDAO hotelListDAO;
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@GetMapping
	public String hotelPage(Model model) {
		
		List<Hotel> hotels = hotelListDAO.getHotelsByCity("AMD");
		
		model.addAttribute("locationListDAO", locationListDAO);
		model.addAttribute("hotels", hotels);
		return "hotel";
	}
	
	

}
