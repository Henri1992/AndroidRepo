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
import android.widget.Toast;

public class Pion{

	private int worp;
	private int positie;
	private int pionNummer;
	private boolean basis;
	private boolean finish;
	private String color;
	private int playerNaam;
	private int worpResult;
	private String[] myStringArray;
	private Context appContext;

	int count = 1;

	public Pion(Context appContext){
		this.appContext = appContext;
		
		if (count <= 16 && count >= 1)
		{
			pionNummer = count;
			count++;
		}
		else
		{
			count = 1;
		}
		;
		playerNaam = 1 /*komt nog*/;
		positie = 0;
		basis = true;
		finish = false;
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
		//Connection.diceGet dg = Connection.new diceGet();

	}

	public void verplaats()
	{
		//methode om de dobbelsteen op te halen.
		new diceGet().execute(false);
		
	}
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

}
