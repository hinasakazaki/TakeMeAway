package edu.berkeley.cs160.hinasakazaki.prog3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class Related extends Activity {
	
	FindRelatedPics task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_related);
		Bundle extras = this.getIntent().getExtras();
		
		Location loc = (Location)extras.get("location");
		task = new FindRelatedPics();
		task.execute(loc);
		//process stuff -- do it on post-execute
		OkListener();
		
//		URL url = null;
//		
//		try {
//			url = new URL(task.get());
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Bitmap bmp = null;
//		try {
//			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		((ImageView)findViewById(R.id.relatedphoto)).setImageBitmap(bmp);
//		Toast.makeText(this, "This photo was taken here.", Toast.LENGTH_LONG).show();
		
	}
	
//	public void okClicked(View v){
//	Log.d("OKCLICKED!!", "");
//	Intent h = new Intent(this, MainActivity.class);
//	startActivity(h);
//}
	
	public void OkListener(){
		
		Button button = (Button) findViewById(R.id.okbtn);
		Log.d("OKCLICKED!!", "");
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(android.view.View arg0) {
				Log.d("OKCLICKED!!", "");
				// TODO Auto-generated method stub
				blahblah();
				
			}
		});
		
	}
	public void blahblah(){
		Intent h = new Intent(this, MainActivity.class);
		startActivity(h);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.related, menu);
		return true;
	}


	
	
	private class FindRelatedPics extends AsyncTask<Location, Void, Drawable> {
		private Context mContext;
		private final String API_KEY = "46ebc0637944e8b4b61112f0ff1de2f1";
		private final String API_SECRET = "9b18534a33725d65";
		private String PHOTO_URL;
		JSONObject jsonresult = null;
		
		@Override
		protected Drawable doInBackground(Location... params) {
			if (params != null) {
				double lon = (params[0]).getLongitude();
	            double lat = (params[0]).getLatitude();
	            Log.d("lat", ""+lat);
				
				final String request = "http://api.flickr.com/services/rest/?method=flickr.photos.search&" + "api_key=" + API_KEY + "&lon=" + lon + "&lat=" + lat  + "&radius=10" + "&per_page=" + "1" + "&format=json";
				HttpClient httpclient = new DefaultHttpClient();
	            HttpResponse response;
	            
	            
	            String responseString = null;
	            try {
	            	
	                response = httpclient.execute(new HttpGet(request));
	                Log.d("reponse??", ""+response);
	                StatusLine statusLine = response.getStatusLine();
	                Log.d("statusline??", ""+statusLine);
	                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                    ByteArrayOutputStream out = new ByteArrayOutputStream();
	                    response.getEntity().writeTo(out);
	                    out.close();
	                    responseString = out.toString();
	                } else{
	                    //Closes the connection.
	                    response.getEntity().getContent().close();
	                    throw new IOException(statusLine.getReasonPhrase());
	                }
	            } catch (ClientProtocolException e) {
	                //TODO Handle problems..
	            } catch (IOException e) {
	                //TODO Handle problems..
	            }
	            
	            if(responseString != null){
	            //now parse!! :D :D :D 
	            	Log.d("responseString???", ""+responseString);
	               
	            	try {
	            		
	            		responseString= responseString.substring(14, (responseString.length()-1));
	            		Log.d("new response string", ""+ responseString);
	    				jsonresult = (JSONObject) new JSONTokener(responseString).nextValue();
	    				Log.d("Did it token?", ""+ responseString);
	    				JSONObject photos = jsonresult.getJSONObject("photos");	
	    				
	    				if (photos.length() > 0){
	    					Log.d("ARE WE HERE??", "asfads");
	    					JSONArray photo =(photos.getJSONArray("photo"));
	    					JSONObject foto = photo.getJSONObject(0);
	    					
	    					
	    					
	    					String farm = foto.getString("farm");
	    				
	    					String server  = foto.getString("server");
	    					String secret = foto.getString("secret");
	    					String photo_id = foto.getString("id");
	    					
	    					
	    					PHOTO_URL = "http://farm" + farm + ".staticflickr.com/" + server + "/" + photo_id + "_" + secret + ".jpg";
	    					
	    					
	    					Log.d("FOTO URL!?!???", ""+PHOTO_URL);
	    					
	    					URL photo_url = null;
	    					try {
								photo_url = new URL(PHOTO_URL);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    					
	    					
	    					InputStream content = null;
	    					try {
	    						content = (InputStream) photo_url.getContent();
	    						
	    					} catch (IOException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    					
	    					Drawable display = Drawable.createFromStream(content, "src");
	    					
	    					return display;
	    				
	    				}
	    				
	    			} catch (JSONException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} 	
	            
			
	            }
	            
	           
			
			}
			return null;}
		
	
		@Override
	    protected void onPostExecute(Drawable display){
			
		 	ImageView iv = (ImageView) findViewById(R.id.relatedphoto);
		 	
		 	
		 	iv.setImageDrawable(display); 
		 	//Toast.makeText(mContext, "This phsdfoto was taken here.", Toast.LENGTH_SHORT).show();
	    }
		
		
	}
}