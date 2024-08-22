package com.xpert.TravellingAgency.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.repository.HotelBookingRepository;

@Service
public class HotelBookingDAO {
	
	@Autowired
	HotelBookingRepository hotelBookingRepository;
	
	public void saveBookingIntoDB(HotelBooking hotelBooking) {
		
		hotelBookingRepository.save(hotelBooking);
		
	}

}
