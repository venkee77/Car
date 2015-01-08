package com.car.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.car.db.CarContract;
import com.car.db.CarDBHelper;
import com.car.db.DatabaseManager;
import com.car.model.Location;

public class LocationEntry {
	private CarDBHelper _dbHelper;
	private DatabaseManager _dbManager;
	private SQLiteDatabase _db;
	
	public LocationEntry(android.content.Context context){
		_dbHelper = new CarDBHelper(context);
		DatabaseManager.initializeInstance(_dbHelper);
		_dbManager = DatabaseManager.getInstance();		
	}
	
	public boolean createNewLocation(Location location){
		ContentValues cValues = new ContentValues();
		boolean result = true;
				
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_LOCATION_NAME,location.getLocationName());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_ADDRESS_1,location.getAddress_1());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_ADDRESS_2,location.getAddress_2());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_ADDRESS_3,location.getAddress_3());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_CITY,location.getCity());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_STATE,location.getState());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_ZIP,location.getZip());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_LATITUDE,location.getLatitude());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_LONGTITUDE,location.getLongitude());
		cValues.put(com.car.db.CarContract.LocationEntry.COLUMN_NAME_LOCATIONDATE,location.getLocationDate());
		
		result = true;
		
		try{
			_db = _dbManager.openDatabase();
			if(deleteAllLocations()){
				location.setLocationID(_db.insert(com.car.db.CarContract.LocationEntry.TABLE_NAME, null, cValues));
			}
		}
		catch(android.database.sqlite.SQLiteException sqlLiteException){
			result = false;
			Log.v(String.format("%s: %s", this.getClass().getSimpleName(), this.getClass().getEnclosingMethod().getName()),sqlLiteException.getMessage());
			
		}finally{
			_db.close();
		}
		return result;
	}
	
	private boolean deleteAllLocations(){
		
		//Log.v(String.format("%s: %s", this.getClass().getSimpleName(), this.getClass().getEnclosingMethod().getName()),"Deleting all locations");
		
		boolean result = true;
		if(_db == null) {return false;}
		
		//Log.v(String.format("%s: %s", this.getClass().getSimpleName(), this.getClass().getEnclosingMethod().getName()),"Deleting all locations");
		
		try{
			_db.delete(com.car.db.CarContract.LocationEntry.TABLE_NAME, null, null);
		}catch(android.database.sqlite.SQLiteException sqlLiteException){
			result = false;
			Log.v(String.format("%s: %s", this.getClass().getSimpleName(), this.getClass().getEnclosingMethod().getName()),sqlLiteException.getMessage());
			
		}finally{
	
		}
		
		return result;
	}
	
}
