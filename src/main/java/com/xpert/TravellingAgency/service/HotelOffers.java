package com.xpert.TravellingAgency.service;

import com.amadeus.resources.HotelOfferSearch;

public interface HotelOffers {

	HotelOfferSearch[] getHotelOffers(String hotelId, String checkInDate, String checkOutDate, int rooms, int guests);

}
