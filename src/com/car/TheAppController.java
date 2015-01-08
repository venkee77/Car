package com.car;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;

//import com.car.services.LocationTracker;
import com.car.shared.Util;
import com.car.shared.Util.DeviceType;

public class TheAppController extends Application {
	private static TheAppController _theAppController = null;
	private static Context _mContext = null;
	private static Util.DeviceType _deviceType = null;  
	private static LocationManager _locationManager = null;
	
	public static Util.DeviceType getDevicetype() {
		return _deviceType;
	}

	// To avoid instantiation
	protected TheAppController(){}
		
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	public synchronized static TheAppController getInstance(Context context){
		if(_theAppController == null){
			_theAppController = new TheAppController();
			_mContext = context;
			_deviceType = _theAppController.getDevice();
			_locationManager = (LocationManager)context.getSystemService("location");
			_theAppController.registerLocationServices();
		}
		
		return _theAppController;		
	}
	
	private boolean registerLocationServices(){
	//	com.car.services.LocationTracker locTracker = LocationTracker.getInstance();
		boolean result = false;
		
		if(_locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)){
		//	_locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locTracker);
			result = true;
		}
		
		if(_locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)){
		//	_locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locTracker);
			result = true;
		}	
		return result;
	}
	
	private Util.DeviceType getDevice(){
		
		TelephonyManager telmgr = (TelephonyManager) _mContext.getSystemService(Context.TELEPHONY_SERVICE);
		Log.e("Telephony Manager", telmgr.getDeviceId());
		if(telmgr.getDeviceId().equals("000000000000000")){
			return DeviceType.Emulator;
		}
		else{
			
			return DeviceType.Phone;
		}
	}
	
}
