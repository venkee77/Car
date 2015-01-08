package com.car.shared;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Util {
	public static Context context;
	
	public enum DeviceType{
		Emulator(1),Phone(2);
		
		private int value;
		
		private DeviceType(int value){
			this.value = value;
		}		
	}
	
	public void showMessage(String message,int duration){
		Toast toast = Toast.makeText(context, message,duration);
		toast.show();
	}
	
	
	/*public Context getContext() {
		return context;
	}

	public synchronized void setContext(Context context) {
		if(this.context == null){
			Util.context = context;
		}
	}
*/
	public final class AppManager{
		public DeviceType device;
		
		public AppManager(){
			
		}
		
		public DeviceType getDevice() {
			return this.device;
		}

	}
	
	public class GPSAndNetwork{
		LocationManager locationManager = null;//(LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		
		public GPSAndNetwork() throws InvocationTargetException{
			locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		}
		
		public boolean isGPSAndNetworkEnabled(){
			return (isGPSEnabled() && isNetworkEnabled());
		}
		
		public boolean isGPSEnabled(){
			if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				return true;
			}else{
				return false;
			}
		}
		
		public boolean isNetworkEnabled(){
			if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
				return true;
			}else{
				return false;
			}
		}
		
		public void openGPSAndNetworkSettings(){
			Intent sIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			context.startActivity(sIntent);
		}
		
	}
	
		
}
