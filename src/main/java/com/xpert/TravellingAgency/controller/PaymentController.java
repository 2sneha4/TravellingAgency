package com.xpert.TravellingAgency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.service.PayPalService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
		
		@Autowired
	    private PayPalService payPalService;
		
		@Autowired
		HotelBookingDAO hotelBookingDAO;
		
		@GetMapping
		public String paymentPage(
				@ModelAttribute HotelBooking hotelBooking,
				Model model) {
			
			hotelBookingDAO.saveBookingIntoDB(hotelBooking);
			model.addAttribute("hotelBooking", hotelBooking);
			
			return "payment";
			
		}

	    @GetMapping("/pay")
	    public String pay(@ModelAttribute HotelBooking hotelBooking) throws PayPalRESTException {
	        String cancelUrl = "/payment/cancel";
	        String successUrl = "/payment/success";
	        
	        String price = Double.toString(1);
	        String currency = "USD";
	        
	        Payment payment = payPalService.createPayment(
	        		price, 
	        		currency, 
	        		"paypal", 
	        		"sale", 
	        		"Paypal Payment",
	        		"http://localhost:8083" + cancelUrl, 
	        		"http://localhost:8083" + successUrl);
	        
	        System.out.println("Created Payment Response : " + payment.toJSON());
	        
	        for(Links link : payment.getLinks()) {
	        	
	        	if(link.getRel().equals("approval_url"))
	        		return "redirect:" + link.getHref();
	        }
	        
	        return "redirect:/";
	    }

	    @GetMapping("/success")
	    public String success(@RequestParam String paymentId, @RequestParam String PayerID) throws PayPalRESTException {
	        
	    	Payment payment = payPalService.executePayment(paymentId, PayerID);
	        
	        System.out.println("Final JSON Response : " + payment.toJSON());
	        
	        if(payment.getState().equals("approved"))
	        	return "booking-confirmation";
	        
	        return "redirect:/";
	    }

	    @GetMapping("/cancel")
	    public String cancel() {
	        return "redirect:/";
	    }
		
}
