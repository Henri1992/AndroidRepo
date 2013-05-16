package com.example.phptestapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new AsyncTaskClass().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	///////////////////////////////
	//Inner class
	public class AsyncTaskClass extends AsyncTask<Void, Void, String>
	{
		@Override
	    protected String doInBackground(Void... params) {
	        
	            String result = "";
	    		//the year data to send
	    		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    		nameValuePairs.add(new BasicNameValuePair("year","0"));
	    		 
	    		InputStream is = null;
	    		//http post
	    		try
	    		{
	    		        HttpClient httpclient = new DefaultHttpClient();
	    		        //Verander ip door ipadres van jouw computer en /NetBeans... door locatie waar jouw php bestand staat.
	    		        HttpPost httppost = new HttpPost("http://192.168.1.1/NetBeansProjects/android/index.php");
	    		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    		        HttpResponse response = httpclient.execute(httppost);
	    		        HttpEntity entity = response.getEntity();
	    		        is = entity.getContent();
	    		}
	    		catch(Exception e)
	    		{
	    		        Log.e("log_tag", "Error in http connection "+e.toString());
	    		}
	    		//convert response to string
	    		try
	    		{
	    		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	    		        StringBuilder sb = new StringBuilder();
	    		        String line = null;
	    		        while ((line = reader.readLine()) != null) {
	    		                sb.append(line + "\n");
	    		        }
	    		        is.close();
	    		 
	    		        result=sb.toString();
	    		}
	    		catch(Exception e)
	    		{
	    		        Log.e("log_tag", "Error converting result "+e.toString());
	    		}
	    		
	    		return result;
		}
		
		protected void onPostExecute(String result) {
	        
			TextView textViewJson = (TextView) findViewById(R.id.textViewJson);
			
	    		//parse json data
	    		try
	    		{
	    		        JSONArray jArray = new JSONArray(result);
	    		        for(int i=0;i<jArray.length();i++){
	    		                JSONObject json_data = jArray.getJSONObject(i);
	    		                Log.i("log_tag","Id: "+json_data.getInt("Id")+
	    		                        ", Data: "+json_data.getString("Data")
	    		                );
	    		                
	    		                textViewJson.setText(textViewJson.getText() + "\n" +
                										"Id: " + json_data.getInt("Id") +
                										", Data: " + json_data.getString("Data"));
	    		        }
	    		}
	    		catch(JSONException e)
	    		{
	    		        Log.e("log_tag", "Error parsing data "+e.toString());
	    		}
	    }
	}


}
