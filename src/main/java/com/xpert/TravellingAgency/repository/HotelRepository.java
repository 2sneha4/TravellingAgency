package com.xpert.TravellingAgency.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>{
	
	List<Hotel> getHotelsByIataCode(String iataCode);
 
}
