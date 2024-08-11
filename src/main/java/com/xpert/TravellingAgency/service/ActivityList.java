package com.xpert.TravellingAgency.service;

import com.amadeus.resources.Activity;

public interface ActivityList {

	public Activity[] getActivitiesByGeoCode(double latitude, double longitude);

}
