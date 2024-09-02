package com.xpert.TravellingAgency.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.DocumentException;
import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.service.EmailService;

@Controller
@RequestMapping("/booking-confirmation")
public class BookingConfirmationPageController {
	
	@Autowired
	HotelBookingDAO hotelBookingDAO;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public String getBookingConfirmation(
			@ModelAttribute("hotelBooking") HotelBooking hotelBooking,
			Model model) {
		
		try {
			emailService.sendBookingConfirmation(hotelBooking.getGuest()[0].getEmail(), hotelBooking);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("hotelBooking", hotelBooking);
		
		return "booking-confirmation";
		
	}

}
