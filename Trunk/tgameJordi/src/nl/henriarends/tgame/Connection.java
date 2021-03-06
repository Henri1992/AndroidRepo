package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;


public class Connection{

	private int aantalOgen;
	private int count;
	private int turnNumber;

	public Connection()
	{
		turnNumber = 0;
	}

	public void turnSet(int turnNumber)
	{
		this.turnNumber = turnNumber;
		// Aanroepen van turnSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new turnSet().execute();

		// Aanroepen van gameStartSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new gameStartSet().execute();

		// Aanroepen van newGameSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new newGameSet().execute();

		// Aanroepen van diceGet class - met parameter
		// blijft zichzelf als voorwaarde klopt herhalen
		new diceGet().execute(false);	

		// Aanroepen van pionGet class - met parameter
		// blijft zichzelf als voorwaarde klopt herhalen
		new pionGet().execute(false);	

		// Aanroepen van playerGet class - met parameter
		// blijft zichzelf als voorwaarde klopt herhalen
		new playerGet().execute(false);


	}
	///////////////////////////////
	// Inner class turnSet
	private class turnSet extends AsyncTask<Void, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/turnSet";
		private static final String METHOD_NAME = "turnSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

		@Override
		protected String doInBackground(Void... params) {

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("turn", turnNumber);

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

		}
	}
	///////////////////////////////
	// Inner class newGameSet
	private class newGameSet extends AsyncTask<Void, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/newGameSet";
		private static final String METHOD_NAME = "newGameSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

		@Override
		protected String doInBackground(Void... params) {

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("turn", turnNumber);

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

		}
	}
	///////////////////////////////
	// Inner class gameStartSet
	private class gameStartSet extends AsyncTask<Void, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/gameStartSet";
		private static final String METHOD_NAME = "gameStartSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

		@Override
		protected String doInBackground(Void... params) {

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("turn", turnNumber);

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

		}
	}
	///////////////////////////////
	// Inner class diceGet
	private class diceGet extends AsyncTask<Boolean, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/diceGet";
		private static final String METHOD_NAME = "diceGet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
		private String resultString;
		private String diceGet;

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
			String diceGet = resultString;
		}
	}
	///////////////////////////////
	// Inner class pionGet
	private class pionGet extends AsyncTask<Boolean, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/pionGet";
		private static final String METHOD_NAME = "pionGet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
		private String resultString;
		private String pionGet;

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
			String pionGet = resultString;
		}
	}
	///////////////////////////////
	// Inner class playerGet
	private class playerGet extends AsyncTask<Boolean, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/playerGet";
		private static final String METHOD_NAME = "playerGet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
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
