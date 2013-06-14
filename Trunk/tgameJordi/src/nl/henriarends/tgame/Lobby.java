package nl.henriarends.tgame;

import java.util.Arrays;
import java.util.Collections;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lobby extends Activity {

	int count = 0;
	private int playerCounter = 1;
	String players;
	
	private String playerIDAndDateTime = null;
	private boolean stopCheck = false;
	private View _v;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
		new AsyncTaskClass_NoRepeatSetNewGame().execute();
	}
	
	public void checkPlayer(int player, boolean enabled)
	{
		if (!enabled) {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier("player" + player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.INVISIBLE);
		} else {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier("player" + player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.VISIBLE);
			count++;
		}
	}
	
	public void playerGet()
	{
		
	}

	// /////////////////////////////
	// Inner class AsyncTaskClass_NoRepeatSetNewGame
	private class AsyncTaskClass_NoRepeatSetNewGame extends AsyncTask<Void, Void, String> {
		private static final String SOAP_ACTION = "http://tempuri.org/newGameSet";
		private static final String METHOD_NAME = "newGameSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

		@Override
		protected String doInBackground(Void... params) {

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("game", 1);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL);
			try {
				aht.call(SOAP_ACTION, soapEnvelope);
				SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
						.getResponse();

				return resultString.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Wanneer try faalt return message
			return "Failed to connect";
		}

		protected void onPostExecute(String result) {
			playerIDAndDateTime = result;
			new AsyncTaskClass_R().execute(false);
		}
	}

	///////////////////////////////
	// Inner class AsyncTaskClass_NoRepeat
	private class AsyncTaskClass_NoRepeat extends AsyncTask<Void, Void, String>
	{
		private static final String SOAP_ACTION = "http://tempuri.org/gameStartSet";
		private static final String METHOD_NAME = "gameStartSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/service.asmx";

		@Override
		protected String doInBackground(Void... params) {
			int gameSet = 1;

			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("status", gameSet);
			Request.addProperty("spelers", players);

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
			//Log.d("System.err", result);
			Intent intent = new Intent(_v.getContext(), Game.class);
			startActivity(intent);			
		}
	}
	
	////////////////////////////////
	// Inner class AsyncTaskClass_R
	public class AsyncTaskClass_R extends AsyncTask<Boolean, Void, String> {
		private static final String SOAP_ACTION_R = "http://tempuri.org/playerGet";
		private static final String METHOD_NAME_R = "playerGet";
		private static final String NAMESPACE_R = "http://tempuri.org/";
		private static final String URL_R = "http://techniek.server-ict.nl:20824/service.asmx";
		
		@Override
		protected String doInBackground(Boolean... params) {
			if (params.length != 1) {
				throw new IllegalArgumentException("params.length != 1");
			}

			if (params[0]) {
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
			Request.addProperty("playerIDAndDateTime", playerIDAndDateTime);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_R);

			SoapPrimitive resultString = null;
			try {
				aht.call(SOAP_ACTION_R, soapEnvelope);
				resultString = (SoapPrimitive) soapEnvelope.getResponse();

				return resultString.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Wanneer try faalt return message
			return ("Failed to connect");
		}

		// Implementeerd een hogere API versie dan minSdkVersion
		// Fallback voor compatibiliteit met oudere versies zelf
		// geimplementeerd
		// Anders fouten op die versies
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		protected void onPostExecute(String result) 
		{
			if(!result.equals("nothing"))
			{
				// string die de tablet terug krijgt: (playerID, timestamp; playerID2, timestamp2)
				String[] splitString = result.split(";");
				// Sla laatste player op
				playerIDAndDateTime = splitString[0];
				Collections.reverse(Arrays.asList(splitString));
				// Eerste waarde geeft status van GameStart aan.

				for (int i = 0; i < splitString.length && playerCounter <= 4; playerCounter++, i++)
				{
					String[] splitOneString = splitString[i].split(",");
					if(playerCounter > 1)
					{
						players += "," + splitOneString[0];
					}
					else
					{
						players = splitOneString[0];
					}

					checkPlayer(playerCounter, true);
				}

				if(playerCounter == 4)
				{
					// lobby sluiten
					// spel starten doe je pas als er op de knop is gedrukt
				}

				//TextView TextView3 = (TextView) findViewById(R.id.notenoughplayers);
				//TextView3.setText( Integer.toString(count));
				// De start game button weergeven als genoeg spelers zijn aangesloten op
				// het spel
				if (count < 2) {
					TextView TextView1 = (TextView) findViewById(R.id.notenoughplayers);
					TextView1.setVisibility(View.VISIBLE);
					Button imagePlayer5 = (Button) findViewById(R.id.startButton);
					imagePlayer5.setVisibility(View.INVISIBLE);
				} 
				else {
					TextView TextView1 = (TextView) findViewById(R.id.notenoughplayers);
					TextView1.setVisibility(View.INVISIBLE);
					Button imagePlayer5 = (Button) findViewById(R.id.startButton);
					imagePlayer5.setVisibility(View.VISIBLE);
					Button start = (Button) findViewById(R.id.startButton);
					start.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							_v = v;
							new AsyncTaskClass_NoRepeat().execute();
						}
					});
				}
			}
			
			
			// Blijft herhalen met zoeken naar spelers
			if(!stopCheck)
			{
				// Voorkomt dat thread sleep ervoor zorgt dat andere AsyncTasks
				// op de thread sleep moeten wachten. (Voert beide AsyncTasks
				// parallel i.p.v. serieel uit).
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					new AsyncTaskClass_R().executeOnExecutor(
							AsyncTask.THREAD_POOL_EXECUTOR, true);
				} else {
					new AsyncTaskClass_R().execute(true);
				}
			}
		}
	}
}
