package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.resources.Activity;
import com.amadeus.resources.Hotel;
import com.amadeus.resources.Location;
import com.xpert.TravellingAgency.DAO.ActivityListDAO;
import com.xpert.TravellingAgency.DAO.HotelListDAO;
import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.service.ActivityList;
import com.xpert.TravellingAgency.service.HotelList;
import com.xpert.TravellingAgency.service.LocationList;

@RestController
@RequestMapping("/download")
public class DownloadDataController {
	
	@Autowired
	private HotelList hotelList;
	
	@Autowired
	private ActivityList activityList;
	
	@Autowired
	private HotelListDAO hotelListDAO;
	
	@Autowired
	private ActivityListDAO activityListDAO;
	
	@Autowired
	private LocationList locationList;
	
	@Autowired
	private LocationListDAO locationListDAO;
	
	@GetMapping("/hotels")
	public String downloadHotels(@RequestParam String cityCode, Model model) {
		
		Hotel[] hotels = hotelList.getHotels(cityCode);
		hotelListDAO.saveHotelsInDB(hotels);
		return "Downloaded Successfully";
		
	}
	
	@GetMapping("/activities")
	public String downloadActivities(@RequestParam double latitude, @RequestParam double longitude, Model model) {
		
		Activity[] activities = activityList.getActivitiesByGeoCode(latitude, longitude);
		activityListDAO.saveActivitiesInDB(activities);
		return "Downloaded Successfully";
	}
	
	@GetMapping("/locations")
	public String downloadCityWithCountryDetails(@RequestParam String keyword, Model model) {
		
		Location[] locations = locationList.getLocationsByKeyword(keyword);
		locationListDAO.saveLocationsInDB(locations);
		return "Downloaded Successfully";
	}

}
