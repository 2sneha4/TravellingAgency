package com.xpert.TravellingAgency.service;

import com.amadeus.resources.Location;

public interface LocationList {

	Location[] getLocationsByKeyword(String keyword);

}
