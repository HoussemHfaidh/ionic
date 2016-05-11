package info.manel.slidingmenu.adapter;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.manel.Bean.FoursquareModel;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.NotifAlarm.example.GPSTracker;
import com.NotifAlarm.example.R;
// List Adapter for NearBy ListView

public class CoffeAdapter extends ArrayAdapter<FoursquareModel> {

	ArrayList<FoursquareModel> mNearByList;
	Activity activity;

	public CoffeAdapter(Activity activity, Context context, int textViewResourceId,
			ArrayList<FoursquareModel> objects) {
		super(context, textViewResourceId, objects);
		this.mNearByList = objects;
		this.activity = activity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View single_row = inflater.inflate(R.layout.row_layout_coffe, null, true);
		TextView tv_name = (TextView) single_row.findViewById(R.id.tv_name);
		TextView tv_address = (TextView) single_row
				.findViewById(R.id.tv_address);
//		TextView tv_category = (TextView) single_row
//				.findViewById(R.id.tv_category);
		
		TextView distanceNumber= (TextView) single_row
				.findViewById(R.id.txt_distance);
		

		tv_name.setText(mNearByList.get(position).getName());
		tv_address.setText(mNearByList.get(position).getAddress());
//		tv_category.setText(mNearByList.get(position).getCategory());
		
		 GPSTracker gps;
         gps = new GPSTracker(activity.getApplicationContext());
         int distance=0;
         if(gps.canGetLocation()){
	        	
	        	double latitude = gps.getLatitude();
	        	double longitude = gps.getLongitude();
//	        	Toast.makeText(
//						SelectStoreActivity.this.getApplicationContext(),
//						"longtitude 2 ."+longitude +"      latitude 2     "+latitude,
//						Toast.LENGTH_SHORT).show();
//	        	
	        	Location locationA = new Location("point A");     
	       		locationA.setLatitude(mNearByList.get(position).getLatitude()); 
	       		locationA.setLongitude(mNearByList.get(position).getLongtitude());
	       		Location locationB = new Location("point B");
	       		locationB.setLatitude(latitude); 
	       	    locationB.setLongitude(longitude);
	       		 distance = (int) locationA.distanceTo(locationB) ;
	       		 
	       		
	       		
	       		
	       		 
         }
         double kilometers = distance * 0.001;
         NumberFormat formatter = new DecimalFormat("#0.00");     
    	 if (distance>999) {
    		 distanceNumber.setText(formatter.format(kilometers) + " KM");
		}else
		{
			distanceNumber.setText(distance + " M");
		}
         
		
		return single_row;
	}

}
