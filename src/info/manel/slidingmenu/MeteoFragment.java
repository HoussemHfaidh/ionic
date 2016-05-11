package info.manel.slidingmenu;

import java.util.ArrayList;

import info.manel.slidingmenu.adapter.WeatherAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.NotifAlarm.example.R;
import com.manager.WeatherManager;
import com.manel.Bean.preferenceCATEGORY;

public class MeteoFragment extends Fragment {
	private ListView listWeather;
	private WeatherAdapter weatherAdapter;
	private ArrayList<preferenceCATEGORY> categories = new ArrayList<preferenceCATEGORY>();
	public MeteoFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_meteo, container, false);
         
        
        listWeather=(ListView) rootView.findViewById(R.id.list_weather);
        try {
			
		
        weatherAdapter= new WeatherAdapter(getActivity(), getActivity(), WeatherManager.getInstance().getListWeather());
        listWeather.setAdapter(weatherAdapter);
        } catch (Exception e) {
			// TODO: handle exception
        	System.out.println("Exception fragment weather  ===>"+e.getMessage());
		}
        
        return rootView;
    }
}
