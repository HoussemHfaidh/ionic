package com.manel.Bean;

public class PagesPreference {
	//PRreferences via le facebook
	public String category;
	public String name;
	public int location;
	public String city;
	public String country;
	public int latitude;
	public int longitude;
	public PagesPreference() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int idCATEGIRY ;
	public PagesPreference(int idCATEGIRY, String category, String name,
			int location, String city, String country, int latitude,
			int longitude) {
		super();
		this.idCATEGIRY = idCATEGIRY;
		this.category = category;
		this.name = name;
		this.location = location;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
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
	public int getIdCATEGIRY() {
		return idCATEGIRY;
	}
	public void setIdCATEGIRY(int idCATEGIRY) {
		this.idCATEGIRY = idCATEGIRY;
	}
	@Override
	public String toString() {
		return "PagesPreference [category=" + category + ", name=" + name
				+ ", location=" + location + ", city=" + city + ", country="
				+ country + ", latitude=" + latitude + ", longitude="
				+ longitude + ", idCATEGIRY=" + idCATEGIRY + ", getCategory()="
				+ getCategory() + ", getName()=" + getName()
				+ ", getLocation()=" + getLocation() + ", getCity()="
				+ getCity() + ", getCountry()=" + getCountry()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getIdCATEGIRY()=" + getIdCATEGIRY()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
