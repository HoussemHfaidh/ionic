package info.manel.slidingmenu;

import info.manel.slidingmenu.adapter.CinemaAdapter;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.NotifAlarm.example.R;
import com.manager.CinemaManager;
import com.manel.Bean.RSSFeed;

public class LieuxinteretFragment extends Fragment {
	
	
	
	private ListView listNews;
	private CinemaAdapter newsAdapter;
	private ArrayList<RSSFeed> categories = new ArrayList<RSSFeed>();
	public LieuxinteretFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_actualites, container, false);
         
        listNews=(ListView) rootView.findViewById(R.id.list_news);
        try {
			
		
        	newsAdapter= new CinemaAdapter(getActivity(), getActivity(), CinemaManager.getInstance().getListWeather());
        	listNews.setAdapter(newsAdapter);
        } catch (Exception e) {
			// TODO: handle exception
        	System.out.println("Exception fragment weather  ===>"+e.getMessage());
		}
        
        return rootView;
    }
}

