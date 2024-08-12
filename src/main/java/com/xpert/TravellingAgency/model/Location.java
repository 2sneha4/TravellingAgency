package com.xpert.TravellingAgency.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Locations")
public class Location {
	
	  private String type;
	  private String subType;
	  private String name;
	  private String detailedName;
	  private String timeZoneOffset;
	  
	  @Id
	  private String iataCode;
	  
	  private GeoCode geoCode;
	  private Address address;
	  private Double relevance;

	  public Location() {}
	  
	  

	  public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getSubType() {
		return subType;
	}



	public void setSubType(String subType) {
		this.subType = subType;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDetailedName() {
		return detailedName;
	}



	public void setDetailedName(String detailedName) {
		this.detailedName = detailedName;
	}



	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}



	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}



	public String getIataCode() {
		return iataCode;
	}



	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}



	public GeoCode getGeoCode() {
		return geoCode;
	}



	public void setGeoCode(GeoCode geoCode) {
		this.geoCode = geoCode;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}


	public Double getRelevance() {
		return relevance;
	}



	public void setRelevance(Double relevance) {
		this.relevance = relevance;
	}



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

	 
	  
	  public static class Address {
	    private String cityName;
	    private String cityCode;
	    private String countryName;
	    private String countryCode;
	    private String regionCode;

	    public Address() {}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getCityCode() {
			return cityCode;
		}

		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getRegionCode() {
			return regionCode;
		}

		public void setRegionCode(String regionCode) {
			this.regionCode = regionCode;
		}
	    
	    
	  }

	 

}
