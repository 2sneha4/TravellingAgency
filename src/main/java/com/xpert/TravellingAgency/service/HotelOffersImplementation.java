package com.xpert.TravellingAgency.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOfferSearch;
import com.xpert.TravellingAgency.exceptions.AmadeusBadRequestException;
import com.xpert.TravellingAgency.exceptions.AmadeusGeneralException;
import com.xpert.TravellingAgency.exceptions.AmadeusInternalServerErrorException;

@Service
public class HotelOffersImplementation implements HotelOffers{
	
private Amadeus amadeus;
	
	public HotelOffersImplementation() {
		
		amadeus = Amadeus.builder("lACw4cJMQgPFzKeGHEAzBsXhhhVtCrhK","otSTb0SxY1Y1JWnF").build();
	}
	
	@Override
	public HotelOfferSearch[] getHotelOffers(String hotelId, String checkInDate, String checkOutDate, int rooms, int guests, String cityName) {
		
		HotelOfferSearch[] hotelOffers = null;
		
		LocalDate checkIn = null;
		LocalDate checkOut = null;
		
		if(checkInDate == null || checkInDate == "") {
			
			checkIn = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			checkInDate = checkIn.format(formatter).toString();
		}
		else {
			
			try {
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		
				checkIn = LocalDate.parse(checkInDate, formatter);
				
				DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				checkInDate = checkIn.format(newFormatter).toString();
				
			} catch(Exception e) {
				
				
			}
		}
		
		if(checkOutDate == null || checkOutDate == "") {
			
			checkOut = checkIn.plusDays(1);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			checkOutDate = checkOut.format(formatter).toString();
			
		}
		
		else {
			
			try {
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				
				checkOut = LocalDate.parse(checkOutDate, formatter);
				
				DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				checkOutDate = checkOut.format(newFormatter).toString();
				
			} catch(Exception e) {
				
			}
		}
		
								
		try {
			hotelOffers = amadeus.shopping.hotelOffersSearch.get(Params
					  .with("hotelIds", hotelId)
					  .and("adults", guests)
					  .and("checkInDate", checkInDate)
					  .and("checkOutDate", checkOutDate)
					  .and("roomQuantity", rooms)
					  .and("paymentPolicy", "NONE")
					  .and("bestRateOnly", true));				
			
		} catch (ResponseException e) {
			
			
			int statusCode = e.getResponse().getStatusCode();
			String errorMessage = "An unexpected error occurred";
	                
	        errorMessage = e.getDescription();

            switch (statusCode) {
                case 400:
                    throw new AmadeusBadRequestException(errorMessage, cityName, checkInDate, checkOutDate, rooms, guests);
                case 500:
                    throw new AmadeusInternalServerErrorException(errorMessage, cityName, checkInDate, checkOutDate, rooms, guests);
                default:
                    throw new AmadeusGeneralException(errorMessage, cityName, checkInDate, checkOutDate, rooms, guests);
            }
		}
		
		return hotelOffers;	

	}
}
