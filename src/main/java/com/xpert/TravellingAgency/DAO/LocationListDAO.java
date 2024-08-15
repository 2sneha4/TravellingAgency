package com.xpert.TravellingAgency.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.resources.Location;
import com.xpert.TravellingAgency.repository.LocationRepository;

@Service
public class LocationListDAO {
	
	@Autowired
	LocationRepository locationRepository;
	
	public void saveLocationsInDB(Location[] locations) {
		
		for(Location location : locations) {
			
			com.xpert.TravellingAgency.model.Location myLocation = new com.xpert.TravellingAgency.model.Location();
			
			myLocation.setDetailedName(location.getDetailedName());
			myLocation.setIataCode(location.getIataCode());
			myLocation.setName(location.getName());
			myLocation.setRelevance(location.getRelevance());
			myLocation.setSubType(location.getSubType());
			myLocation.setTimeZoneOffset(location.getTimeZoneOffset());
			myLocation.setType(location.getType());
			
			com.xpert.TravellingAgency.model.Location.Address address = new com.xpert.TravellingAgency.model.Location.Address();
			
			address.setCityCode(location.getAddress().getCityCode());
			address.setCityName(location.getAddress().getCityName());
			address.setCountryCode(location.getAddress().getCountryCode());
			address.setCountryName(location.getAddress().getCountryName());
			address.setRegionCode(location.getAddress().getRegionCode());
			myLocation.setAddress(address);
			
			com.xpert.TravellingAgency.model.Location.GeoCode geoCode = new com.xpert.TravellingAgency.model.Location.GeoCode();
			
			geoCode.setLatitude(location.getGeoCode().getLatitude());
			geoCode.setLongitude(location.getGeoCode().getLongitude());
			myLocation.setGeoCode(geoCode);
			
			locationRepository.save(myLocation);
		}
		
	}
	
	public String getCityName(String iataCode) {
		
		return locationRepository.findById(iataCode).get().getAddress().getCityName();
		
	}
	
	public String getCountryName(String iataCode) {
		
		return locationRepository.findById(iataCode).get().getAddress().getCountryName();
		
	}
	
	public List<String> getAllCities() {
		
		List<com.xpert.TravellingAgency.model.Location> locations = locationRepository.findAll();
		
		List<String> cities = new ArrayList<>();
		
		for(com.xpert.TravellingAgency.model.Location location : locations) {
			cities.add(location.getAddress().getCityName());
		}
		
		return cities;
	}
	
	public String getCityCode(String cityName) {
		
		return locationRepository.getLocationByName(cityName).getIataCode();
		
	}
	
	public List<com.xpert.TravellingAgency.model.Location> getAllLocations() {
		return locationRepository.findAll();
	}
	
	public double getLatitude(String cityName) {
		return locationRepository.findById(getCityCode(cityName)).get().getGeoCode().getLatitude();
	}
	
	public double getLongitude(String cityName) {
		return locationRepository.findById(getCityCode(cityName)).get().getGeoCode().getLongitude();
	}

}
