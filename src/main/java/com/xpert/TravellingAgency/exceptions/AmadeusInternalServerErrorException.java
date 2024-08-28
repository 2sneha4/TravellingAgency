package com.xpert.TravellingAgency.exceptions;

public class AmadeusInternalServerErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	 
	private final String cityName;
    private final String checkInDate;
    private final String checkOutDate;
    private final int rooms;
    private final int guests;

	public AmadeusInternalServerErrorException(String message, String cityName, String checkInDate, String checkOutDate, int rooms, int guests) {
	        super(message);
	        this.cityName = cityName;
	        this.checkInDate = checkInDate;
	        this.checkOutDate = checkOutDate;
	        this.rooms = rooms;
	        this.guests = guests;
	    }

	public String getCityName() {
		return cityName;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public int getRooms() {
		return rooms;
	}

	public int getGuests() {
		return guests;
	}
	
	
}
