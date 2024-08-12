package com.xpert.TravellingAgency.service;

import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;

@Service
public class LocationListImplementation implements LocationList{
	
	private Amadeus amadeus;
	
	public LocationListImplementation() {
		
		amadeus = Amadeus.builder("77LONFz3hRcuNpfja0mAkwVm939AFLye","hXNUPNUAQAcepciP").build();
	}
	
	@Override
	public Location[] getLocationsByKeyword(String keyword) {
		
		Location[] locations = null;
		
		try {
			locations = amadeus.referenceData.locations.get(Params
					  .with("keyword", keyword)
					  .and("subType", "CITY"));
			
		} catch (ResponseException e) {
			
			e.printStackTrace();
		}
		
		return locations;
	}

}
