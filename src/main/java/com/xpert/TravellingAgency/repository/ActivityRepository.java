package com.xpert.TravellingAgency.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>, PagingAndSortingRepository<Activity, String> {

	@Query("{ 'geoCode' : { $geoWithin: { $centerSphere: [ [ ?0, ?1 ], ?2 ] } } }")
    Page<Activity> findAllByLocationWithinRadius(double latitude, double longitude, double radius, Pageable pageable);
}
