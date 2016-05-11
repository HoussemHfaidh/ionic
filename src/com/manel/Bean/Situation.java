package com.manel.Bean;

public class Situation {
	int id ;
	int daysWEEK ;
	int timeDAY;
	public int latitude;
	public int longitude;
	public Situation(int id, int dayswEEK, int timeDAY, int latitude,
			int longitude) {
		super();
		this.id = id;
		this.daysWEEK = daysWEEK;
		this.timeDAY = timeDAY;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Situation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDaysWEEK() {
		return daysWEEK;
	}
	public void setDaysWEEK(int daysWEEK) {
		this.daysWEEK = daysWEEK;
	}
	public int getTimeDAY() {
		return timeDAY;
	}
	public void setTimeDAY(int timeDAY) {
		this.timeDAY = timeDAY;
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
	@Override
	public String toString() {
		return "Situation [id=" + id + ", daysWEEK=" + daysWEEK + ", timeDAY="
				+ timeDAY + ", latitude=" + latitude + ", longitude="
				+ longitude + ", getId()=" + getId() + ", getDaysWEEK()="
				+ getDaysWEEK() + ", getTimeDAY()=" + getTimeDAY()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
