package com.xpert.TravellingAgency.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOfferSearch;

@Service
public class HotelOffersImplementation implements HotelOffers{
	
private Amadeus amadeus;
	
	public HotelOffersImplementation() {
		
		amadeus = Amadeus.builder("77LONFz3hRcuNpfja0mAkwVm939AFLye","hXNUPNUAQAcepciP").build();
	}
	
	@Override
	public HotelOfferSearch[] getHotelOffers(String hotelId) {
		
		HotelOfferSearch[] hotelOffers = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate tenDaysAheadDate = LocalDate.now().plusDays(14);
								
		try {
			hotelOffers = amadeus.shopping.hotelOffersSearch.get(Params
					  .with("hotelIds", "MCLONGHM")
					  .and("adults", 1)
					  .and("checkInDate", tenDaysAheadDate.format(formatter).toString())
					  .and("roomQuantity", 1)
					  .and("paymentPolicy", "NONE")
					  .and("bestRateOnly", true));
		} catch (ResponseException e) {
			e.printStackTrace();
		}
		
		return hotelOffers;
		
	}

}
