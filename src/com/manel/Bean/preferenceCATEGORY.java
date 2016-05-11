package com.manel.Bean;

public class preferenceCATEGORY {
	
	public int idCAT ;
	public String category;
	public String name;
	public int location;
	public String city;
	public String country;
	public int latitude;
	public int longitude;
	public int iconWeather;
	public preferenceCATEGORY() {
		super();
		
	}

	@Override
	public String toString() {
		return "preferenceCATEGORY [idCAT=" + idCAT + ", category=" + category
				+ ", name=" + name + ", location=" + location + ", city="
				+ city + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", iconWeather=" + iconWeather
				+ ", street=" + street + ", zip=" + zip + "]";
	}

	public preferenceCATEGORY(int idCAT, String category, String name,
			int location, String city, String country, int latitude,
			int longitude, int iconWeather, String street, int zip) {
		super();
		this.idCAT = idCAT;
		this.category = category;
		this.name = name;
		this.location = location;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.iconWeather = iconWeather;
		this.street = street;
		this.zip = zip;
	}

	public int getIdCAT() {
		return idCAT;
	}

	public void setIdCAT(int idCAT) {
		this.idCAT = idCAT;
	}

	public int getIconWeather() {
		return iconWeather;
	}

	public void setIconWeather(int iconWeather) {
		this.iconWeather = iconWeather;
	}

	public int getIdidCAT() {
		return idCAT;
	}
	public void setidCAT(int idCAT) {
		this.idCAT = idCAT;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public String street ;
	public int zip ;
	
	
	
	

}
