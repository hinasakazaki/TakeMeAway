package edu.berkeley.cs160.hinasakazaki.prog3;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class View extends Activity {

//	Location here;
//	
//	double lng = Shoot.Counts[0];
//    double lat = Shoot.Counts[1];
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
		//get location
//		 String provider = "";
//		 Drawable d = null;
//         LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
//        
//         
//         
//         if (loc.getAllProviders().size()> 0){
//         	 Log.d("Is loc null?", ""+ loc.getAllProviders().get(0));
//         provider = loc.getAllProviders().get(0);
//         Log.d("Is get last known location working??", ""+loc.getLastKnownLocation(provider));
//         here = loc.getLastKnownLocation(provider); 
//         }
//         
//         ImageView imageView = ((ImageView)findViewById(R.id.foto));
//
//         d = Drawable.createFromPath(Environment.getExternalStorageDirectory().toString() +  "/TakeMeAway/" + "image" + lng + lat + ".jpg");
//
//         imageView.setImageDrawable(d);

	}
	public void why(View v){
		Intent i = new Intent(this, Nope.class);
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}

}
