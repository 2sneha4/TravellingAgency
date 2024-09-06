package com.xpert.TravellingAgency.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AmadeusBadRequestException.class)
    public String handleBadRequestException(AmadeusBadRequestException ex, 
    		Model model) {
    	
    	model.addAttribute("error", ex.getMessage());
        
		 return "forward:/hotel?city=" + ex.getCityName() +
		           "&checkindate=" + ex.getCheckInDate() +
		           "&checkoutdate=" + ex.getCheckOutDate() +
		           "&rooms=" + ex.getRooms() +
		           "&guests=" + ex.getGuests();
    }

    @ExceptionHandler(AmadeusInternalServerErrorException.class)
    public String handleInternalServerError(AmadeusInternalServerErrorException ex, 
    		Model model) {
    	
    	model.addAttribute("error", ex.getMessage());
        
		 return "forward:/hotel?city=" + ex.getCityName() +
		           "&checkindate=" + ex.getCheckInDate() +
		           "&checkoutdate=" + ex.getCheckOutDate() +
		           "&rooms=" + ex.getRooms() +
		           "&guests=" + ex.getGuests();
    }
    
    @ExceptionHandler(AmadeusGeneralException.class)
    public String handleGeneralException(AmadeusGeneralException ex, 
    		Model model) {
    	
    	model.addAttribute("error", ex.getMessage());
    
		 return "forward:/hotel?city=" + ex.getCityName() +
		           "&checkindate=" + ex.getCheckInDate() +
		           "&checkoutdate=" + ex.getCheckOutDate() +
		           "&rooms=" + ex.getRooms() +
		           "&guests=" + ex.getGuests();
    }
}
