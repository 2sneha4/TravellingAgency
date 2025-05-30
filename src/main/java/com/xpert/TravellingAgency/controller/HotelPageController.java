package com.xpert.TravellingAgency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String hotelPage(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "20") int size,
	        @RequestParam(name = "city", defaultValue = "PARIS") String cityName,
	        @RequestParam(name = "ajax", defaultValue = "false") boolean ajax,
	        @RequestParam(name = "checkindate", required = false) String checkInDate,
	        @RequestParam(name = "checkoutdate", required = false) String checkOutDate,
	        @RequestParam(name = "rooms", defaultValue = "1") int rooms,
	        @RequestParam(name = "guests", defaultValue = "1") int guests,
	        Model model) {
		
		String cityCode = locationListDAO.getCityCode(cityName);
		
		Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelPage = hotelListDAO.getHotelsByCity(cityCode, pageable);
        
        // Calculate the start and end page numbers for display
        int totalPages = hotelPage.getTotalPages();
        int startPage = Math.max(0, page - 2); // Display up to 2 pages before the current page
        int endPage = Math.min(totalPages - 1, page + 2); // Display up to 2 pages after the current page

        if (endPage - startPage < 4) { // Adjust if there are fewer pages than 5
            if (startPage > 0) {
                endPage = Math.min(totalPages - 1, startPage + 4);
            } else {
                endPage = Math.min(totalPages - 1, 4);
            }
        }
		
        model.addAttribute("hotels", hotelPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("cityName",cityName);
        model.addAttribute("checkInDate",checkInDate);
        model.addAttribute("checkOutDate",checkOutDate);
        model.addAttribute("rooms",rooms);
        model.addAttribute("guests",guests);
        
        model.addAttribute("navigationPage", "hotel");
		model.addAttribute("locationListDAO", locationListDAO);
		
		if(ajax)
			return "fragments/hotel-fragment :: hotelFragment";
		
		return "hotel";
	}
	
	@GetMapping("/cities")
	@ResponseBody
	public List<String> getAllCities() {
		
		List<String> cities = locationListDAO.getAllCities();
		return cities;
	}
	
	

}
