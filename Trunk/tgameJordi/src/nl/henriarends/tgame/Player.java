package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

public class Player {

	private int playerNaam;
	private int playerId;
	private String color;
	private boolean finishAll;
	private boolean basis;
	int count = 1;
	private playerGet playerGetId;
	private Context appContext;
	
	public Player(Context appContext){
		this.appContext = appContext;
		
		if (count <= 4 && count >= 1)
		{
			playerNaam = count;
			count++;
		}
		else
		{
			count = 1;
		};
		playerGet playerGetId = new playerGet();
		playerNaam = 1;
		playerId = 1 /*komt nog*/;
		switch(playerNaam)
		{
		case 1: color = "Green";
		break;
		case 2: color = "Blue";
		break;
		case 3: color = "Black";
		break;
		case 4: color = "Red";
		break;
		};		
		finishAll = false;
		basis = true;
	}
	///////////////////////////////
	// Inner class playerGet
	public  class playerGet extends AsyncTask<Boolean, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/playerGet";
		private  final String METHOD_NAME = "playerGet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
		private String resultString;
		private String playerGet;

		@Override
		protected String doInBackground(Boolean... params) {
			if(params.length != 1) {
				throw new IllegalArgumentException("params.length != 1");
			}

			if(params[0])
			{
				// Hier thread slapen i.p.v. dit in onPostExecute
				// met de Handler methodes removeCallbacks en postDelayed en
				// een Runnable met bijvoorbeeld new AsyncTaskClass()_Repeat.execute();
				// te doen. Je belast hierdoor niet de main thread.
				try {
					Thread.sleep(10000);         
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL);

			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION, soapEnvelope);
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

		// onPostExecute methode
		// Implementeerd een hogere API versie dan minSdkVersion
		// Fallback voor compatibiliteit met oudere versies zelf geïmplementeerd
		// Anders fouten op die versies
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		protected void onPostExecute(String result)
		{
			String playerGet = resultString;
		}
	}
}
