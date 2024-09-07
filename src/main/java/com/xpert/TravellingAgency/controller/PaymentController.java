package com.xpert.TravellingAgency.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.xpert.TravellingAgency.DAO.HotelBookingDAO;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.service.EmailService;
import com.xpert.TravellingAgency.service.PayPalService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
		
		@Autowired
	    private PayPalService payPalService;
		
		@Autowired
		HotelBookingDAO hotelBookingDAO;
		
		@Autowired
		EmailService emailService;
		
		@GetMapping
		public String paymentPage(@ModelAttribute HotelBooking hotelBooking,
				Model model) {
			
			
			hotelBooking.setBookingStatus("Booked");
			
			HotelBooking existingBooking = hotelBookingDAO.getHotelBookingByBookingId(hotelBooking.getBookingId());
			
			hotelBookingDAO.deleteExistingBooking(existingBooking.getBookingId());
			
			
			hotelBookingDAO.saveBookingIntoDB(hotelBooking);
			
			model.addAttribute("hotelBooking", hotelBooking);
			
			return "payment";
			
		}

	    @GetMapping("/pay")
	    public String pay(@ModelAttribute HotelBooking hotelBooking, Model model) throws PayPalRESTException {
	        String cancelUrl = "/payment/cancel";
	        String successUrl = "/payment/success";
	        
	        String currency = hotelBooking.getCurrency();
	        double priceInDouble = hotelBooking.getPrice();
	        
	        DecimalFormat df = new DecimalFormat("0.00");
	        
	        if(currency.equals("INR")) {
	        	priceInDouble /= 82.0;
	        	currency = "USD";
	        }
	        
	        // String price = Double.toString(priceInDouble);
	        String price = df.format(priceInDouble);
	        
	        Payment payment = payPalService.createPayment(
	        		price, 
	        		currency, 
	        		"paypal", 
	        		"sale", 
	        		"Paypal Payment",
	        		"http://localhost:8083" + cancelUrl, 
	        		"http://localhost:8083" + successUrl);
	        
	        System.out.println("Created Payment Response : " + payment.toJSON());
	        
	        if(payment.getState().equals("created")) {
	        	
	        	hotelBooking.setPaymentId(payment.getId());
	        	hotelBooking.setPaymentStatus("Paid");
	        	
	        	HotelBooking existingBooking = hotelBookingDAO.getHotelBookingByBookingId(hotelBooking.getBookingId());
	        	
	        	hotelBookingDAO.deleteExistingBooking(existingBooking.getBookingId());
	        	
	        	hotelBookingDAO.saveBookingIntoDB(hotelBooking);
	        	
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
