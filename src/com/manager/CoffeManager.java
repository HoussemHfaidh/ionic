package com.manager;

import java.util.ArrayList;
import java.util.List;

import com.manel.Bean.FoursquareModel;
import com.manel.Bean.RSSFeed;

public class CoffeManager {

	private ArrayList<FoursquareModel> categories;
	 private static CoffeManager INSTANCE;

	    private CoffeManager() {
	    	categories = new ArrayList<FoursquareModel>();
	    }
	    
	 public static CoffeManager getInstance() {
	        if (INSTANCE == null) {
	            INSTANCE = new CoffeManager();
	        }
	        return INSTANCE;
	    }
	 
	public ArrayList<FoursquareModel> getListCoffe()
	{
		return categories;
		
	}
	
	public void setListCoffe(List<FoursquareModel> category)
	{
		
		System.out.println("setListCinema   ========>  "+category);
		categories.clear();
		
		for (FoursquareModel rssFeed : category) {
			
			categories.add(rssFeed);
		}
		
		
		
	}
	
}
