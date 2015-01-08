/**
 * 
 */
package com.car.services;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

//import com.car.services;

/**
 * @author ItsMe
 *
 */
public class Gps {
	private IGpsListener mainActivity;
	private LocationListener mLocListener;
    private LocationManager mLocManager;
    private boolean isRunning;
	
	public Gps(IGpsListener main){
		this.mainActivity = main;
		
		mLocManager = (LocationManager)((Activity) this.mainActivity).getSystemService(Context.LOCATION_SERVICE);
		mLocListener = new LocationTracker();
		mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
		this.isRunning = true;
	}
	
	public boolean isRunning(){return this.isRunning;}
	
	public void resumeGPS(){
		if(!this.isRunning){
			mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
			this.isRunning = true;	
		}
	}
	
	public void stopGPS(){
		if(this.isRunning){
			mLocManager.removeUpdates(mLocListener);
			this.isRunning = false;
		}		
	}
	
	public class LocationTracker implements LocationListener {

		String TAG = null;
		
		double latitude;
		double longitude;
			
		public LocationTracker(){}
		
		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}		
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			this.latitude = location.getLatitude();
			this.longitude = location.getLongitude();
		
			Gps.this.mainActivity.onLocationChange(location.getLatitude(), location.getLongitude());
			
		//	Log.v(TAG, String.format("Latitude %f, Longitude %f",location.getLatitude(),location.getLongitude()));
			
//			util.showMessage(String.format("Latitude : {0}, Longitude : {1}",Double.toString(latitude),Double.toString(longitude)), Toast.LENGTH_SHORT);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}
	
}
