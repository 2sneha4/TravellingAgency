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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.xpert.TravellingAgency.model.HotelBooking;
import com.xpert.TravellingAgency.model.HotelBooking.Guest;

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
	
	        String body = templateEngine.process("email", context);
	        
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
	        
	        PdfWriter writer = PdfWriter.getInstance(document, baos);
	        document.open();

	        // Define custom fonts
	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
	        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
	        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

	        // Add title
	        Paragraph title = new Paragraph("Booking Confirmation", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);
	        document.add(new Paragraph(" ")); // Add space

	        // Create a table for booking details
	        PdfPTable table = new PdfPTable(2); // 2 columns
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(10f);
	        table.setSpacingAfter(10f);

	        // Set column widths
	        float[] columnWidths = {2f, 3f};
	        table.setWidths(columnWidths);

	        // Add table header
	        PdfPCell cell = new PdfPCell(new Phrase("Booking Details", headerFont));
	        cell.setBackgroundColor(BaseColor.GRAY);
	        cell.setColspan(2);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setPadding(10f);
	        table.addCell(cell);

	        // Add booking details to the table
	        addTableRow(table, "Booking ID:", hotelBooking.getBookingId(), bodyFont);
	        addTableRow(table, "Hotel Name:", hotelBooking.getHotelName(), bodyFont);
	        addTableRow(table, "City:", hotelBooking.getCityName() + " (" + hotelBooking.getCityCode() + ")", bodyFont);
	        addTableRow(table, "Check-in Date:", hotelBooking.getCheckInDate(), bodyFont);
	        addTableRow(table, "Check-out Date:", hotelBooking.getCheckOutDate(), bodyFont);
	        addTableRow(table, "Number of Rooms:", String.valueOf(hotelBooking.getNoOfRooms()), bodyFont);
	        addTableRow(table, "Number of Guests:", String.valueOf(hotelBooking.getNoOfGuests()), bodyFont);
	        addTableRow(table, "Booking Status:", hotelBooking.getBookingStatus(), bodyFont);
	        addTableRow(table, "Room Description:", hotelBooking.getRoomDescription(), bodyFont);
	        addTableRow(table, "Room Category:", hotelBooking.getRoomCategory(), bodyFont);
	        addTableRow(table, "Bed Type:", hotelBooking.getRoomBedType(), bodyFont);
	        addTableRow(table, "Payment ID:", hotelBooking.getPaymentId(), bodyFont);
	        addTableRow(table, "Payment Status:", hotelBooking.getPaymentStatus(), bodyFont);
	        addTableRow(table, "Total Price:", hotelBooking.getCurrency() + " " + hotelBooking.getPrice(), bodyFont);

	        // Add Guest Details
	        PdfPCell guestHeader = new PdfPCell(new Phrase("Guest Details", headerFont));
	        guestHeader.setBackgroundColor(BaseColor.GRAY);
	        guestHeader.setColspan(2);
	        guestHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        guestHeader.setPadding(10f);
	        table.addCell(guestHeader);

	        // Loop through guests and add their details
	        for (Guest guest : hotelBooking.getGuest()) {
	            addTableRow(table, "Guest Name:", guest.getFirstName() + " " + guest.getLastName(), bodyFont);
	            addTableRow(table, "Email:", guest.getEmail(), bodyFont);
	            addTableRow(table, "Phone:", guest.getPhone(), bodyFont);
	            // Add an empty row for spacing between guests
	            table.addCell(new PdfPCell(new Phrase(" "))); // Empty cell
	            table.addCell(new PdfPCell(new Phrase(" "))); // Empty cell
	        }

	        // Add the table to the document
	        document.add(table);

	        // Footer
	        Paragraph footer = new Paragraph("Thank you for booking with us! We look forward to welcoming you.", bodyFont);
	        footer.setAlignment(Element.ALIGN_CENTER);
	        document.add(footer);

	        // Close document
	        document.close();

	        return new ByteArrayResource(baos.toByteArray());
	    }

	    private void addTableRow(PdfPTable table, String label, String value, Font font) {
	        PdfPCell labelCell = new PdfPCell(new Phrase(label, font));
	        labelCell.setPadding(10f);
	        labelCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        table.addCell(labelCell);

	        PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
	        valueCell.setPadding(10f);
	        table.addCell(valueCell);
	    }

}
