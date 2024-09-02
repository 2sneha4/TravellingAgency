package com.xpert.TravellingAgency.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.xpert.TravellingAgency.model.HotelBooking;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImplementation implements EmailService{
	
	 	@Autowired
	    private JavaMailSender javaMailSender;
	
	    @Autowired
	    private SpringTemplateEngine templateEngine;
	
	    @Override
	    public void sendBookingConfirmation(String to, HotelBooking hotelBooking) throws DocumentException, IOException {
	    	
	        Context context = new Context();
	        context.setVariable("booking", hotelBooking);
	
	        String body = templateEngine.process("booking-confirmation", context);
	        
	        // Generate PDF attachment
			ByteArrayResource pdfResource = createPdf(hotelBooking);
				
	
	        try {
		        MimeMessage message = javaMailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message, true);
		        helper.setTo(to);
		        helper.setSubject("Your Booking Confirmation");
		        helper.setText(body, true); // true indicates HTML
		        
		     // Add PDF as attachment
		        helper.addAttachment("BookingConfirmation.pdf", pdfResource, "application/pdf");
		
		        javaMailSender.send(message);
		        
	        } catch (MessagingException e) {
	        	e.printStackTrace();
	        }
	}
	    
	    public ByteArrayResource createPdf(HotelBooking hotelBooking) throws DocumentException, IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        Document document = new Document();
	        
	        PdfWriter.getInstance(document, baos);
	        document.open();
	        
	        document.add(new Paragraph("Booking Confirmation"));
	        document.add(new Paragraph("Booking ID: " + hotelBooking.getBookingId()));
	        document.add(new Paragraph("Hotel Name: " + hotelBooking.getHotelName()));
	        document.add(new Paragraph("City: " + hotelBooking.getCityName() + " (" + hotelBooking.getCityCode() + ")"));
	        document.add(new Paragraph("Check-in Date: " + hotelBooking.getCheckInDate()));
	        document.add(new Paragraph("Check-out Date: " + hotelBooking.getCheckOutDate()));
	        document.add(new Paragraph("Number of Rooms: " + hotelBooking.getNoOfRooms()));
	        document.add(new Paragraph("Number of Guests: " + hotelBooking.getNoOfGuests()));
	        document.add(new Paragraph("Booking Status: " + hotelBooking.getBookingStatus()));
	        document.add(new Paragraph("Room Description: " + hotelBooking.getRoomDescription()));
	        document.add(new Paragraph("Room Category: " + hotelBooking.getRoomCategory()));
	        document.add(new Paragraph("Bed Type: " + hotelBooking.getRoomBedType()));
	        document.add(new Paragraph("Payment ID: " + hotelBooking.getPaymentId()));
	        document.add(new Paragraph("Payment Status: " + hotelBooking.getPaymentStatus()));
	        document.add(new Paragraph("Total Price: " + hotelBooking.getCurrency() + " " + hotelBooking.getPrice()));
	        
	        document.close();
	        
	        return new ByteArrayResource(baos.toByteArray());
	    }

}
