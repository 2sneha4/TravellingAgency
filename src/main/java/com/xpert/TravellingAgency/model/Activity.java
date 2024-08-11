package com.xpert.TravellingAgency.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * An Activity object as returned by the Tours and Activities API.
 * @see com.amadeus.shopping.Activity#get()
 */

@Document(collection = "Activity")
public class Activity {
  private String type;
  
  @Id
  private String id;
  
  private String name;
  private String shortDescription;
  private String description;
  
  @Field("geoCode")
  private GeoCode geoCode;
  
  private String rating;
  private String bookingLink;
  private String minimumDuration;
  
  private ElementaryPrice price;
  
  private String[] pictures;

  public Activity() {}
  

  public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getShortDescription() {
	return shortDescription;
}

public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public GeoCode getGeoCode() {
	return geoCode;
}

public void setGeoCode(GeoCode geoCode) {
	this.geoCode = geoCode;
}

public String getRating() {
	return rating;
}

public void setRating(String rating) {
	this.rating = rating;
}

public String getBookingLink() {
	return bookingLink;
}

public void setBookingLink(String bookingLink) {
	this.bookingLink = bookingLink;
}

public String getMinimumDuration() {
	return minimumDuration;
}

public void setMinimumDuration(String minimumDuration) {
	this.minimumDuration = minimumDuration;
}

public ElementaryPrice getPrice() {
	return price;
}

public void setPrice(ElementaryPrice price) {
	this.price = price;
}

public String[] getPictures() {
	return pictures;
}

public void setPictures(String[] pictures) {
	this.pictures = pictures;
}


/**
   * An Activity-related object as returned by the Tours and Activities API.
   * @see com.amadeus.shopping.Activity#get()
   */
  public static class GeoCode {
    private double latitude;
    private double longitude;

    public GeoCode() {}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
    
    
  }

  public static class ElementaryPrice {
    private String amount;
    private String currencyCode;

    public ElementaryPrice() {}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
    
    
  }
}