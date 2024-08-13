package com.xpert.TravellingAgency.service;

import com.amadeus.resources.HotelOfferSearch;

public interface HotelOffers {

	HotelOfferSearch[] getHotelOffers(String hotelId);

}
