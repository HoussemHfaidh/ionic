package info.manel.slidingmenu;

import info.manel.slidingmenu.adapter.CoffeAdapter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.NotifAlarm.example.GPSTracker;
import com.NotifAlarm.example.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.manel.Bean.FoursquareModel;

public class RestauCafeFragment extends Fragment {
	
	ArrayList<FoursquareModel> venuesList;

	// the foursquare client_id and the client_secret

	// ============== YOU SHOULD MAKE NEW KEYS ====================//
	final String CLIENT_ID = "44G3L35UGT2ZZFBOYNESG3V42BXIQKVY4I2GR32LAUG0G13C";
	final String CLIENT_SECRET = "Z1CNCEPGQYESVDMJ3C3WCRPP4A2D20P0A0RU403YPA1ECCQG";

	// we will need to take the latitude and the logntitude from a certain point

	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected double latitude, longitude;
	ArrayAdapter<String> myAdapter;

	// GPSTracker class
	GPSTracker gps;

	// Near BY ListView
	ListView lv;

	// Google Map
	private GoogleMap googleMap;

	// ArrayList & array list adapter to set listview
	ArrayList<FoursquareModel> _commentList = new ArrayList<FoursquareModel>();
	CoffeAdapter _nearByListAdapter;
	LinearLayout btnDisplay,mapCafe,listCafe;
	ImageView imgDisplay;
	TextView txtBtnDisplay;
	Boolean IsMaped =true;
	public RestauCafeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_restau_cafe, container, false);
         
	lv = (ListView) rootView.findViewById(R.id.listview);
	btnDisplay = (LinearLayout) rootView.findViewById(R.id.display);
	listCafe = (LinearLayout) rootView.findViewById(R.id.list_cafe);
	mapCafe = (LinearLayout) rootView.findViewById(R.id.map_cafe);
	imgDisplay = (ImageView) rootView.findViewById(R.id.img_display);
	txtBtnDisplay = (TextView) rootView.findViewById(R.id.txt_btn_display);
	
	btnDisplay.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (IsMaped) {
				listCafe.setVisibility(View.VISIBLE);
				mapCafe.setVisibility(View.GONE);
		        txtBtnDisplay.setText(getString(R.string.map));
		        imgDisplay.setImageDrawable(getResources().getDrawable(R.drawable.icon_carte));
				IsMaped=false;
			}else
			{
				listCafe.setVisibility(View.GONE);
				mapCafe.setVisibility(View.VISIBLE);
		        txtBtnDisplay.setText(getString(R.string.liste));
		        imgDisplay.setImageDrawable(getResources().getDrawable(R.drawable.icon_liste));
				IsMaped=true;
				
			}
			
		}
	});
	
	
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
//				FoursquareModel foursquareModel=(FoursquareModel)lv.getItemAtPosition(position);
//				
//				Manager.getInstance().setFoursquare(foursquareModel);
//				 Intent intent = new Intent(MainActivity.this,DetailActivity.class);
//				    
//				 startActivity(intent);
				
			}
		});
		
		gps = new GPSTracker(getActivity());

		// check if GPS enabled
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
		
			
			

		} else {

			gps.showSettingsAlert();
		}

		try {
			// Loading map
			initilizeMap();

			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(latitude, longitude), 13));

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(latitude, longitude)) // Sets the center
																// of the map to
																// location user
					.zoom(17) // Sets the zoom
					.build(); // Creates a CameraPosition from the builder
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			// googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// start the AsyncTask that makes the call for the venus search.

		new fourquare().execute();
		return rootView;
	}

	// ========================== function to load map. If map is not created it
		// will create it for you ==============================
		private void initilizeMap() {
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(
						R.id.map)).getMap();
	             
				
				
				
				// check if map is created successfully or not
				if (googleMap == null) {
					Toast.makeText(getActivity().getApplicationContext(),
							"Sorry! unable to create maps", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}

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
					venuesList = (ArrayList<FoursquareModel>) parseFoursquare(temp);

					// set the results to the list
					// and show them in the xml
					_nearByListAdapter = new CoffeAdapter(getActivity(),getActivity().getBaseContext(), 0,
							venuesList);

					for (FoursquareModel iterable_element : _commentList) {
						LatLng coordinates = new LatLng(iterable_element.getLatitude(),iterable_element.getLongtitude());
						googleMap.addMarker(new MarkerOptions().position(coordinates).draggable(
			    				false));
						
					}
					
					lv.setAdapter(_nearByListAdapter);

					for (FoursquareModel model : venuesList) {

						// create marker
						MarkerOptions marker = new MarkerOptions().position(
								new LatLng(model.getLatitude(), model
										.getLongtitude())).title(model.getName());

						// Changing marker icon
						marker.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

						// adding marker
						googleMap.addMarker(marker);
					}

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
	
}
