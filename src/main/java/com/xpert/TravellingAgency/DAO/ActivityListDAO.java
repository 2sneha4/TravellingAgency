package com.xpert.TravellingAgency.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amadeus.resources.Activity;
import com.xpert.TravellingAgency.repository.ActivityRepository;

@Service
public class ActivityListDAO {
	
	@Autowired
	ActivityRepository activityRepository;
	
	public void saveActivitiesInDB(Activity[] activities) {
		
		for(Activity activity : activities) {
		
			com.xpert.TravellingAgency.model.Activity myActivity = new com.xpert.TravellingAgency.model.Activity();
			
			myActivity.setBookingLink(activity.getBookingLink());
			myActivity.setDescription(activity.getDescription());
			
			com.xpert.TravellingAgency.model.Activity.GeoCode geoCode= new com.xpert.TravellingAgency.model.Activity.GeoCode();
			
			if(activity.getGeoCode() !=null) {
				geoCode.setLatitude(activity.getGeoCode().getLatitude());
				geoCode.setLongitude(activity.getGeoCode().getLongitude());
			}
			myActivity.setGeoCode(geoCode);
			
			myActivity.setId(activity.getId());
			myActivity.setMinimumDuration(activity.getMinimumDuration());
			myActivity.setName(activity.getName());
			myActivity.setPictures(activity.getPictures());
			
			com.xpert.TravellingAgency.model.Activity.ElementaryPrice price = new com.xpert.TravellingAgency.model.Activity.ElementaryPrice();
			
			if(activity.getPrice() != null) {
				price.setAmount(activity.getPrice().getAmount());
				price.setCurrencyCode(activity.getPrice().getCurrencyCode());
			}
			myActivity.setPrice(price);
			
			myActivity.setRating(activity.getRating());
			myActivity.setShortDescription(activity.getShortDescription());
			myActivity.setType(activity.getType());
			
			activityRepository.save(myActivity);
		
		}
	}
	
	public Page<com.xpert.TravellingAgency.model.Activity> getAllActivities(Pageable pageable) {
		return activityRepository.findAll(pageable);
	}

}
