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

import com.xpert.TravellingAgency.DAO.ActivityListDAO;
import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.model.Activity;
import com.xpert.TravellingAgency.service.ActivityList;

@Controller
@RequestMapping("/destination")
public class DestinationPageController {
	
	@Autowired
	ActivityList activityList;
	
	@Autowired
	ActivityListDAO activityListDAO;
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@GetMapping
	public String destinationPage(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "20") int size,
	        @RequestParam(name = "city", defaultValue = "PARIS") String cityName,
	        @RequestParam(name = "ajax", defaultValue = "false") boolean ajax,
	        Model model) {
				
		Pageable pageable = PageRequest.of(page, size);
		
		double latitude = locationListDAO.getLatitude(cityName);
		double longitude = locationListDAO.getLongitude(cityName);
		
        Page<Activity> activityPage = activityListDAO.getAllActivitiesByCity(latitude, longitude, 20, pageable);
                
        // Calculate the start and end page numbers for display
        int totalPages = activityPage.getTotalPages();
        int startPage = Math.max(0, page - 2); // Display up to 2 pages before the current page
        int endPage = Math.min(totalPages - 1, page + 2); // Display up to 2 pages after the current page

        if (endPage - startPage < 4) { // Adjust if there are fewer pages than 5
            if (startPage > 0) {
                endPage = Math.min(totalPages - 1, startPage + 4);
            } else {
                endPage = Math.min(totalPages - 1, 4);
            }
        }

        model.addAttribute("activities", activityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("cityName", cityName);
        
		model.addAttribute("locationListDAO", locationListDAO);
		
		if(ajax)
			return "fragments/activity-fragment :: activityFragment";
		
		return "destination";
	}
	
	@GetMapping("/cities")
	@ResponseBody
	public List<String> getAllCities() {
		
		List<String> cities = locationListDAO.getAllCities();
		return cities;
	}

}
