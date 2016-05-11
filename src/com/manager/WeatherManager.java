package com.manager;

import java.util.ArrayList;

import com.manel.Bean.preferenceCATEGORY;

public class WeatherManager {

	 private ArrayList<preferenceCATEGORY> categories;
	 private static WeatherManager INSTANCE;

	    private WeatherManager() {
	    	categories = new ArrayList<preferenceCATEGORY>();
	    }
	    
	 public static WeatherManager getInstance() {
	        if (INSTANCE == null) {
	            INSTANCE = new WeatherManager();
	        }
	        return INSTANCE;
	    }
	 
	public ArrayList<preferenceCATEGORY> getListWeather()
	{
		return categories;
		
	}
	
	public void setWeather(preferenceCATEGORY category)
	{
		
		categories.clear();
		categories.add(category);
		
		
		
		
	}
}
