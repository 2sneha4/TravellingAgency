package com.xpert.TravellingAgency.service;

import com.amadeus.resources.City;
import com.amadeus.resources.Hotel;
import com.amadeus.resources.HotelOfferSearch;

public interface HotelList {
		
	public HotelOfferSearch getHotelOffers(String hotelId);
	
	public City[] getCitiesByKeyword(String keyword);

	Hotel[] getHotels(String cityCode);
	
}
