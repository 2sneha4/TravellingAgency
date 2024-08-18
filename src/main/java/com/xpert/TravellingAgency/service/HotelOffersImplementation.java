package com.xpert.TravellingAgency.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOfferSearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpert.TravellingAgency.exceptions.AmadeusBadRequestException;
import com.xpert.TravellingAgency.exceptions.AmadeusGeneralException;
import com.xpert.TravellingAgency.exceptions.AmadeusInternalServerErrorException;

@Service
public class HotelOffersImplementation implements HotelOffers{
	
private Amadeus amadeus;
	
	public HotelOffersImplementation() {
		
		amadeus = Amadeus.builder("77LONFz3hRcuNpfja0mAkwVm939AFLye","hXNUPNUAQAcepciP").build();
	}
	
	@Override
	public HotelOfferSearch[] getHotelOffers(String hotelId, String checkInDate, String checkOutDate, int rooms, int guests) {
		
		HotelOfferSearch[] hotelOffers = null;
		
		LocalDate checkIn = null;
		LocalDate checkOut = null;
		
		if(checkInDate == null || checkInDate == "") {
			
			checkIn = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			checkInDate = checkIn.format(formatter).toString();
		}
		else {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	
			checkIn = LocalDate.parse(checkInDate, formatter);
			
			DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			checkInDate = checkIn.format(newFormatter).toString();
		}
		
		if(checkOutDate == null || checkOutDate == "") {
			
			checkOut = checkIn.plusDays(1);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			checkOutDate = checkOut.format(formatter).toString();
			
		}
		
		else {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
			
			checkOut = LocalDate.parse(checkOutDate, formatter);
			
			DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			checkOutDate = checkOut.format(newFormatter).toString();
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
			
			 try {
	                // Parse JSON error response using Jackson
	                ObjectMapper mapper = new ObjectMapper();
	                JsonNode errorResponse = mapper.readTree(e.getResponse().getBody());

	                if (errorResponse.has("title")) {
	                    errorMessage = errorResponse.get("title").asText();  // Extract the "title" field
	                }

	            } catch (IOException ioException) {
	                ioException.printStackTrace(); // Log JSON parsing error
	          }
			 
            switch (statusCode) {
                case 400:
                    throw new AmadeusBadRequestException(errorMessage);
                case 500:
                    throw new AmadeusInternalServerErrorException("Internal Server Error: Please try again later.");
                default:
                    throw new AmadeusGeneralException(errorMessage);
            }
		}
		
		return hotelOffers;
		
	}

}
