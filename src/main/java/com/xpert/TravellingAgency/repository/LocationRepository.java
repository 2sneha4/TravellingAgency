package com.xpert.TravellingAgency.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
	
	Location getLocationByName(String name);

}
