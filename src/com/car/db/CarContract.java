package com.car.db;

import android.provider.BaseColumns;

public final class CarContract {

	public CarContract(){}
	
	public static abstract class LocationEntry implements BaseColumns{
		public static final String TABLE_NAME = "LocationEntry";
        public static final String COLUMN_NAME_LOCATION_NAME = "locationname";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGTITUDE = "longtitude";
        public static final String COLUMN_NAME_ADDRESS_1 = "address1";
        public static final String COLUMN_NAME_ADDRESS_2 = "address2";
        public static final String COLUMN_NAME_ADDRESS_3 = "address3";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_ZIP = "zip";
        public static final String COLUMN_NAME_LOCATIONDATE = "locationdate";
       		
	}
	
}
