package com.example.diceapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Connect extends Activity {

	private static final String SOAP_ACTION_R = "http://tempuri.org/newGameGet";
	private static final String METHOD_NAME_R = "newGameGet";
	private static final String NAMESPACE_R = "http://tempuri.org/";
	private static final String URL_R = "http://techniek.server-ict.nl:20824/Service.asmx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);
		new AsyncTaskClass_R().execute(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		
		// Implementeerd een hogere API versie dan minSdkVersion
		// Fallback voor compatibiliteit met oudere versies zelf geïmplementeerd
		// Anders fouten op die versies
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		protected void onPostExecute(String result)
		{
			TextView tv_R;
			tv_R = (TextView)findViewById(R.id.Rconnect);

			if(("Ontvangen " + result).equals(tv_R.getText()))
			{
				// Voorkomt dat thread sleep ervoor zorgt dat andere AsyncTasks
				// op de thread sleep moeten wachten. (Voert beide AsyncTasks
				// parallel i.p.v. serieel uit).
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				{
					new AsyncTaskClass_R().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
				}
				else
				{
					new AsyncTaskClass_R().execute(true);
				}
			}			
			if (result.equals("1"))//Is er een game gevonden?
			{	
				tv_R.setText("Game gevonden");
				startActivity(new Intent(getApplication(), Lobby.class));
				Connect.this.finish();
			}
			else if(result.equals("0"))//Is er geen game gevonden?
			{
				tv_R.setText("Geen game gevonden");
				Main.errorSet = 1;
				startActivity(new Intent(getApplication(), Main.class));
				Connect.this.finish();
			}
			else//Geen connectie of een error?
			{
				tv_R.setText("Geen connectie beschikbaar");
				Main.errorSet = 2;
				startActivity(new Intent(getApplication(), Main.class));
				Connect.this.finish();
			}
		}
	}
}