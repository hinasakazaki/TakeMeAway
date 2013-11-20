package edu.berkeley.cs160.hinasakazaki.prog3;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class Shoot extends Activity {
	

	public static double[] Counts = new double[8];
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	
	Location here;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoot);
        Log.d("HWERE THE FUCK AM I?", "");
      //get location
        
        String provider = "";

        LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
       
        if (loc.getAllProviders().size()> 0){
        	 Log.d("Is loc null?", ""+ loc.getAllProviders().get(0));
        	provider = loc.getAllProviders().get(0);
        	Log.d("Is get last known location working??", ""+loc.getLastKnownLocation(provider));
        	here = loc.getLastKnownLocation(provider); //still null
       }
        
        Intent intent =	new	Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File album = new File (Environment.getExternalStorageDirectory(), "TakeMeAway"); //gets the file where we save stuff
        album.mkdirs();
        File image = new File (album, "image" + here.getLongitude() + here.getLatitude() +  ".jpg");
        double lng = here.getLongitude();
        double lat = here.getLatitude();
        Counts[0] = lng;
        Counts[1] = lat;
        
        Uri fileUri = Uri.fromFile(image);
   
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);	
        
        
        
        //take photo
    	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    	
    	
    	
      
	}
	
	
	

	//find out where we are.
//	void checkLocation(View v){

//		LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
//		
//		String providerName = manager.getBestProvider(new Criteria(), true);
//		loc = manager.getLastKnownLocation(providerName);
//		Log.d("LOCATIONN", ""+ loc);
//	}
	
	
	@Override
	protected void onActivityResult(int	requestCode, int resultCode, Intent	data)	{
		Log.d("Is data null?", ""+ data);
		Log.d("LOCATIONN", ""+ here);
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			
	        if (resultCode == RESULT_OK) {
	        	Log.d("IS DATA NULL", ""+ data);
//	        	saveFile(data);
	        	
	        	Intent k = new Intent(this, Related.class);
	        	
	    		//send over the location... 
	    		k.putExtra("location", here);
	    		startActivity(k);	
	    		
	        	
	        	
	        } else if (resultCode == RESULT_CANCELED) {
	        	Log.d("cancel", "lol");
	        	startActivityForResult(super.getIntent(), CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	       
	        	Toast.makeText(this, "Photo save cancelled.", Toast.LENGTH_LONG).show();
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	}
	
//	private void saveFile(Intent intent){
//		
//		Log.d("Taken photo", "lala");
//		File file =	new	File(Environment.getExternalStorageDirectory(), "TakeMeAway");
//		file.mkdirs();
//		
//		Log.d("FileUri", ""+ fileUri);
//		
//		File photo = new File(file, "photo" + here.getLatitude() + here.getLongitude() + ".jpg");
//		
//		intent.putExtra(MediaStore.EXTRA_OUTPUT,  fileUri);
//		Bundle extras = intent.getExtras();
//		
//		Bitmap bmp = (Bitmap) extras.get("data"); //saves the photo in the file?
//    	Log.d("GAH", "lala");
//    	Toast.makeText(this, "Image saved to:\n" + intent.getData(), Toast.LENGTH_LONG).show();
//    	
//    	
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shoot, menu);
		return true;
	}
	
	
	//how to create a File or Uri location for a media file that can be used when invoking a device's camera
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
		
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "TakeMeAway");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("TakeMeAway", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	    	
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp +  ".jpg");
	        
	        Log.d("Is there storage???", ""+mediaFile);
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	

}
