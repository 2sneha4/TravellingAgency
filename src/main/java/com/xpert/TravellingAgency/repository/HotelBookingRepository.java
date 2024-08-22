package com.xpert.TravellingAgency.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.HotelBooking;

@Repository
public interface HotelBookingRepository extends MongoRepository<HotelBooking, String>{

}
