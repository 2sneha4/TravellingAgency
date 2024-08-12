package com.xpert.TravellingAgency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, PagingAndSortingRepository<Hotel, String> {
	
	Page<Hotel> getHotelsByIataCode(String iataCode, Pageable pageable);
 
}
