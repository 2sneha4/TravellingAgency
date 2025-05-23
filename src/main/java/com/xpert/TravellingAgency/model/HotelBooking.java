package com.xpert.TravellingAgency.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;

@Document(collection = "Bookings")
public class HotelBooking {
	
	@Id
	@Field("bookingId")
	private String bookingId;
	
	private String username;
	private String password;
	
	private Guest[] guest;
	private int noOfGuests;
	
	private String hotelId;
	private String hotelName;
	
	private String cityCode;
	private String cityName;
	
	private String offerId;
	private String checkInDate;
	private String checkOutDate;
	
	private String roomDescription;
	private String roomCategory;
	private String roomBedType;
	private int noOfRooms;
	
	private double price;
	private String currency;
	
	private String paymentId;
	
	private String bookingStatus;
	private String paymentStatus;
	
	public HotelBooking() {}



	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public int getNoOfGuests() {
		return noOfGuests;
	}



	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}



	public int getNoOfRooms() {
		return noOfRooms;
	}



	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}



	public String getBookingStatus() {
		return bookingStatus;
	}



	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}



	public String getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	public String getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}



	public String getBookingId() {
		return bookingId;
	}



	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}



	public Guest[] getGuest() {
		return guest;
	}



	public void setGuest(Guest[] guest) {
		this.guest = guest;
	}



	public String getHotelId() {
		return hotelId;
	}



	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getCityCode() {
		return cityCode;
	}



	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String getOfferId() {
		return offerId;
	}



	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}



	public String getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}



	public String getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	public String getRoomDescription() {
		return roomDescription;
	}



	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}



	public String getRoomCategory() {
		return roomCategory;
	}



	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}



	public String getRoomBedType() {
		return roomBedType;
	}



	public void setRoomBedType(String roomBedType) {
		this.roomBedType = roomBedType;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public static class Guest {
		
		private String guestId;
		private String phone;
	    private String email;
	    private String title;
	    private String firstName;
	    private String lastName;
	    
	    public Guest() {}

		public String getGuestId() {
			return guestId;
		}

		public void setGuestId(String guestId) {
			this.guestId = guestId;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    
	    
		
	}
	
}
