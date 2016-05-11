package com.manager;

import java.util.ArrayList;
import java.util.List;

import com.manel.Bean.RSSFeed;
import com.manel.Bean.preferenceCATEGORY;

public class CinemaManager {

	private ArrayList<RSSFeed> categories;
	 private static CinemaManager INSTANCE;

	    private CinemaManager() {
	    	categories = new ArrayList<RSSFeed>();
	    }
	    
	 public static CinemaManager getInstance() {
	        if (INSTANCE == null) {
	            INSTANCE = new CinemaManager();
	        }
	        return INSTANCE;
	    }
	 
	public ArrayList<RSSFeed> getListWeather()
	{
		return categories;
		
	}
	
	public void setWeather(List<RSSFeed> category)
	{
		categories.clear();
		
		for (RSSFeed rssFeed : category) {
			
			categories.add(rssFeed);
		}
		
		
		
	}
	
}
