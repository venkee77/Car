package com.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.car.services.Gps;
import com.car.shared.Util;
//import com.car.services.LocationTracker;

public class HomeActivity extends ActionBarActivity implements OnClickListener {
	
	public Util util = new Util();
	private Util.DeviceType deviceType;
	public TheAppController appController = null;	
//	private LocationTracker locTracker = null;
	
	ImageView img_currentLocation = null;
	Button locate = null;
	Button navigate = null;
	
	public final static String INTENT_MSG_LOCATION = "location";
		
	public Util.DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Util.DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.activity_home);
		
		Util.context = (this).getApplicationContext();
	
		locate = (Button) findViewById(R.id.o_locate);
		navigate = (Button) findViewById(R.id.o_navigate);
		
		locate.setOnClickListener(this);
		navigate.setOnClickListener(this);
		
		try{
			//locTracker = LocationTracker.getInstance();
		}catch(Exception ex){
			Log.e("Location Service Error", "Exception occurred while registering location services. " + ex.getMessage());
			util.showMessage("Location Services not available", Toast.LENGTH_SHORT);
		}
		
		// Calling the initialize method;
		try{
			//new TheAppController();
			TheAppController.getInstance(Util.context);
			this.deviceType = TheAppController.getDevicetype();
		    
		    if(this.deviceType.equals(Util.DeviceType.Phone)){
		    	util.showMessage("Phone is in use.", Toast.LENGTH_LONG);
		    }else{
		    	util.showMessage("Emulator is in use.", Toast.LENGTH_LONG);
		    }		    
			
		}catch(Exception ex){ex.printStackTrace();util.showMessage(ex.getMessage(), Toast.LENGTH_LONG);}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v){
		
		//double latitude = 0, longitude = 0;
		
		if(v.getId() == R.id.o_locate){
			try{
				
			//	util.showMessage(String.valueOf(GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext())), Toast.LENGTH_LONG);
			//	util.showMessage((GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext())==ConnectionResult.SUCCESS?"Available":"N/A"), Toast.LENGTH_LONG);
			//	util.showMessage(String.format("Latitude: %f / Longitude : %f", locTracker.getLatitude(),locTracker.getLongitude()),Toast.LENGTH_LONG);
								
				Intent locationIntent = new Intent(this,LocationActivity.class);
				//Bundle extras = locationIntent.getExtras();
			//	locationIntent.putExtra(INTENT_MSG_LOCATION, new double[]{locTracker.getLatitude(),locTracker.getLongitude()});				
				startActivity(locationIntent);
			}
			catch(Exception ex){
				util.showMessage(ex.toString(), Toast.LENGTH_SHORT);
			}
		}
	}
	
	@Override
	protected void onStop() {
	   // Log.w(TAG, "App stopped");

	    super.onStop();
	}

	@Override
	protected void onDestroy() {
	   // Log.w(TAG, "App destoryed");

	    super.onDestroy();
	}
	
	
}
	

