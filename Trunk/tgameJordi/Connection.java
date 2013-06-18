package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;


public  class Connection extends AsyncTask<Boolean, Void, String>{

	private int aantalOgen;
	private int count;
	private int turnNumber;

	public Connection()
	{
		turnNumber = 0;
	}

	public  void turnSet(int turnNumber)
	{
		this.turnNumber = turnNumber;
		// Aanroepen van turnSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new turnSet().execute();

		// Aanroepen van gameStartSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new gameStartSet().execute();

		// Aanroepen van newGameSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new newGameSet().execute();
		
		// Aanroepen van turnSet class - geen parameter dus wordt maar 1 keer uitgevoerd
		new pionSet().execute();

		// Aanroepen van diceGet class - met parameter
		// blijft zichzelf als voorwaarde klopt herhalen
		new diceGet().execute(false);		

		// Aanroepen van playerGet class - met parameter
		// blijft zichzelf als voorwaarde klopt herhalen
		new playerGet().execute(false);


	}
	///////////////////////////////
	// Inner class turnSet
	public class turnSet extends AsyncTask<Void, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/turnSet";
		private  final String METHOD_NAME = "turnSet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

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
	public  class newGameSet extends AsyncTask<Void, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/newGameSet";
		private  final String METHOD_NAME = "newGameSet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

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
	public  class gameStartSet extends AsyncTask<Void, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/gameStartSet";
		private  final String METHOD_NAME = "gameStartSet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

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
	public class pionSet extends AsyncTask<Void, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/pionSet";
		private  final String METHOD_NAME = "pionSet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

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
	public class diceGet extends AsyncTask<Boolean, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/diceGet";
		private  final String METHOD_NAME = "diceGet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
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

		return resultString;
		// onPostExecute methode
		// Implementeerd een hogere API versie dan minSdkVersion
		// Fallback voor compatibiliteit met oudere versies zelf geïmplementeerd
		// Anders fouten op die versies
		
	}
	///////////////////////////////
	// Inner class pionGet
	private  class pionGet extends AsyncTask<Boolean, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/pionGet";
		private  final String METHOD_NAME = "pionGet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
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
