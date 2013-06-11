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
	
	private static final String SOAP_ACTION_W = "http://tempuri.org/newGameSet";
	private static final String METHOD_NAME_W = "newGameSet";
	private static final String NAMESPACE_W = "http://tempuri.org/";
	private static final String URL_W = "http://techniek.server-ict.nl:20824/Service.asmx";
	
	private static final String SOAP_ACTION_R = "http://tempuri.org/newGameGet";
	private static final String METHOD_NAME_R = "newGameGet";
	private static final String NAMESPACE_R = "http://tempuri.org/";
	private static final String URL_R = "http://techniek.server-ict.nl:20824/Service.asmx";

	int count = 0;
	int playerTeller;
	
	private String timestamp = null;
	private int lastPlayerID = 0;
	private boolean stopCheck = false;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		new AsyncTaskClass_W().execute();
		
	}
	
	public void checkPlayer(String player, boolean enabled)
	{
		if (!enabled) {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier(player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.INVISIBLE);
		} else {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier(player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.VISIBLE);
			count++;
		}
	}
	
	public void playerGet()
	{
		
	}
	
	// /////////////////////////////
	// Inner class AsyncTaskClass_W
	public class AsyncTaskClass_W extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {

			SoapObject Request = new SoapObject(NAMESPACE_W, METHOD_NAME_W);
			Request.addProperty("setGame", 1);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_W);
			try {
				aht.call(SOAP_ACTION_W, soapEnvelope);
				SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
						.getResponse();

				return resultString.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Wanneer try faalt return message
			return "Failed to connect";
		}
	
	// /////////////////////////////
	// Inner class AsyncTaskClass_R
	public class AsyncTaskClass_R extends AsyncTask<Boolean, Void, String> {
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
			Request.addProperty("dateTime", timestamp);
			Request.addProperty("playerID", lastPlayerID);

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
			
			// string die de tablet terugkrijgt: (playerID, timestamp; playerID2, timestamp2)
			String[] splitString = result.split(";");
			Collections.reverse(Arrays.asList(splitString));
			
			for (playerTeller = 1; playerTeller <= splitString.length; playerTeller++)
			{
				
			}
			
			checkPlayer("player1", true);
			checkPlayer("player2", true);
			checkPlayer("player3", false);
			checkPlayer("player4", false);
						
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
						Intent intent = new Intent(v.getContext(), Game.class);
						startActivity(intent);
					}
				});
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


			/*
			// Gevonden Spelers in de lobby weergeven
			if (player1 == false) {
				ImageView imagePlayer1 = (ImageView) findViewById(R.id.player1button);
				imagePlayer1.setVisibility(View.INVISIBLE);
			} else {
				ImageView imagePlayer1 = (ImageView) findViewById(R.id.player1button);
				imagePlayer1.setVisibility(View.VISIBLE);
				count++;
			}

			if (player2 == false) {
				ImageView imagePlayer2 = (ImageView) findViewById(R.id.player2button);
				imagePlayer2.setVisibility(View.INVISIBLE);
			} else {
				ImageView imagePlayer2 = (ImageView) findViewById(R.id.player2button);
				imagePlayer2.setVisibility(View.VISIBLE);
				count++;
			}

			if (player3 == false) {
				ImageView imagePlayer3 = (ImageView) findViewById(R.id.player3button);
				imagePlayer3.setVisibility(View.INVISIBLE);
			} else {
				ImageView imagePlayer3 = (ImageView) findViewById(R.id.player3button);
				imagePlayer3.setVisibility(View.VISIBLE);
				count++;
			}

			if (player4 == false) {
				ImageView imagePlayer4 = (ImageView) findViewById(R.id.player4button);
				imagePlayer4.setVisibility(View.INVISIBLE);
			} else {
				ImageView imagePlayer4 = (ImageView) findViewById(R.id.player4button);
				imagePlayer4.setVisibility(View.VISIBLE);
				count++;
			}
			*/
		}
	}
}
}
