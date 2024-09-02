package com.xpert.TravellingAgency.service;

import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.City;
import com.amadeus.resources.Hotel;
import com.amadeus.resources.HotelOfferSearch;

@Service
public class HotelListImplementation implements HotelList{
	
	private Amadeus amadeus;
	
	public HotelListImplementation() {
		
		amadeus = Amadeus.builder("lACw4cJMQgPFzKeGHEAzBsXhhhVtCrhK","otSTb0SxY1Y1JWnF").build();

	}

	@Override
	public Hotel[] getHotels(String cityCode) {    	

	    Hotel[] hotels = null;
	    
		try {
			
			hotels = amadeus.referenceData.locations.hotels.byCity.get(Params
					.with("cityCode", cityCode));
			} catch (ResponseException e) {
			
			e.printStackTrace();
		}
		
		if (hotels[0].getResponse().getStatusCode() != 200) {
			System.out.println("Wrong status code: " + hotels[0].getResponse().getStatusCode());
			System.exit(-1);
    	}
		System.out.println(hotels[0]);

		return hotels; 
	}

	@Override
	public HotelOfferSearch getHotelOffers(String hotelId) {
		
		HotelOfferSearch[] hotelOfferSearch = null;
		
		try {
			
			hotelOfferSearch = amadeus.shopping.hotelOffersSearch.get(Params
					  .with("hotelIds", hotelId));
			
			if(hotelOfferSearch == null)
				return null;
			
		} catch (ResponseException e) {
			e.printStackTrace();
			return null;
		}
		
		return hotelOfferSearch[0];
	}

	@Override
	public City[] getCitiesByKeyword(String keyword) {
		
		City[] cities = null;
		
		try {
			
			cities = amadeus.referenceData.locations.cities.get(Params.with("keywod", keyword));
			
		} catch (ResponseException e) {
			e.printStackTrace();
		}
		return cities;
	}
	
}
