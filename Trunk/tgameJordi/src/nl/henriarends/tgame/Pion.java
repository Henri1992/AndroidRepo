package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.os.AsyncTask;

public class Pion{	
	
	private int positie;
	private int pionNummer;
	private boolean basis;
	private boolean finish;
	private int playerNaam;
	private int status;
	
	private boolean succesvol;
	
	private int worpResult;
	private String[] myStringArray;
	private Context appContext;
	
	private int turnNumber;

	int count = 1;

	public Pion(Context appContext, int playerNaam, int pionNummer){
		this.appContext = appContext;
		
		this.pionNummer = pionNummer;
		this.playerNaam = playerNaam;
		positie = 0;
		basis = true;
		finish = false;
		succesvol = false;
		status = 0;
		//Connection.diceGet dg = Connection.new diceGet();

	}

	public int verplaats(int worp)
	{
		//methode om de dobbelsteen op te halen.
		if (worp == 6)
			{
			if (basis == true)
				{
					basis = false;
					switch(playerNaam)
							{
					case 1: positie = 1;	//Green
					break;
					case 2: positie = 13;	//Red
					break;
					case 3: positie = 26; 	//Black
					break;
					case 4: positie = 38;	//Blue
					break;
							}
					status = 1;
					succesvol = true;
				}
			else if (finish == true)
				{
					status = 2;
					succesvol = false;
				}
			else
				{
				if(positie >= 1 && positie <= 49)
					{	
						positie = positie + worp;
						succesvol = true;
					}
				else
					{
					status = 2;
						succesvol = false;
					}
				}
			}
		else
		{
			if(basis == true)
			{
				succesvol = true;
			}
			else
			{
				positie = positie + worp;
				succesvol = true;
			}
		}
		return positie;
	}
	
	public void setPion ()
	{
		new pionSet().execute();
	}
	
	public boolean getFinish()
	{
		return finish;
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
			Request.addProperty("playerID", playerNaam);
			Request.addProperty("pion", pionNummer);
			Request.addProperty("status", status);

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
	/*
	// Haalt op : int worp, int pion, int player, int gameId, int Succesvol
	public class diceGet extends AsyncTask<Boolean, Void, String>
	{
		private  final String SOAP_ACTION = "http://tempuri.org/diceGet";
		private  final String METHOD_NAME = "diceGet";
		private  final String NAMESPACE = "http://tempuri.org/";
		private  final String URL = "http://techniek.server-ict.nl:20824/service.asmx";
		
		protected String doInBackground(Boolean... params) {
			if(params.length != 1) {
				throw new IllegalArgumentException("params.length != 1");
			}

			if(params[0])
			{
				// Hier thread slapen i.p.v. dit in onPostExecute
				// met de Handler methodes removeCallbacks en postDelayed en
				// een Runnable met bijvoorbeeld new AsyncTaskClass()_R.execute();
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
			myStringArray = result.split(",");
			
			int intResult = Integer.parseInt(myStringArray[0]);
			
			if(intResult >= 1 && intResult <= 6)
			{
				worpResult = intResult;
				// Voorkomt dat thread sleep ervoor zorgt dat andere AsyncTasks
				// op de thread sleep moeten wachten. (Voert beide AsyncTasks
				// parallel i.p.v. serieel uit).
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				{
					new diceGet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
				}
				else
				{
					new diceGet().execute(true);
				}
			}
			else
			{
				// Geef toast
				Toast.makeText(appContext, "Something has failed, the dice does not have the right values"
							, Toast.LENGTH_LONG).show();
			}

			// Wanneer if-test = false stopt loop en wordt onderstaande uitgevoerd			
			// Sla misschien result op - verwijder deze regels indien in gebruik		}
		}
	}
*/
}
