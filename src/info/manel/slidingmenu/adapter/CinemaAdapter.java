package info.manel.slidingmenu.adapter;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.NotifAlarm.example.R;
import com.manel.Bean.RSSFeed;
import com.utils.ImageManager;




public class CinemaAdapter extends BaseAdapter {

	    private Context context;
	    private ArrayList<RSSFeed> listeArticle = new ArrayList<RSSFeed>();
	    private LayoutInflater layoutInflater;
		Activity activity;
		public ImageManager imageManager;
		
		////public String description;

	    public CinemaAdapter(Activity activity, Context context, ArrayList<RSSFeed> listP)
	    
	    {
	        this.context = context;
	        this.listeArticle  = listP;
			layoutInflater = LayoutInflater.from(context);
			this.activity = activity;
			imageManager = new ImageManager(context);
			

	    }

	    @Override
	    public int getCount()
	    {
	        // Retourne le size de personne
	        return listeArticle.size();
	    }

	    @Override
	    public RSSFeed getItem(int position)
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
				
				convertView = layoutInflater.inflate(R.layout.my_cinema_item_adapter, null);
				holder = new ViewHolder();
//				holder.newsBackground = (ImageView) convertView.findViewById(R.id.my_news_background);
				
				holder.descriptionNews = (TextView) convertView.findViewById(R.id.description_cinema);
				
				holder.dateNews= (TextView) convertView.findViewById(R.id.date_cinema);

				
			
				

				//holder.url_image= (ImageView)convertView.findViewById(R.id.img_catalogue_article);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			// current Promo 
			RSSFeed article= listeArticle.get(position);
			
			 
			// Afficher les données dans les TextViews,..
			
			
			holder.descriptionNews.setText(article.getTitle());
			holder.dateNews.setText(article.getPubDate());
			String description=article.getDescription();
			if (!description.equalsIgnoreCase("")) {
				
				 //System.out.print("Found Index :" +description);
//			 String SubStr1 = new String("src='http");
//		        String SubStr2 = new String("' alt");
//
//		   
//		        System.out.print("Found Index :" );
//		        System.out.println( description.indexOf( SubStr1 ));
//		
//		        System.out.print("Found Index :" );
//		        System.out.println(description.indexOf( SubStr2 ));
//		        
		        
//		        System.out.println(description.substring(description.indexOf( SubStr1 )+5,description.indexOf( SubStr2 )));
//		        
	     //String image= description.substring(description.indexOf( SubStr1 )+5,description.indexOf( SubStr2 ));  
	    

	     String image="http://www.mdrc.org/sites/all/themes/mdrc/img/bg-news-hero.gif";
//	     imageManager.displayImage(image, activity, holder.newsBackground);
			}
			
			
			
			return convertView;
	    }
	    // Definir une classe de tous les composant d'item

	    
	    static class ViewHolder
	    {
	    	ImageView newsBackground;
	    	TextView descriptionNews;
	    	TextView dateNews;
	    	TextView  tempWeather;
	    	TextView  qtesortie; 	
	    	//TextView on_promo ;
	    	//TextView point_fidelite;
	    	//ImageView url_image;
	    }
	}


