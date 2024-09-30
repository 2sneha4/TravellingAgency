package com.xpert.TravellingAgency.repository;

import com.xpert.TravellingAgency.model.HotelBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyBookingRepository extends MongoRepository<HotelBooking, String> {
    List<HotelBooking> findByUsername(String username);
}