package com.example.mytest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String SOAP_ACTION_W = "http://tempuri.org/Write";
	private static final String METHOD_NAME_W = "Write";
	private static final String NAMESPACE_W = "http://tempuri.org/";
	private static final String URL_W = "http://techniek.server-ict.nl:20824/Service.asmx";
	
	private static final String SOAP_ACTION_R = "http://tempuri.org/Read";
	private static final String METHOD_NAME_R = "Read";
	private static final String NAMESPACE_R = "http://tempuri.org/";
	private static final String URL_R = "http://techniek.server-ict.nl:20824/Service.asmx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendMessage(View view) {
		
		new AsyncTaskClass_W().execute();
	}
	
	public void refreshMessage(View view) {
		new AsyncTaskClass_R().execute(false);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	///////////////////////////////
	//Inner class AsyncTaskClass_W
	public class AsyncTaskClass_W extends AsyncTask<Void, Void, String>
	{
		@Override
		protected String doInBackground(Void... params) {
			EditText editText = (EditText) findViewById(R.id.edit_message);
			
			SoapObject Request = new SoapObject(NAMESPACE_W, METHOD_NAME_W);
			Request.addProperty("worp", editText.getText().toString());
			
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			
			HttpTransportSE aht = new HttpTransportSE(URL_W);
			try
			{
				aht.call(SOAP_ACTION_W, soapEnvelope);
				SoapPrimitive resultString = (SoapPrimitive)soapEnvelope.getResponse();
				
				return resultString.toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			// Wanneer try faalt return message
			return "Failed to connect";
		}

		protected void onPostExecute(String result) {
			TextView tv_W;
			tv_W = (TextView)findViewById(R.id.TextView_W);
			tv_W.setText("Verstuurd: " + result);
		}
	}
	
	///////////////////////////////
	//Inner class AsyncTaskClass_R
	public class AsyncTaskClass_R extends AsyncTask<Boolean, Void, String>
	{		
		@Override
		protected String doInBackground(Boolean... params) {
			if(params.length != 1) {
				throw new IllegalArgumentException("params.length != 1");
			}
			
			if(params[0])
			{
				// Hier thread slapen i.p.v. dit in onPostExecute
				// met de Handler methodes removeCallbacks en postDelayed en
				// een Runnable met new AsyncTaskClass_R().execute();
				// te doen. Je belast hierdoor niet de main thread.
				try {
			        Thread.sleep(10000);         
			    } catch (InterruptedException e) {
			       e.printStackTrace();
			    }
			}
			
			SoapObject Request = new SoapObject(NAMESPACE_R, METHOD_NAME_R);
			
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			
			HttpTransportSE aht = new HttpTransportSE(URL_R);
			
			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION_R, soapEnvelope);
				resultString = (SoapPrimitive)soapEnvelope.getResponse();

				return resultString.toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			// Wanneer try faalt return message
			return ("Failed to connect");
		}
		
		protected void onPostExecute(String result)
		{
			TextView tv_R;
			tv_R = (TextView)findViewById(R.id.TextView_R);

			if(("Ontvangen " + result).equals(tv_R.getText()))
			{
				new AsyncTaskClass_R().execute(true);
			}
			
			tv_R.setText("Ontvangen " + result.toString());
		}
	}
}