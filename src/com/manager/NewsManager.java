package com.manager;

import java.util.ArrayList;
import java.util.List;

import com.manel.Bean.RSSFeed;
import com.manel.Bean.preferenceCATEGORY;

public class NewsManager {

	private ArrayList<RSSFeed> categories;
	 private static NewsManager INSTANCE;

	    private NewsManager() {
	    	categories = new ArrayList<RSSFeed>();
	    }
	    
	 public static NewsManager getInstance() {
	        if (INSTANCE == null) {
	            INSTANCE = new NewsManager();
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
