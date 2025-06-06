package com.xpert.TravellingAgency.service;

import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Activity;


@Service
public class ActivityListImplementation implements ActivityList{
	
	private Amadeus amadeus;
	
	public ActivityListImplementation() {
		
		amadeus = Amadeus.builder("lACw4cJMQgPFzKeGHEAzBsXhhhVtCrhK","otSTb0SxY1Y1JWnF").build();

	}
	
	@Override
	public Activity[] getActivitiesByGeoCode(double latitude, double longitude) {
		
		Activity[] activities = null;
		
		try {
			activities = amadeus.shopping.activities.get(Params
					   .with("latitude", latitude)
					   .and("longitude", longitude)
					   .and("radius", 20));
		} catch (ResponseException e) {
			
			e.printStackTrace();
		} 
		
		if (activities[0].getResponse().getStatusCode() != 200) {
			System.out.println("Wrong status code: " + activities[0].getResponse().getStatusCode());
			System.exit(-1);
    	}
		
		return activities;
	}
}
