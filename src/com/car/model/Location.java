package com.car.model;

public class Location {
	
	private long locationID;
	
	private double latitude;
	private double longitude;
	private String locationName;
	private String address_1;
	private String address_2;
	private String address_3;
	private String city;
	private String state;
	private long zip;
	private String locationDate;
	
	public Location(){}
	
	public long getLocationID() {
		return locationID;
	}

	public void setLocationID(long l) {
		this.locationID = l;
	}
	
	public String getLocationDate() {
		return locationDate;
	}

	public void setLocationDate(String locationDate) {
		this.locationDate = locationDate;
	}
	
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
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getAddress_3() {
		return address_3;
	}
	public void setAddress_3(String address_3) {
		this.address_3 = address_3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZip() {
		return zip;
	}
	public void setZip(long zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", latitude=" + latitude
				+ ", longtitude=" + longitude + ", locationName="
				+ locationName + ", address_1=" + address_1 + ", address_2="
				+ address_2 + ", address_3=" + address_3 + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", locationDate="
				+ locationDate + "]";
	}
	
}
