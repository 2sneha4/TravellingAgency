package com.xpert.TravellingAgency.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amadeus.resources.Hotel;
import com.xpert.TravellingAgency.repository.HotelRepository;

@Service
public class HotelListDAO {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public void saveHotelsInDB(Hotel[] hotels) {
		
		for(Hotel hotel : hotels) {
			
			com.xpert.TravellingAgency.model.Hotel myHotel = new com.xpert.TravellingAgency.model.Hotel();
			myHotel.setChainCode(hotel.getChainCode());
			myHotel.setHotelId(hotel.getHotelId());
			myHotel.setSubtype(hotel.getSubtype());
			myHotel.setName(hotel.getName());
			myHotel.setTimeZoneName(hotel.getTimeZoneName());
			myHotel.setIataCode(hotel.getIataCode());
			
			com.xpert.TravellingAgency.model.Hotel.Address address = new com.xpert.TravellingAgency.model.Hotel.Address();
			if(hotel.getAddress() != null) {
				address.setCategory(hotel.getAddress().getCategory());
				address.setCityName(hotel.getAddress().getCityName());
				address.setCountryCode(hotel.getAddress().getCountryCode());
				address.setLines(hotel.getAddress().getLines());
				address.setPostalBox(hotel.getAddress().getPostalBox());
				address.setPostalCode(hotel.getAddress().getPostalCode());
				address.setState(hotel.getAddress().getState());
				address.setStateCode(hotel.getAddress().getStateCode());
				address.setText(hotel.getAddress().getText());
			}
			myHotel.setAddress(address);
			
			com.xpert.TravellingAgency.model.Hotel.GeoCode geoCode = new com.xpert.TravellingAgency.model.Hotel.GeoCode();
			geoCode.setLatitude(hotel.getGeoCode().getLatitude());
			geoCode.setLongitude(hotel.getGeoCode().getLongitude());
			
			myHotel.setGeoCode(geoCode);
			
			myHotel.setGooglePlaceId(hotel.getGooglePlaceId());
			myHotel.setOpenjetAirportId(hotel.getOpenjetAirportId());
			myHotel.setUicCode(hotel.getUicCode());
			
			com.xpert.TravellingAgency.model.Hotel.Distance distance = new com.xpert.TravellingAgency.model.Hotel.Distance();
			if(hotel.getDistance() != null) {
				distance.setDisplayValue(hotel.getDistance().getDisplayValue());
				distance.setIsUnlimited(hotel.getDistance().getIsUnlimited());
				distance.setUnit(hotel.getDistance().getUnit());
				distance.setValue(hotel.getDistance().getValue());
			}
			
			myHotel.setDistance(distance);
			
			myHotel.setLastUpdate(hotel.getLastUpdate());
			
			hotelRepository.save(myHotel);
			
		}
	}
	
	public Page<com.xpert.TravellingAgency.model.Hotel> getHotelsByCity(String cityCode, Pageable pageable) {
		
		return hotelRepository.getHotelsByIataCode(cityCode, pageable);
	}

}
