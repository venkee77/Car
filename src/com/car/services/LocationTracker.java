/*
package com.car.services;

import java.util.Timer;

import com.car.shared.Util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocationTracker implements LocationListener {

	static LocationTracker locationTracker = null;
	String TAG = null;
	
	double latitude;
	double longitude;
		
	private LocationTracker(){}
		
	Util util = new Util();
	
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

	// Singleton instance
	public static synchronized LocationTracker getInstance(){
		if(locationTracker == null){
			locationTracker = new LocationTracker();	
			locationTracker.TAG = locationTracker.getClass().getName();
		}
		return locationTracker;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		
	//	Log.v(TAG, String.format("Latitude %f, Longitude %f",location.getLatitude(),location.getLongitude()));
		
//		util.showMessage(String.format("Latitude : {0}, Longitude : {1}",Double.toString(latitude),Double.toString(longitude)), Toast.LENGTH_SHORT);
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
*/