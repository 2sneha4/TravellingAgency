package com.xpert.TravellingAgency.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AmadeusBadRequestException.class)
    public ModelAndView handleBadRequestException(AmadeusBadRequestException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler(AmadeusInternalServerErrorException.class)
    public ModelAndView handleInternalServerError(AmadeusInternalServerErrorException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler(AmadeusGeneralException.class)
    public ModelAndView handleGeneralException(AmadeusGeneralException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("error");
    }
}
