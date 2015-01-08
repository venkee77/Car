package com.car.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import com.car.services.IAPIPostExecute;

public class CallAPI extends AsyncTask<String, Void, String> {
	
	IAPIPostExecute apiPostExecute;
	String url = "";
		
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CallAPI(IAPIPostExecute main){
		apiPostExecute = main;
	
	}
	
	@Override
	protected String doInBackground(String... URLs) {
		// TODO Auto-generated method stub
		return GET(URLs[0]);
	}
	
	 @Override
     protected void onPostExecute(String result) {
		 if(apiPostExecute != null){
			 apiPostExecute.apiPostExecute(result);
		 }
	 }

	private String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	    }
	
	private String GET(String url){
	     InputStream inputStream = null;
	     String result = "";
	
	     try {
	
	         // create HttpClient
	         HttpClient httpclient = new DefaultHttpClient();
	
	         // make GET request to the given URL
	         HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	
	         // receive response as inputStream
	         inputStream = httpResponse.getEntity().getContent();
	
	         // convert inputstream to string
	         if(inputStream != null)
	             result = convertInputStreamToString(inputStream);
	         else
	             result = "Did not work!";
	
	     } catch (Exception e) {
	         Log.d("InputStream", e.getLocalizedMessage());
	     }

	     return result;
	}
	
	
	
}