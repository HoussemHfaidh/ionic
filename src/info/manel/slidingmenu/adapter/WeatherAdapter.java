package info.manel.slidingmenu.adapter;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.NotifAlarm.example.R;
import com.manel.Bean.preferenceCATEGORY;




public class WeatherAdapter extends BaseAdapter {

	    private Context context;
	    private ArrayList<preferenceCATEGORY> listeArticle = new ArrayList<preferenceCATEGORY>();
	    private LayoutInflater layoutInflater;
		Activity activity;
		
		
		////public String description;

	    public WeatherAdapter(Activity activity, Context context, ArrayList<preferenceCATEGORY> listP)
	    
	    {
	        this.context = context;
	        this.listeArticle  = listP;
			layoutInflater = LayoutInflater.from(context);
			this.activity = activity;
			

	    }

	    @Override
	    public int getCount()
	    {
	        // Retourne le size de personne
	        return listeArticle.size();
	    }

	    @Override
	    public preferenceCATEGORY getItem(int position)
	    {
	    	// retourne l'item de position courant
	        return this.listeArticle.get(position);
	    }
	    
	    

	    @Override
	    public long getItemId(int position)
	    {
	        return position;
	    }
	  
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent)
	    {
	    	ViewHolder holder = null;
			if (convertView == null)
			{
				
				// Inflater sert à faire la connexion entre l'adapteur et le fichier xml d'un item
				// convertView represente le view d'item : Chaque composant hérite de classe mère View
				
				convertView = layoutInflater.inflate(R.layout.my_weather_item_adapter, null);
				holder = new ViewHolder();
				holder.iconWeather = (ImageView) convertView.findViewById(R.id.icon_weather);
				
				holder.descriptionWeather = (TextView) convertView.findViewById(R.id.description_weather);
				
				holder.nameCity= (TextView) convertView.findViewById(R.id.txt_name_city);

				holder.tempWeather = (TextView) convertView.findViewById(R.id.temp_weather);
			
				

				//holder.url_image= (ImageView)convertView.findViewById(R.id.img_catalogue_article);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			// current Promo 
			preferenceCATEGORY article= listeArticle.get(position);
			
			 
			// Afficher les données dans les TextViews,..
			
			
			holder.descriptionWeather.setText(article.getName());
			holder.nameCity.setText(article.getCity());
			
			holder.tempWeather.setText("20°");
			holder.iconWeather.setImageResource(article.getIconWeather());
			
			//holder.on_promo.setText(article.getEn_promo()+"");
			//holder.point_fidelite.setText(article.getAR_CodeFiscal()+"");
			//imageManager.displayImage(article.getAr_Image(), activity, holder.url_image);
			
			return convertView;
	    }
	    // Definir une classe de tous les composant d'item

	    
	    static class ViewHolder
	    {
	    	ImageView iconWeather;
	    	TextView descriptionWeather;
	    	TextView nameCity;
	    	TextView  tempWeather;
	    	TextView  qtesortie; 	
	    	//TextView on_promo ;
	    	//TextView point_fidelite;
	    	//ImageView url_image;
	    }
	}


