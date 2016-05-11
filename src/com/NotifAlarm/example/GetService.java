package com.NotifAlarm.example;

import info.manel.slidingmenu.MainActivity;
import info.manel.slidingmenu.adapter.CoffeAdapter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.manager.CinemaManager;
import com.manager.CoffeManager;
import com.manager.NewsManager;
import com.manager.WeatherManager;
import com.manel.Bean.FoursquareModel;
import com.manel.Bean.RSSFeed;
import com.manel.Bean.preferenceCATEGORY;
import com.parser.NewsFeedParser;
import com.webservice.AsyncHTTPRequestTask;



public class GetService extends Service {
	private NotificationManager mManager;

	private AlarmManager alarms;
	private PendingIntent alarmIntent;
	double latitude, longitude;
	private ConnectivityManager cnnxManager;
	private AsyncHTTPRequestTask mGetRestWS;
	final String CLIENT_ID = "44G3L35UGT2ZZFBOYNESG3V42BXIQKVY4I2GR32LAUG0G13C";
	final String CLIENT_SECRET = "Z1CNCEPGQYESVDMJ3C3WCRPP4A2D20P0A0RU403YPA1ECCQG";
	private static final String newsUrl =
            "http://www.mosaiquefm.net/fr/rss";
	
	 private static final String cinemaUrl =
	            "http://feeds.feedburner.com/jetsetmagazine_agenda_culturel_cinema?format=xml";
	private NewsFeedParser mNewsFeeder;
	private List<RSSFeed> mRssFeedList;
	// GPSTracker class
	GPSTracker gps;

	@Override
	public void onCreate() {
		super.onCreate();
		cnnxManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intentOnAlarm = new Intent(
				LaunchReceiver.ACTION_PULSE_SERVER_ALARM);
		alarmIntent = PendingIntent.getBroadcast(this, 0, intentOnAlarm, 0);
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		
		getData();
	}
	private void getData() {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		int millis = now.get(Calendar.MILLISECOND);
		
		 GPSTracker gps =  new GPSTracker(GetService.this);
		if(gps.canGetLocation()){
        	
      	  latitude = gps.getLatitude();
      	  longitude = gps.getLongitude();
      	 
      	// System.out.println("  latitude "+latitude +" longtitude "+ longtitude);
			}
		
		/**
		 * Lundi => Vendredi
		 */
		if((dayOfWeek>=2)&&(dayOfWeek<=6))
			
		{
			if((hour>=7)&&(minute>=0)&&(hour<10)&&(minute<=59))
			{
				
				getServiceA();
				getServiceB();
			}
			
			if((hour>=17)&&(minute>=0)&&(hour<17)&&(minute<=59))
			{
				
				getServiceC();
			}
		}
		
		/**
		 * Lundi => samedi
		 */
		if((dayOfWeek>=2)&&(dayOfWeek<=7))
			
		{
			
			
			if((hour>=12)&&(minute>=0)&&(hour<13)&&(minute<=59))
			{
				getServiceC();
			}
			
			
		}
		
		
		/**
		 *  Samedi
		 */
		
		
		if((hour>=22)&&(minute>=0)&&(hour<00)&&(minute<=59))
		{
			//System.out.println("ok");
			
			
			
		}
		getServiceB();
		
		//getServiceC();
		getServiceA();
		//System.out.println( "okkkkkkkkkkkkkkkkkk "+    year+" ,"+ month+"  "+ dayOfWeek+"  "+ hour+"  "+ minute);
	}

	private void getServiceB() {
		// TODO Auto-generated method stub
		System.out.println(" Matin  Méto");
		
		mGetRestWS = new GetWeather();
		mGetRestWS.execute((Void) null);
		
	}

	private void getServiceC() {
		// TODO Auto-generated method stub
		//System.out.println(" Café  + gps ");
		
		
		new fourquare().execute();
	}

	private void getServiceA() {
		// TODO Auto-generated method stub
	//	System.out.println(" Matin Actualité Méto");
		
		 new DoRssFeedTask().execute(newsUrl);
		 new CinemaTask().execute(cinemaUrl); 
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 
	 * @author HOUCEM
	 *
	 */
	
	private class fourquare extends AsyncTask<View, Void, String> {
		

		String temp;

		@Override
		protected String doInBackground(View... urls) {
			// make Call to the url
			temp = makeCall("https://api.foursquare.com/v2/venues/search?client_id="
					+ CLIENT_ID
					+ "&client_secret="
					+ CLIENT_SECRET
					+ "&v=20130815&ll="
					+ String.valueOf(latitude)
					+ ","
					+ String.valueOf(longitude)
					//+"&query=coffee" 
					+"&query=café"
					 );
			Log.e("Link ---- > ", temp);
			return "";
		}

		@Override
		protected void onPreExecute() {
			// we can start a progress bar here
		}

		@Override
		protected void onPostExecute(String result) {
			if (temp == null) {
				// we have an error to the call
				// we can also stop the progress bar
			} else {
				// all things went right

				// parseFoursquare venues search result
				ArrayList<FoursquareModel> venuesList = (ArrayList<FoursquareModel>) parseFoursquare(temp);
                CoffeManager.getInstance().setListCoffe(venuesList);
				// set the results to the list
				// and show them in the xml
//				_nearByListAdapter = new NearbyListAdapter(MainActivity.this,getBaseContext(), 0,
//						venuesList);
//
//				for (FoursquareModel iterable_element : _commentList) {
//					LatLng coordinates = new LatLng(iterable_element.getLatitude(),iterable_element.getLongtitude());
//					googleMap.addMarker(new MarkerOptions().position(coordinates).draggable(
//		    				false));
//					
//				}
//				
//				lv.setAdapter(_nearByListAdapter);

//				for (FoursquareModel model : venuesList) {
//
//					// create marker
//					MarkerOptions marker = new MarkerOptions().position(
//							new LatLng(model.getLatitude(), model
//									.getLongtitude())).title(model.getName());
//
//					// Changing marker icon
//					marker.icon(BitmapDescriptorFactory
//							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//
//					// adding marker
////					googleMap.addMarker(marker);
//				}

			}
		}
	}

	public static String makeCall(String url) {

		// string buffers the url
		StringBuffer buffer_string = new StringBuffer(url);
		String replyString = "";

		// instanciate an HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// instanciate an HttpGet
		HttpGet httpget = new HttpGet(buffer_string.toString());

		try {
			// get the responce of the httpclient execution of the url
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();

			// buffer input stream the result
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(20);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			// the result as a string is ready for parsing
			replyString = new String(baf.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// trim the whitespaces
		return replyString.trim();
	}

	private static ArrayList<FoursquareModel> parseFoursquare(
			final String response) {

		ArrayList<FoursquareModel> temp = new ArrayList<FoursquareModel>();
		try {

			// make an jsonObject in order to parse the response
			JSONObject jsonObject = new JSONObject(response);

			// make an jsonObject in order to parse the response
			if (jsonObject.has("response")) {
				if (jsonObject.getJSONObject("response").has("venues")) {
					JSONArray jsonArray = jsonObject.getJSONObject("response")
							.getJSONArray("venues");

					for (int i = 0; i < jsonArray.length(); i++) {
						FoursquareModel poi = new FoursquareModel();

						try {
							if (jsonArray.getJSONObject(i).has("name")) {
								poi.setName(jsonArray.getJSONObject(i)
										.getString("name"));

								// We will get only those locations which has
								// address
								if (jsonArray.getJSONObject(i).has("location")) {
									if (jsonArray.getJSONObject(i)
											.getJSONObject("location")
											.has("address")) {
										poi.setAddress(jsonArray
												.getJSONObject(i)
												.getJSONObject("location")
												.getString("address"));

										if (jsonArray.getJSONObject(i)
												.getJSONObject("location")
												.has("lat")) {
											poi.setLatitude(jsonArray
													.getJSONObject(i)
													.getJSONObject("location")
													.getString("lat"));
										}
										if (jsonArray.getJSONObject(i)
												.getJSONObject("location")
												.has("lng")) {
											poi.setLongtitude(jsonArray
													.getJSONObject(i)
													.getJSONObject("location")
													.getString("lng"));
										}

										if (jsonArray.getJSONObject(i)
												.getJSONObject("location")
												.has("city")) {
											poi.setCity(jsonArray
													.getJSONObject(i)
													.getJSONObject("location")
													.getString("city"));
										}
										if (jsonArray.getJSONObject(i)
												.getJSONObject("location")
												.has("country")) {
											poi.setCountry(jsonArray
													.getJSONObject(i)
													.getJSONObject("location")
													.getString("country"));
										}
										if (jsonArray.getJSONObject(i).has(
												"categories")) {
											if (jsonArray.getJSONObject(i)
													.getJSONArray("categories")
													.length() > 0) {
												if (jsonArray
														.getJSONObject(i)
														.getJSONArray(
																"categories")
														.getJSONObject(0)
														.has("name")) {
													poi.setCategory(jsonArray
															.getJSONObject(i)
															.getJSONArray(
																	"categories")
															.getJSONObject(0)
															.getString("name"));
												}
												if (jsonArray
														.getJSONObject(i)
														.getJSONArray(
																"categories")
														.getJSONObject(0)
														.has("id")) {
													poi.setCategoryID(jsonArray
															.getJSONObject(i)
															.getJSONArray(
																	"categories")
															.getJSONObject(0)
															.getString("id"));
												}
												if (jsonArray
														.getJSONObject(i)
														.getJSONArray(
																"categories")
														.getJSONObject(0)
														.has("icon")) {

													poi.setCategoryIcon(jsonArray
															.getJSONObject(i)
															.getJSONArray(
																	"categories")
															.getJSONObject(0)
															.getJSONObject(
																	"icon")
															.getString("prefix")
															+ "bg_32.png");
												}
											}
										}
										temp.add(poi);

									}
								}

							}
						} catch (Exception e) {

						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;

	}

	
	public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;

        @Override
        protected void onPreExecute() {
//            prog = new ProgressDialog(GetService.this);
//            prog.setMessage("Loading....");
//            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
                
               // System.out.println("urlVal"+urlVal);
            }
            mRssFeedList = mNewsFeeder.parse();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
           
        	//System.out.println("result  "+result);
        	
        	NewsManager.getInstance().setWeather(result);
        	int i=0;
        	for (RSSFeed rssFeed : result) {
        		i++;
        		//System.out.println("result  "+rssFeed.getDescription());
        		
        		
        		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        		
    			Notification notification = new Notification(R.drawable.iconnews, "Météo",System.currentTimeMillis());

    		    Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
    		    notificationIntent.putExtra("interface", 1);
    		   
    		   
    		 
            
    		    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
    		            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    		    PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,notificationIntent, 0);

    		    notification.setLatestEventInfo(getApplicationContext(),"name "+rssFeed.getTitle() ,rssFeed.getDescription(),intent);
//    	         
    		    notification.flags |= Notification.FLAG_AUTO_CANCEL;
    		    notificationManager.notify(0, notification);
			}
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
	
	
	
	public class CinemaTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;

        @Override
        protected void onPreExecute() {
//            prog = new ProgressDialog(GetService.this);
//            prog.setMessage("Loading....");
//            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
                
               // System.out.println("urlVal"+urlVal);
            }
            mRssFeedList = mNewsFeeder.parse();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
           
        	//System.out.println("result  "+result);
        	
        	CinemaManager.getInstance().setWeather(result);
        	int i=0;
        	for (RSSFeed rssFeed : result) {
        		i++;
        		//System.out.println("result  "+rssFeed.getDescription());
        		
        		
        		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        		
    			Notification notification = new Notification(R.drawable.ic_launcher, "Météo",System.currentTimeMillis());

    		    Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
    		    notificationIntent.putExtra("interface", 1);
    		   
    		   
    		 
            
    		    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
    		            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    		    PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,notificationIntent, 0);

    		    notification.setLatestEventInfo(getApplicationContext(),"name "+rssFeed.getTitle() ,rssFeed.getDescription(),intent);
//    	         
    		    notification.flags |= Notification.FLAG_AUTO_CANCEL;
    		    notificationManager.notify(0, notification);
			}
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
	public class GetWeather extends AsyncHTTPRequestTask {

		public GetWeather() {
			 GPSTracker gps =  new GPSTracker(GetService.this);
			 String latitude ="";
			 String longtitude="";
			if(gps.canGetLocation()){
		        	
	        	  latitude = gps.getLatitude()+"";
	        	 longtitude = gps.getLongitude()+"";
	        	 
	        	// System.out.println("  latitude "+latitude +" longtitude "+ longtitude);
				}
			//DataBaseHelper dbHelper = OpenHelperManager.getHelper(LoginActivity.this, DataBaseHelper.class);
			this.url = "http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longtitude+"&APPID=bdbb6f47c9760f614bc91c3b67489513";
			this.method = HttpGet.METHOD_NAME;
			this.params = null;
			
		}

		
		@SuppressWarnings("deprecation")
		@Override
		public void onPostExecute(String response) {
			
			try {
				
			
			//System.out.println("Weather Response    "+response);
			JSONObject jObj = new JSONObject(response);
			//System.out.println( " weather  " +jObj.get("main"));
			JSONObject  jsonObject=jObj.getJSONObject("main");
			//System.out.println( " weather  1  " +jsonObject.getDouble("temp"));
			double temp=jsonObject.getDouble("temp");
			double tempc=temp-273.15;
			//System.out.println( " weather  2  " +tempc);
			
			
			//System.out.println( " weather  3 " +jObj.getString("name"));
			
			JSONArray jArr = jObj.getJSONArray("weather");
			JSONObject JSONWeather = jArr.getJSONObject(0);
			
			
			
			//System.out.println( " weather  description " +JSONWeather.getString("description"));
			
			
			String iconWeather=JSONWeather.getString("icon");
			int iconNotification=R.drawable.ic_launcher;
			if(iconWeather.equalsIgnoreCase("01d"))
			{
				iconNotification=R.drawable.dclear;
			}else if(iconWeather.equalsIgnoreCase("01n"))
			{
				iconNotification=R.drawable.nclear;
				
			}else if(iconWeather.equalsIgnoreCase("02n"))
			{
				iconNotification=R.drawable.nfew;
				
			}else if(iconWeather.equalsIgnoreCase("02d"))
			{
				iconNotification=R.drawable.dfew;
				
			}
			else if(iconWeather.equalsIgnoreCase("03n"))
			{
				iconNotification=R.drawable.scattered;
				
			}else if(iconWeather.equalsIgnoreCase("03d"))
			{
				iconNotification=R.drawable.scattered;
				
			}
			else if(iconWeather.equalsIgnoreCase("04n"))
			{
				iconNotification=R.drawable.broken;
				
			}else if(iconWeather.equalsIgnoreCase("04d"))
			{
				iconNotification=R.drawable.broken;
				
			}
			else if(iconWeather.equalsIgnoreCase("09n"))
			{
				iconNotification=R.drawable.shower;
				
			}else if(iconWeather.equalsIgnoreCase("09d"))
			{
				iconNotification=R.drawable.shower;
				
			}
			else if(iconWeather.equalsIgnoreCase("10n"))
			{
				iconNotification=R.drawable.nrain;
				
			}else if(iconWeather.equalsIgnoreCase("10d"))
			{
				iconNotification=R.drawable.drain;
				
			}
			
			else if(iconWeather.equalsIgnoreCase("11n"))
			{
				iconNotification=R.drawable.thunderstorm;
				
			}else if(iconWeather.equalsIgnoreCase("11d"))
			{
				iconNotification=R.drawable.thunderstorm;
				
			}
			
			else if(iconWeather.equalsIgnoreCase("13n"))
			{
				iconNotification=R.drawable.snow;
				
			}else if(iconWeather.equalsIgnoreCase("13d"))
			{
				iconNotification=R.drawable.snow;
				
			}
			
			else if(iconWeather.equalsIgnoreCase("50n"))
			{
				iconNotification=R.drawable.nmist;
				
			}else if(iconWeather.equalsIgnoreCase("50d"))
			{
				iconNotification=R.drawable.dmist;
				
			}
				
				
				
				
//			
//			// NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//			 
//			 
//	            //Notification notify=new Notification(iconNotification,"tittle",System.currentTimeMillis());
//	           
//	            
//	            //PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), Test.class), 0);
//	            
//	            //notify.setLatestEventInfo(getApplicationContext(),"Werather "+jObj.getString("name") ,JSONWeather.getString("description") +" Temp"+tempc,pending);
//	            //notif.notify(0, notify);
			
			
			NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
			Notification notification1 = new Notification(iconNotification, "Météo",System.currentTimeMillis());

		    Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
		    notificationIntent.putExtra("interface", "2");
 		   
		    
		    
		    preferenceCATEGORY category= new preferenceCATEGORY();
        	category.setCategory("metio");
        	category.setCity(jObj.getString("name"));
        	category.setName(JSONWeather.getString("description"));
        	category.setidCAT(1);
        	category.setIconWeather(iconNotification);
        	
        	WeatherManager.getInstance().setWeather(category);
		    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
		            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

		    PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,notificationIntent, 0);

		    notification1.setLatestEventInfo(getApplicationContext(),"Werather "+jObj.getString("name") ,JSONWeather.getString("description") +" Temp"+tempc,intent);
//	         
		    notification1.flags |= Notification.FLAG_AUTO_CANCEL;
		    notificationManager.notify(0, notification1);
	       
			} catch (Exception e) {
				// TODO: handle exception
				
				System.out.println( " weather hhhh    " +e.getMessage());
			}
		}

		@Override
		protected void onCancelled() {
			mGetRestWS = null;
//			showProgress(false);

		}
	}


}
