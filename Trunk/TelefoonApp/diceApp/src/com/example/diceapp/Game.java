package com.example.diceapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Game extends Activity {

	//dit is speler die op de mobiel zit
	protected static int currentPlayer = Lobby.localPlayer;

	//turnGet informatie die wordt gebruikt bij het regelen van beurten.
	protected int getPlayer = 0;
	protected static int currentTime;
	protected long timeStampOld = 0;//millis
	protected long timeStamp;//milis

	//Geef feedback aan de speler
	public static int showTurnData = 0;

	//Mag de speler rollen 0=ja 1=nee (staat los van de beurt van de speler)
	public static int hasBeenRolled = 0;

	//Is de speler aan de beurt? 0=nee 1=ja
	protected static int hasTurn = 0;

	//Is alle data klaar om verstuurd te worden? 0=nee 1=ja
	protected int dataReady = 0;

	//Hier worden de posities van de pionnen opgeslagen, dit wordt later via de server geregeld.
	private static int statusPion1; //positie 1e pion
	private static int statusPion2; //positie 2e pion
	private static int statusPion3; //positie 3e pion
	private static int statusPion4; //positie 4e pion
	private static int selectedPion = 0; //geef aan welke pion is geselecteerd

	//Zet gebruik geluid (1)
	SoundPool tada2 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_id;
	private AudioManager audio;


	@SuppressWarnings("deprecation")
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		new AsyncTaskClass_RhasTurn().execute(false);

		//Zet gebruik geluid (2)
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		sound_id=tada2.load(this,R.raw.tada2,1);

		//Zet de achtergrond voor de speler gebaseert op hun nummer.
		Resources res = getResources();
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.game);
		if (currentPlayer == 1){
			Drawable drawable = res.getDrawable(R.drawable.backmobielgroen); 
			linearLayout.setBackgroundDrawable(drawable);
		}
		else if (currentPlayer == 2){
			Drawable drawable = res.getDrawable(R.drawable.backmobielrood); 
			linearLayout.setBackgroundDrawable(drawable);
		}
		else if (currentPlayer == 3){
			Drawable drawable = res.getDrawable(R.drawable.backmobielzwart); 
			linearLayout.setBackgroundDrawable(drawable);
		}
		else if (currentPlayer == 2){
			Drawable drawable = res.getDrawable(R.drawable.backmobielblauw); 
			linearLayout.setBackgroundDrawable(drawable);
		}

		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		//GAME START


		//check de status van de dice en geef feedback terug aan de speler
		if (showTurnData == 1){
			Toast.makeText(getApplicationContext(), "Please, select a pawn which you want to set forward.", Toast.LENGTH_SHORT).show();
			showTurnData = 0;
		}

		//OnClickListener voor de rolbutton
		//Check of de speler mag rollen wanneer hij/zij hier op klikt.
		final ImageView rollButton = (ImageView) findViewById(R.id.imageRoll);
		rollButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//wanneer er nog niet gerolt is met de dice ga naar de dice toe
				if (hasBeenRolled == 0 && hasTurn == 1){
					startActivity(new Intent(getApplication(), Dice.class));
					Game.this.finish();
				}
			}
		});

		//zoek plaatjes van de pionnen, zodat deze gebruikt kunnen worden door de code
		final ImageView imgPion1 = (ImageView) findViewById(R.id.pion1);
		final ImageView imgPion2 = (ImageView) findViewById(R.id.pion2);
		final ImageView imgPion3 = (ImageView) findViewById(R.id.pion3);
		final ImageView imgPion4 = (ImageView) findViewById(R.id.pion4);

		//Zet plaatjes pion wanneer deze in basis zitten.
		if (Game.statusPion1 == 1){imgPion1.setImageResource(R.drawable.pionleeg1);}
		if (Game.statusPion2 == 1){imgPion2.setImageResource(R.drawable.pionleeg2);}
		if (Game.statusPion3 == 1){imgPion3.setImageResource(R.drawable.pionleeg3);}
		if (Game.statusPion4 == 1){imgPion4.setImageResource(R.drawable.pionleeg4);}

		//Zet plaatjes pion wanneer deze in het veld zitten.
		if (Game.statusPion1 == 2){
			if (Game.currentPlayer == 1){imgPion1.setImageResource(R.drawable.piongroenveld1);}
			else if (Game.currentPlayer == 2){imgPion1.setImageResource(R.drawable.pionroodveld1);}
			else if (Game.currentPlayer == 3){imgPion1.setImageResource(R.drawable.pionzwartveld1);}
			else if (Game.currentPlayer == 4){imgPion1.setImageResource(R.drawable.pionblauwveld1);}
		}
		if (Game.statusPion2 == 2){
			if (Game.currentPlayer == 1){imgPion2.setImageResource(R.drawable.piongroenveld2);}
			else if (Game.currentPlayer == 2){imgPion2.setImageResource(R.drawable.pionroodveld2);}
			else if (Game.currentPlayer == 3){imgPion2.setImageResource(R.drawable.pionzwartveld2);}
			else if (Game.currentPlayer == 4){imgPion2.setImageResource(R.drawable.pionblauwveld2);}			
		}
		if (Game.statusPion3 == 2){
			if (Game.currentPlayer == 1){imgPion3.setImageResource(R.drawable.piongroenveld3);}
			else if (Game.currentPlayer == 2){imgPion3.setImageResource(R.drawable.pionroodveld3);}
			else if (Game.currentPlayer == 3){imgPion3.setImageResource(R.drawable.pionzwartveld3);}
			else if (Game.currentPlayer == 4){imgPion3.setImageResource(R.drawable.pionblauwveld3);}			
		}
		if (Game.statusPion4 == 2){
			if (Game.currentPlayer == 1){imgPion4.setImageResource(R.drawable.piongroenveld4);}
			else if (Game.currentPlayer == 2){imgPion4.setImageResource(R.drawable.pionroodveld4);}
			else if (Game.currentPlayer == 3){imgPion4.setImageResource(R.drawable.pionzwartveld4);}
			else if (Game.currentPlayer == 4){imgPion4.setImageResource(R.drawable.pionblauwveld4);}			
		}

		//Zet plaatjes pion wanneer deze in de finish zitten
		if (Game.statusPion1 == 3){
			if (Game.currentPlayer == 1){imgPion1.setImageResource(R.drawable.piongroenfinish1);}
			else if (Game.currentPlayer == 2){imgPion1.setImageResource(R.drawable.pionroodfinish1);}
			else if (Game.currentPlayer == 3){imgPion1.setImageResource(R.drawable.pionzwartfinish1);}
			else if (Game.currentPlayer == 4){imgPion1.setImageResource(R.drawable.pionblauwfinish1);}
		}
		if (Game.statusPion2 == 3){
			if (Game.currentPlayer == 1){imgPion2.setImageResource(R.drawable.piongroenfinish2);}
			else if (Game.currentPlayer == 2){imgPion2.setImageResource(R.drawable.pionroodfinish2);}
			else if (Game.currentPlayer == 3){imgPion2.setImageResource(R.drawable.pionzwartfinish2);}
			else if (Game.currentPlayer == 4){imgPion2.setImageResource(R.drawable.pionblauwfinish2);}			
		}
		if (Game.statusPion3 == 3){
			if (Game.currentPlayer == 1){imgPion3.setImageResource(R.drawable.piongroenfinish3);}
			else if (Game.currentPlayer == 2){imgPion3.setImageResource(R.drawable.pionroodfinish3);}
			else if (Game.currentPlayer == 3){imgPion3.setImageResource(R.drawable.pionzwartfinish3);}
			else if (Game.currentPlayer == 4){imgPion3.setImageResource(R.drawable.pionblauwfinish3);}			
		}
		if (Game.statusPion4 == 3){
			if (Game.currentPlayer == 1){imgPion4.setImageResource(R.drawable.piongroenfinish4);}
			else if (Game.currentPlayer == 2){imgPion4.setImageResource(R.drawable.pionroodfinish4);}
			else if (Game.currentPlayer == 3){imgPion4.setImageResource(R.drawable.pionzwartfinish4);}
			else if (Game.currentPlayer == 4){imgPion4.setImageResource(R.drawable.pionblauwfinish4);}			
		}
		/* einde zetten van plaatjes voor pionnen
		 * 
		 *****************************************************************************************
		 *****************************************************************************************
		 * 
		 * begin code selectie van pionnen
		 * Elke pion moet een rode rand krijgen om aan te geven dat deze geselecteerd is.
		 * Na de selectie wordt de waarde van de dice aan de positie van de pion toegevoegd en wordt de dice waarde weer op 0 gezet
		 */
		if (hasTurn == 1)
		{
			//selecteer pion (onclicklisteners)

			//Selecteer pion 1
			ImageView slcPion1 = (ImageView) findViewById(R.id.pion1);
			slcPion1.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (statusPion1 == 3){
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if (((statusPion1 == 1)||(statusPion1 == 2))&&(hasBeenRolled == 1)){
						//Sla selectie op
						selectedPion = 1;
						dataReady = 1;
						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){imgPion1.setImageResource(R.drawable.piongroenveld1select);}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){imgPion1.setImageResource(R.drawable.pionroodveld1select);}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){imgPion1.setImageResource(R.drawable.pionzwartveld1select);}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){imgPion1.setImageResource(R.drawable.pionblauwveld1select);}
						//reset dice waarde
						Dice.hasrolled = 0;
						new AsyncTaskClass_WsendData().execute();
					}
				}
			});

			//Selecteer pion 2
			ImageView slcPion2 = (ImageView) findViewById(R.id.pion2);
			slcPion2.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (statusPion2 == 3){
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if (((statusPion1 == 1)||(statusPion1 == 2))&&(hasBeenRolled == 1)){
						//Sla selectie op
						selectedPion = 2;
						dataReady = 1;
						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){imgPion2.setImageResource(R.drawable.piongroenveld2select);}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){imgPion2.setImageResource(R.drawable.pionroodveld2select);}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){imgPion2.setImageResource(R.drawable.pionzwartveld2select);}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){imgPion2.setImageResource(R.drawable.pionblauwveld2select);}
						//reset dice waarde
						Dice.hasrolled = 0;
						new AsyncTaskClass_WsendData().execute();
					}
				}
			});

			//Selecteer pion 3
			ImageView slcPion3 = (ImageView) findViewById(R.id.pion3);
			slcPion3.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (statusPion3 == 3){
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if (((statusPion1 == 1)||(statusPion1 == 2))&&(hasBeenRolled == 1)){
						//Sla selectie op
						selectedPion = 3;
						dataReady = 1;
						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){imgPion3.setImageResource(R.drawable.piongroenveld3select);}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){imgPion3.setImageResource(R.drawable.pionroodveld3select);}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){imgPion3.setImageResource(R.drawable.pionzwartveld3select);}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){imgPion3.setImageResource(R.drawable.pionblauwveld3select);}
						//reset dice waarde
						Dice.hasrolled = 0;
						new AsyncTaskClass_WsendData().execute();
					}
				}
			});

			//Selecteer pion 4
			ImageView slcPion4 = (ImageView) findViewById(R.id.pion4);
			slcPion4.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (statusPion4 == 3){
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if (((statusPion1 == 1)||(statusPion1 == 2))&&(hasBeenRolled == 1)){
						//Sla selectie op
						selectedPion = 4;
						dataReady = 1;
						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){imgPion4.setImageResource(R.drawable.piongroenveld4select);}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){imgPion4.setImageResource(R.drawable.pionroodveld4select);}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){imgPion4.setImageResource(R.drawable.pionzwartveld4select);}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){imgPion4.setImageResource(R.drawable.pionblauwveld4select);}
						//reset dice waarde
						Dice.hasrolled = 0;
						new AsyncTaskClass_WsendData().execute();
					}
				}
			});
		}
		else if (hasTurn == 0)
		{
			//niks
		}

		/////////////////////////////////////////////////
		/////////////////////////////////////////////////
		/////////////////////////////////////////////////
		//EINDE GAME
	}

	//De code hieronder is voor het controleren van de volume, de code zet het gebruik van media-volume
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			return true;
		default:
			return false;
		}
	}

	
	///////////////////////////////
	//Inner class AsyncTaskClass_RhasTurn
	public class AsyncTaskClass_RhasTurn extends AsyncTask<Boolean, Void, String>
	{
		//hasTurn
		private static final String SOAP_ACTION_RhasTurn = "http://tempuri.org/turnGet";
		private static final String METHOD_NAME_RhasTurn = "turnGet";
		private static final String NAMESPACE_RhasTurn = "http://tempuri.org/";
		private static final String URL_RhasTurn = "http://techniek.server-ict.nl:20824/Service.asmx";

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
					Thread.sleep(2000);         
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE_RhasTurn, METHOD_NAME_RhasTurn);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_RhasTurn);

			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION_RhasTurn, soapEnvelope);
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
			String[] splitString = result.split(",");
			//sla laatset timestamp op
			timeStamp = Long.parseLong(splitString[1]);
			getPlayer = Integer.parseInt(splitString[0]);
			if (timeStamp > timeStampOld)
			{
				//controleren op spelers
				if (currentPlayer == getPlayer)
				{
					hasTurn = 1;
					hasBeenRolled = 0;
					timeStampOld = timeStamp;
					ImageView setBtn = (ImageView) findViewById(R.id.imageRoll);
					setBtn.setImageResource(R.drawable.rollbuttongreen);
				}
				else
				{
					hasTurn = 0;
					ImageView setBtn = (ImageView) findViewById(R.id.imageRoll);
					setBtn.setImageResource(R.drawable.rollbuttonred);
					if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
					{
						new AsyncTaskClass_RhasTurn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
					}
					else
					{
						new AsyncTaskClass_RhasTurn().execute(true);
					}
				}
			}
			else
			{
				hasTurn = 0;
				ImageView setBtn = (ImageView) findViewById(R.id.imageRoll);
				setBtn.setImageResource(R.drawable.rollbuttonred);
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				{
					new AsyncTaskClass_RhasTurn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
				}
				else
				{
					new AsyncTaskClass_RhasTurn().execute(true);
				}
			}
		}
	}
	
	///////////////////////////////
	//Inner class AsyncTaskClass_RgetData
	private class AsyncTaskClass_RgetData extends AsyncTask<Boolean, Void, String>
	{
		//getData (Pion status)
		private static final String SOAP_ACTION_RgetData = "http://tempuri.org/pionGet";
		private static final String METHOD_NAME_RgetData = "pionGet";
		private static final String NAMESPACE_RgetData = "http://tempuri.org/";
		private static final String URL_RgetData = "http://techniek.server-ict.nl:20824/Service.asmx";

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
					Thread.sleep(1000);         
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE_RgetData, METHOD_NAME_RgetData);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_RgetData);

			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION_RgetData, soapEnvelope);
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
			//teruggestuurde data = statusPion1,statusPion2,statusPion3,statusPion4
			//split op de komma
			String[] splitString = result.split(",");
			//Zet waarden
			statusPion1 = Integer.parseInt(splitString[0]);
			statusPion2 = Integer.parseInt(splitString[1]);
			statusPion3 = Integer.parseInt(splitString[2]);
			statusPion4 = Integer.parseInt(splitString[3]);

			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				new AsyncTaskClass_RgetData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
			}
			else
			{
				new AsyncTaskClass_RgetData().execute(true);
			}
		}
	}

	///////////////////////////////
	//Inner class AsyncTaskClass_RgetError
	@SuppressWarnings("unused")
	private class AsyncTaskClass_RgetError extends AsyncTask<Boolean, Void, String>
	{
		//getError
		private static final String SOAP_ACTION_RgetError = "http://tempuri.org/diceGet";
		private static final String METHOD_NAME_RgetError = "diceGet";
		private static final String NAMESPACE_RgetError = "http://tempuri.org/";
		private static final String URL_RgetError = "http://techniek.server-ict.nl:20824/Service.asmx";

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
					Thread.sleep(1000);         
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE_RgetError, METHOD_NAME_RgetError);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_RgetError);

			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION_RgetError, soapEnvelope);
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
			//teruggestuurde data = statusPion1,statusPion2,statusPion3,statusPion4
			//split op de komma
			String[] splitString = result.split(",");
			//Zet waarden
			int errorGet = Integer.parseInt(splitString[3]);
			if (errorGet == 1){
				Toast.makeText(getApplicationContext(), "Your own pion is in the way, please select a different pion", Toast.LENGTH_SHORT).show();
			}
			else if (errorGet == 2){
				Toast.makeText(getApplicationContext(), "Wrong data send", Toast.LENGTH_SHORT).show();
			}
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				new AsyncTaskClass_RgetData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, true);
			}
			else
			{
				new AsyncTaskClass_RgetData().execute(true);
			}
		}
	}

	///////////////////////////////
	//Inner class AsyncTaskClass_WsendData
	public class AsyncTaskClass_WsendData extends AsyncTask<Void, Void, String>
	{
		//sendData (Player + dice + pion + error)
		private static final String SOAP_ACTION = "http://tempuri.org/diceSet";
		private static final String METHOD_NAME = "diceSet";
		private static final String NAMESPACE = "http://tempuri.org/";
		private static final String URL = "http://techniek.server-ict.nl:20824/Service.asmx";

		@Override
		protected String doInBackground(Void... params) {


			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("player", currentPlayer);
			Request.addProperty("worp", Dice.hasrolled);
			Request.addProperty("pion", selectedPion);
			Request.addProperty("error", 0);
			
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

			// Wanneer try failt return message
			return "Failed to connect";
		}
	}
}