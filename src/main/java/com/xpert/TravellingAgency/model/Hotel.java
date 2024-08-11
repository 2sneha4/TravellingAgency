package com.xpert.TravellingAgency.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Hotel object as returned by the Hotel List API.
 * @see com.amadeus.referencedata.locations.hotels.ByHotels#get()
 * @see com.amadeus.referencedata.locations.hotels.ByCity#get()
 * @see com.amadeus.referencedata.locations.hotels.ByGeocode#get()
 */
@Document(collection = "HotelsByCity")
public class Hotel {
	
  private String subtype;
  private String name;
  private String timeZoneName;
  
  @Field("iataCode")
  private String iataCode;
  
  private Address address;
  private GeoCode geoCode;
  private String googlePlaceId;
  private String openjetAirportId;
  private String uicCode;
  
  @Id
  private String hotelId;
  private String chainCode;
  private Distance distance;
  private String lastUpdate;
  
  

  public String getSubtype() {
	return subtype;
}

public void setSubtype(String subtype) {
	this.subtype = subtype;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getTimeZoneName() {
	return timeZoneName;
}

public void setTimeZoneName(String timeZoneName) {
	this.timeZoneName = timeZoneName;
}

public String getIataCode() {
	return iataCode;
}

public void setIataCode(String iataCode) {
	this.iataCode = iataCode;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public GeoCode getGeoCode() {
	return geoCode;
}

public void setGeoCode(GeoCode geoCode) {
	this.geoCode = geoCode;
}

public String getGooglePlaceId() {
	return googlePlaceId;
}

public void setGooglePlaceId(String googlePlaceId) {
	this.googlePlaceId = googlePlaceId;
}

public String getOpenjetAirportId() {
	return openjetAirportId;
}

public void setOpenjetAirportId(String openjetAirportId) {
	this.openjetAirportId = openjetAirportId;
}

public String getUicCode() {
	return uicCode;
}

public void setUicCode(String uicCode) {
	this.uicCode = uicCode;
}

public String getHotelId() {
	return hotelId;
}

public void setHotelId(String hotelId) {
	this.hotelId = hotelId;
}

public String getChainCode() {
	return chainCode;
}

public void setChainCode(String chainCode) {
	this.chainCode = chainCode;
}

public Distance getDistance() {
	return distance;
}

public void setDistance(Distance distance) {
	this.distance = distance;
}

public String getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
	this.lastUpdate = lastUpdate;
}

public Hotel() {}

public static class Address {
	
	@Field("category")
    private String category;
	@Field("lines")
    private String[] lines;
	@Field("postalCode")
    private String postalCode;
	@Field("countryCode")
    private String countryCode;
	@Field("cityName")
    private String cityName;
	@Field("stateCode")
    private String stateCode;
	@Field("postalBox")
    private String postalBox;
	@Field("text")
    private String text;
	@Field("state")
    private String state;
    
    public Address() {}

    public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String[] getLines() {
		return lines;
	}



	public void setLines(String[] lines) {
		this.lines = lines;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String getStateCode() {
		return stateCode;
	}



	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}



	public String getPostalBox() {
		return postalBox;
	}



	public void setPostalBox(String postalBox) {
		this.postalBox = postalBox;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}
}
	
	public static class GeoCode {

		  @Field("latitude")	
		  private float latitude;
		  @Field("longitude")
		  private float longitude;
		  
		  

		  public float getLatitude() {
				return latitude;
			}



			public void setLatitude(float latitude) {
				this.latitude = latitude;
			}



			public float getLongitude() {
				return longitude;
			}



			public void setLongitude(float longitude) {
				this.longitude = longitude;
			}



			public GeoCode() {}
	}
	public static class Distance {

	  @Field("unit")
	  private String unit;
	  @Field("value")
	  private double value;
	  @Field("displayValue")
	  private String displayValue;
	  @Field("isUnlimited")
	  private String isUnlimited;

	  
	  public String getUnit() {
			return unit;
		}


		public void setUnit(String unit) {
			this.unit = unit;
		}


		public double getValue() {
			return value;
		}


		public void setValue(double value) {
			this.value = value;
		}


		public String getDisplayValue() {
			return displayValue;
		}


		public void setDisplayValue(String displayValue) {
			this.displayValue = displayValue;
		}


		public String getIsUnlimited() {
			return isUnlimited;
		}


		public void setIsUnlimited(String isUnlimited) {
			this.isUnlimited = isUnlimited;
		}


		public Distance() {}
	}
}