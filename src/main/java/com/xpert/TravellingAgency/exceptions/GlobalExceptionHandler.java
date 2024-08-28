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
        model.addAttribute("errorCityName", ex.getCityName());
        model.addAttribute("errorCheckInDate", ex.getCheckInDate());
        model.addAttribute("errorCheckOutDate", ex.getCheckOutDate());
        model.addAttribute("errorRooms", ex.getRooms());
        model.addAttribute("errorGuests", ex.getGuests());
        
    	return "forward:/hotel"; // Forward to the current view
    }

    @ExceptionHandler(AmadeusInternalServerErrorException.class)
    public String handleInternalServerError(AmadeusInternalServerErrorException ex, 
    		Model model) {
    	
    	model.addAttribute("error", ex.getMessage());
        model.addAttribute("errorCityName", ex.getCityName());
        model.addAttribute("errorCheckInDate", ex.getCheckInDate());
        model.addAttribute("errorCheckOutDate", ex.getCheckOutDate());
        model.addAttribute("errorRooms", ex.getRooms());
        model.addAttribute("errorGuests", ex.getGuests());
        
        return "forward:/hotel"; // Forward to the current view
    }
    
    @ExceptionHandler(AmadeusGeneralException.class)
    public String handleGeneralException(AmadeusGeneralException ex, 
    		Model model) {
    	
    	model.addAttribute("error", ex.getMessage());
        model.addAttribute("errorCityName", ex.getCityName());
        model.addAttribute("errorCheckInDate", ex.getCheckInDate());
        model.addAttribute("errorCheckOutDate", ex.getCheckOutDate());
        model.addAttribute("errorRooms", ex.getRooms());
        model.addAttribute("errorGuests", ex.getGuests());
        
        return "forward:/hotel"; // Forward to the current view
    }
}
