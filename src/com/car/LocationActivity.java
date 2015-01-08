package com.car;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.car.services.CallAPI;
import com.car.services.Gps;
import com.car.services.IAPIPostExecute;
import com.car.shared.Util;
import com.car.storage.LocationEntry;

@SuppressLint("NewApi")
public class LocationActivity extends ActionBarActivity implements com.car.services.IGpsListener, AutoCloseable{

	public static Util util = new Util();
	double[] locationCoordinates = null;
	
	public final static String TAG = LocationActivity.class.getSimpleName(); 
	double latitude = 0, longitude = 0;
	
	private LocationFragment locFragment = null;
	private Gps gps = null;

	private boolean isNull(Object object){
		return object == null;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gps = new Gps(this); 
		
		Intent intent = getIntent();
					
		//util.showMessage(String.format("Latitude: %f / Longitude : %f", locationCoordinates[0],locationCoordinates[1]),Toast.LENGTH_LONG);
		
		setContentView(R.layout.activity_location);
		if (savedInstanceState == null) {
			
		//	Bundle args = new Bundle();
		//	args.putDoubleArray(HomeActivity.INTENT_MSG_LOCATION, intent.getDoubleArrayExtra(HomeActivity.INTENT_MSG_LOCATION));
			
			locFragment = new LocationFragment();
		//	plFragment.setArguments(args);
			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, locFragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
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
	public void onLocationChange(double latitude, double longitude) {
		// TODO Auto-generated method stub
		
		this.latitude = latitude;
		this.longitude = longitude;
		
		locFragment.onLocationChange(latitude, longitude);
		if(gps != null){
			gps.stopGPS();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class LocationFragment extends Fragment implements android.view.View.OnClickListener,IAPIPostExecute
	{
		
		double latitude = 0,longitude = 0;
		
		EditText o_location_name;
		TextView l_address1, l_address2, l_address3, l_address4, l_address5;
		Button o_save,o_cancel;
		final String TAG = "Location";
		CallAPI callAPI = null;
		
		public LocationFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_location,
					container, false);

			double[] locationCoOrdinates = null;
			o_location_name = (EditText) rootView.findViewById(R.id.o_location_name);
			l_address1 = (TextView) rootView.findViewById(R.id.l_address1);
			l_address2 = (TextView) rootView.findViewById(R.id.l_address2);
			l_address3 = (TextView) rootView.findViewById(R.id.l_address3);
			l_address4 = (TextView) rootView.findViewById(R.id.l_address4);
			l_address5 = (TextView) rootView.findViewById(R.id.l_address5);
			o_save = (Button) rootView.findViewById(R.id.o_save);
			o_cancel = (Button) rootView.findViewById(R.id.o_cancel);
			
			o_save.setOnClickListener(this);
			o_cancel.setOnClickListener(this);
			
			Bundle inputArgs = this.getArguments();
			
			if(inputArgs!=null){
				locationCoOrdinates = inputArgs.getDoubleArray(HomeActivity.INTENT_MSG_LOCATION);
				
				latitude = locationCoOrdinates[0];
				longitude = locationCoOrdinates[1];
				
				if(Geocoder.isPresent()){
					Log.v(TAG, "Geocoder is present.");
					Geocoder geo = new Geocoder(this.getActivity().getApplicationContext());
					List<android.location.Address> addressList = null;
					
					try {
						addressList = geo.getFromLocation(latitude,longitude,1);
						
						android.location.Address address = addressList.get(0);
						if(address != null){
							
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Log.v(TAG, "Geocoder not available.");
				}				
				
			}	
			
			return rootView;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
				case R.id.o_save:{
						if(o_location_name.getText().toString().isEmpty()){
							util.showMessage("Enter Location Name to be saved.", Toast.LENGTH_SHORT);
							return;
						}
						else if(latitude == 0 || longitude == 0){
							util.showMessage("Location not set properly.", Toast.LENGTH_SHORT);
							return;
						}
						
						LocationEntry locationEntry = new LocationEntry(this.getActivity().getApplicationContext());
						com.car.model.Location loc = new com.car.model.Location();
						loc.setLocationName(o_location_name.getText().toString());
						loc.setLatitude(latitude);
						loc.setLongitude(longitude);
						loc.setAddress_1(l_address1.getText().toString());
						loc.setAddress_2(l_address2.getText().toString());
						loc.setAddress_3(l_address3.getText().toString());
						loc.setLocationDate(new Date().toString());
												
						if(locationEntry.createNewLocation(loc)){
							util.showMessage("New Location has been stored successfully", Toast.LENGTH_SHORT);
						}
						
						//loc.setCity(String.)
						
					break;
					}
				case R.id.o_cancel:{
					break;
				}
			}
			
			Intent intent=new Intent(getActivity(),HomeActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			
		}

		private String getStaticMapURL(){
			if(this.latitude == 0 && this.longitude == 0){return "";}
			
			String coOrdinates = String.format("%f,%f",this.latitude,this.longitude);
			
			return "https://maps.googleapis.com/maps/api/staticmap?center=" + coOrdinates
					+ "&zoom=13&size=400x200&maptype=roadmap" 
					+ "&markers=color:red%7Clabel:I%7C" + coOrdinates;
		
		}
		
		@SuppressLint("DefaultLocale")
		public void onLocationChange(double latitude, double longitude) {
			// TODO Auto-generated method stub
			this.latitude = latitude;
			this.longitude = longitude;
			final LinearLayout locationMap = (LinearLayout) findViewById(R.id.location_map);
			
			// Executing the REST API to get the address
			new CallAPI(this).execute(String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f", latitude,longitude));
			
			// Making the save button enabled
			o_save.setEnabled(true);
			
			Log.v(TAG, String.format("Location Changed. Latitude: %f/Longitude: %f",this.latitude,this.longitude));
			
			AsyncTask<Void, Void, Bitmap> setImageFromUrl = new AsyncTask<Void, Void, Bitmap>(){
				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bmp = null;
			        HttpClient httpclient = new DefaultHttpClient();   
			  
			        String url = getStaticMapURL();
			        if(url.intern().isEmpty()){Log.v(TAG, "Latitude and Longtitude not set.");  return null;}
			        
			        HttpGet request = new HttpGet(url); 
			    
			        Log.v("Location", url);
			        
			        InputStream in = null;
			        try {
			            in = httpclient.execute(request).getEntity().getContent();
			            bmp = BitmapFactory.decodeStream(in);
			            in.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        } 
					return bmp;
				}
				protected void onPostExecute(Bitmap bmp) {
					if (bmp!=null) {
												
						final ImageView iv = (ImageView) locationMap.findViewById(R.id.i_current_location);
						iv.setImageBitmap(bmp);
						
					}
				}
			};
			
			setImageFromUrl.execute();
			
		}

		@Override
		public void apiPostExecute(String result) {
			// TODO Auto-generated method stub
			try {
			
				String key = "";
				String lookUpKey = "";
				String address1 = "";
				String address2 = "";
				String address3 = "";
				String city = "";
				String state = "";
				String postalCode = "";
				
				JSONObject json = (new JSONObject(result).getJSONArray("results")).getJSONObject(0);
				JSONArray address = json.getJSONArray("address_components");
				lookUpKey = "short_name";
				
				for(int i=0;i<address.length();i++){
					JSONObject addr = address.getJSONObject(i); 
					
					//addr.getJSONArray("types").
					// Address 1
					for(int j=0;j<addr.getJSONArray("types").length();j++){
						key = addr.getJSONArray("types").getString(j);
						
						if(key.equalsIgnoreCase("street_number")){
							address1 = addr.getString(lookUpKey);
						}else if(key.equalsIgnoreCase("route")){
							address1 += addr.getString(lookUpKey);
						}else if(key.equalsIgnoreCase("locality")){
							city = addr.getString(lookUpKey);
						}else if(key.equalsIgnoreCase("administrative_area_level_1")){
							state = addr.getString(lookUpKey);
						}else if(key.equalsIgnoreCase("postal_code")){
							postalCode = addr.getString(lookUpKey);
						}
						
					}

				}
				
				l_address1.setHint("");
				l_address2.setHint("");
				l_address3.setHint("");
				l_address4.setHint("");
				l_address5.setHint("");
				
				l_address1.setText(address1);
				l_address2.setText(address2);
				l_address3.setText(String.format("%s %s %s", city,state,postalCode));
				
				Log.v(TAG, result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		if(!isNull(gps)){
			if(gps.isRunning()){
				gps.stopGPS();
				gps = null;
			}
		}
	}
	
}
