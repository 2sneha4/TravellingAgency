package com.xpert.TravellingAgency.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.xpert.TravellingAgency.model.HotelBooking;

public interface EmailService {

	void sendBookingConfirmation(String to, HotelBooking hotelBooking) throws DocumentException, IOException;

}
