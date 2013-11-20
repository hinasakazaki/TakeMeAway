package edu.berkeley.cs160.hinasakazaki.prog3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void getShoot(View v) {
		Intent j = new Intent(this, Shoot.class);
		startActivity(j);
	}
	
	public void getView(View v) {
		 Log.d("HWERE THE FUCK AM I?", "");
		Intent i = new Intent(this, Nope.class);
		startActivity(i);
	}

}
