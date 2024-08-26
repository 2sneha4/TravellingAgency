package com.xpert.TravellingAgency.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amadeus.resources.HotelOfferSearch;
import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.DAO.LocationListDAO;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.service.HotelOffers;

@Controller
@RequestMapping("/hotel-details")
public class HotelDetailsPageController {
	
	
	@Autowired
	HotelOffers hotelOffers;
	
	@Autowired
	LocationListDAO locationListDAO;
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@GetMapping
	public String getHotelDetails(
			@RequestParam("id") String hotelId,
			@RequestParam(name = "checkindate", required = false) String checkInDate,
	        @RequestParam(name = "checkoutdate", required = false) String checkOutDate,
	        @RequestParam(name = "rooms", defaultValue = "1") int rooms,
	        @RequestParam(name = "guests", defaultValue = "1") int guests,
			Model model) {
				
		HotelOfferSearch[] hotelOfferSearch = hotelOffers.getHotelOffers(hotelId, checkInDate, checkOutDate, rooms, guests);
				
		HotelBooking hotelBooking = new HotelBooking();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        int randomNum = new Random().nextInt(900000) + 100000; // Generate a 6-digit random number
        
        hotelBooking.setBookingId("BKG" + timestamp + randomNum);
		
		hotelBooking.setHotelId(hotelId);
		hotelBooking.setHotelName(hotelOfferSearch[0].getHotel().getName());
		
//		hotelBooking.setCheckInDate(checkInDate);
//		hotelBooking.setCheckOutDate(checkOutDate);
		
		
		hotelBooking.setCityName(locationListDAO.getCityName(hotelOfferSearch[0].getHotel().getCityCode()));
		hotelBooking.setCityCode(hotelOfferSearch[0].getHotel().getCityCode());
		
//		hotelBooking.setOfferId(hotelOfferSearch[0].getOffers()[0].getId());
//		hotelBooking.setRoomDescription(hotelOfferSearch[0].getOffers()[0].getRoom().getDescription().getText());
//		hotelBooking.setRoomCategory(hotelOfferSearch[0].getOffers()[0].getRoom().getTypeEstimated().getCategory());
//		hotelBooking.setRoomBedType(hotelOfferSearch[0].getOffers()[0].getRoom().getTypeEstimated().getBedType());
//		hotelBooking.setPrice(Double.parseDouble(hotelOfferSearch[0].getOffers()[0].getPrice().getTotal()));
//		hotelBooking.setCurrency(hotelOfferSearch[0].getOffers()[0].getPrice().getCurrency());
		
		hotelBooking.setNoOfGuests(guests);
		hotelBooking.setNoOfRooms(rooms);
//		
		hotelBooking.setBookingStatus("Not Booked");
		hotelBooking.setPaymentStatus("Not Paid");
		
//		hotelBookingDAO.saveBookingIntoDB(hotelBooking);
		
		model.addAttribute("locationListDAO", locationListDAO);
		model.addAttribute("hotelBooking", hotelBooking);
		model.addAttribute("navigationPage", "hotel");
		
		model.addAttribute("hotelOffers", hotelOfferSearch[0].getOffers());
		model.addAttribute("hotelInfo", hotelOfferSearch[0].getHotel());
		
		return "hotel-details";
		
	}

}
