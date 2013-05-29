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

	private static final String SOAP_ACTION = "http://tempuri.org/Welkom";
	private static final String METHOD_NAME = "Welkom";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://techniek.server-ict.nl:20824/Service.asmx";
	
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendMessage(View view) {
		
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
			EditText editText = (EditText) findViewById(R.id.edit_message);
			
			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("naam", editText.getText().toString());
			
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			
			HttpTransportSE aht = new HttpTransportSE(URL);
			try
			{
				aht.call(SOAP_ACTION, soapEnvelope);
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
			tv = (TextView)findViewById(R.id.TextView1);
			tv.setText(result);
		}
	}

}
