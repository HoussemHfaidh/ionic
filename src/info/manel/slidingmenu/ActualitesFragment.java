package info.manel.slidingmenu;

import java.util.ArrayList;

import info.manel.slidingmenu.adapter.NewsAdapter;
import info.manel.slidingmenu.adapter.WeatherAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.NotifAlarm.example.R;
import com.manager.NewsManager;
import com.manager.WeatherManager;
import com.manel.Bean.RSSFeed;
import com.manel.Bean.preferenceCATEGORY;

public class ActualitesFragment extends Fragment {
	private ListView listNews;
	private NewsAdapter newsAdapter;
	private ArrayList<RSSFeed> categories = new ArrayList<RSSFeed>();
	public ActualitesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_actualites, container, false);
         
        listNews=(ListView) rootView.findViewById(R.id.list_news);
        try {
			
		
        	newsAdapter= new NewsAdapter(getActivity(), getActivity(), NewsManager.getInstance().getListWeather());
        	listNews.setAdapter(newsAdapter);
        } catch (Exception e) {
			// TODO: handle exception
        	System.out.println("Exception fragment weather  ===>"+e.getMessage());
		}
        
        return rootView;
    }
}
