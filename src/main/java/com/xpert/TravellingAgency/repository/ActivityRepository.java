package com.xpert.TravellingAgency.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xpert.TravellingAgency.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>, PagingAndSortingRepository<Activity, String> {

}
