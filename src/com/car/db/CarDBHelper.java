package com.car.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.car.db.CarContract.LocationEntry;

public class CarDBHelper extends SQLiteOpenHelper {

	 // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Car.db";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String REAL_TYPE = " REAL";
	
	private static final String COMMA_SEP = ",";
	
	private static final String SQL_CREATE_LOCATION_ENTRIES =
	    "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
	    LocationEntry._ID + " INTEGER PRIMARY KEY," +
	    LocationEntry.COLUMN_NAME_LOCATION_NAME + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_LONGTITUDE + REAL_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_ADDRESS_1 + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_ADDRESS_2 + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_ADDRESS_3 + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_CITY + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_STATE + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_ZIP + TEXT_TYPE + COMMA_SEP +
	    LocationEntry.COLUMN_NAME_LOCATIONDATE + TEXT_TYPE + " )";	
	
	private static final String SQL_DELETE_LOCATION_ENTRIES = "DROP TABLE IF EXISTS" + LocationEntry.TABLE_NAME;
	
	@SuppressLint("NewApi")
	public CarDBHelper(Context context) {
	
		super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_LOCATION_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_LOCATION_ENTRIES);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onUpgrade(db,oldVersion,newVersion);
	}

}
