package com.xpert.TravellingAgency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xpert.TravellingAgency.DAO.LocationListDAO;


@Controller
@RequestMapping("/")
public class HomePageController {
	
	@Autowired
	private LocationListDAO locationListDAO;
	
	@GetMapping
	public String homePage(@RequestParam(name = "city", defaultValue = "PARIS") String cityName,
			Model model) {
		
		List<com.xpert.TravellingAgency.model.Location> locations = locationListDAO.getAllLocations();
		
		model.addAttribute("locations", locations);
		model.addAttribute("cityName", cityName);
		model.addAttribute("navigationPage", "home");
		
		return "index";
	}
	
	@GetMapping("/cities")
	@ResponseBody
	public List<String> getAllCities() {
		
		List<String> cities = locationListDAO.getAllCities();
		return cities;
	}
	
	@GetMapping("/api-react")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Hello from Spring Boot!");
    }

	
	

}
