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
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Game extends Activity {

	private static final String SOAP_ACTION_Rturn = "http://tempuri.org/turnGet";
	private static final String METHOD_NAME_Rturn = "turnGet";
	private static final String NAMESPACE_Rturn = "http://tempuri.org/";
	private static final String URL_Rturn = "http://techniek.server-ict.nl:20824/Service.asmx";


	//dit is speler die op de mobiel zit
	protected static int currentPlayer = Lobby.localPlayer;

	//is de dice gebruikt? 0 = nee, 1 = ja
	public static int hasBeenRolled = 0;

	//heeft de speler de beurt?
	protected static int hasTurn = 0;

	//Hier worden de posities van de pionnen opgeslagen, dit wordt later via de server geregeld.
	private static int positionp1; //positie 1e pion
	private static int positionp2; //positie 2e pion
	private static int positionp3; //positie 3e pion
	private static int positionp4; //positie 4e pion

	SoundPool tada2 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_id;
	private AudioManager audio;

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		new AsyncTaskClass_R().execute(false);

		//initieer geluiden
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		sound_id=tada2.load(this,R.raw.tada2,1);

		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		//GAME START

		//check de status van de dice en geef feedback terug aan de speler
		if (hasBeenRolled == 1)
		{
			Toast.makeText(getApplicationContext(), "Please, select a pawn which you want to set forward.", Toast.LENGTH_SHORT).show();
		}
		else if(hasBeenRolled == 0)
		{
			Toast.makeText(getApplicationContext(), "Please, roll the dice first or wait for your turn.", Toast.LENGTH_SHORT).show();
		}

		//roll
		final ImageView rollButton = (ImageView) findViewById(R.id.imageRoll);
		rollButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//wanneer er nog niet gerolt is met de dice ga naar de dice toe
				if (hasBeenRolled == 0 && hasTurn == 1)
				{
					startActivity(new Intent(getApplication(), Dice.class));
					Game.this.finish();
				}
			}
		});
		
		//zet de rollbutton op de groene 'ok' kleur wanneer de speler mag rollen
		if ((hasBeenRolled == 0) && (hasTurn == 1))
		{
			rollButton.setImageResource(R.drawable.rollbuttongreen);
		}
		//anders zet de rollbutton op de rode 'weiger' kleur
		else if ((hasBeenRolled == 1) && (hasTurn == 1))
		{
			rollButton.setImageResource(R.drawable.rollbuttonred);
		}
		else if (((hasBeenRolled == 0) || (hasBeenRolled == 1)) && hasTurn == 0)
		{
			rollButton.setImageResource(R.drawable.rollbuttonred);
		}

		//zoek plaatjes van de pionnen, zodat deze gebruikt kunnen worden door de code
		final ImageView imgPion1 = (ImageView) findViewById(R.id.pion1);
		final ImageView imgPion2 = (ImageView) findViewById(R.id.pion2);
		final ImageView imgPion3 = (ImageView) findViewById(R.id.pion3);
		final ImageView imgPion4 = (ImageView) findViewById(R.id.pion4);

		//Zet plaatjes aan de hand van de posities
		//De waarde 0 geeft aan dat de pionnen nog in de thuisbasis zitten
		//Alle pionnen boven de positie 0 zitten in het veld
		//de waarde 0 is een dummy
		//Update image pion 1
		if (Game.positionp1 > 0){
			if (Game.currentPlayer == 1){imgPion1.setImageResource(R.drawable.piongroenveld1);}
			else if (Game.currentPlayer == 2){imgPion1.setImageResource(R.drawable.pionroodveld1);}
			else if (Game.currentPlayer == 3){imgPion1.setImageResource(R.drawable.pionzwartveld1);}
			else if (Game.currentPlayer == 4){imgPion1.setImageResource(R.drawable.pionblauwveld1);}
		}
		//Update image pion 2
		if (Game.positionp2 > 0){
			if (Game.currentPlayer == 1){imgPion2.setImageResource(R.drawable.piongroenveld2);}
			else if (Game.currentPlayer == 2){imgPion2.setImageResource(R.drawable.pionroodveld2);}
			else if (Game.currentPlayer == 3){imgPion2.setImageResource(R.drawable.pionzwartveld2);}
			else if (Game.currentPlayer == 4){imgPion2.setImageResource(R.drawable.pionblauwveld2);}			
		}
		//Update image pion 3
		if (Game.positionp3 > 0){
			if (Game.currentPlayer == 1){imgPion3.setImageResource(R.drawable.piongroenveld3);}
			else if (Game.currentPlayer == 2){imgPion3.setImageResource(R.drawable.pionroodveld3);}
			else if (Game.currentPlayer == 3){imgPion3.setImageResource(R.drawable.pionzwartveld3);}
			else if (Game.currentPlayer == 4){imgPion3.setImageResource(R.drawable.pionblauwveld3);}			
		}
		//Update image pion 4
		if (Game.positionp4 > 0){
			if (Game.currentPlayer == 1){imgPion4.setImageResource(R.drawable.piongroenveld4);}
			else if (Game.currentPlayer == 2){imgPion4.setImageResource(R.drawable.pionroodveld4);}
			else if (Game.currentPlayer == 3){imgPion4.setImageResource(R.drawable.pionzwartveld4);}
			else if (Game.currentPlayer == 4){imgPion4.setImageResource(R.drawable.pionblauwveld4);}			
		}

		//Alle pionen boven de positie 20 zitten in de finish
		//De waarde 20 is een dummy getal
		if (Game.positionp1 >= 20){
			if (Game.currentPlayer == 1){imgPion1.setImageResource(R.drawable.piongroenfinish1);}
			else if (Game.currentPlayer == 2){imgPion1.setImageResource(R.drawable.pionroodfinish1);}
			else if (Game.currentPlayer == 3){imgPion1.setImageResource(R.drawable.pionzwartfinish1);}
			else if (Game.currentPlayer == 4){imgPion1.setImageResource(R.drawable.pionblauwfinish1);}
		}
		if (Game.positionp2 >= 20){
			if (Game.currentPlayer == 1){imgPion2.setImageResource(R.drawable.piongroenfinish2);}
			else if (Game.currentPlayer == 2){imgPion2.setImageResource(R.drawable.pionroodfinish2);}
			else if (Game.currentPlayer == 3){imgPion2.setImageResource(R.drawable.pionzwartfinish2);}
			else if (Game.currentPlayer == 4){imgPion2.setImageResource(R.drawable.pionblauwfinish2);}			
		}
		if (Game.positionp3 >= 20){
			if (Game.currentPlayer == 1){imgPion3.setImageResource(R.drawable.piongroenfinish3);}
			else if (Game.currentPlayer == 2){imgPion3.setImageResource(R.drawable.pionroodfinish3);}
			else if (Game.currentPlayer == 3){imgPion3.setImageResource(R.drawable.pionzwartfinish3);}
			else if (Game.currentPlayer == 4){imgPion3.setImageResource(R.drawable.pionblauwfinish3);}			
		}
		if (Game.positionp4 >= 20){
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
					if (positionp1 >= 20)
					{
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if ((positionp1 < 20)&&(hasBeenRolled == 1))
					{
						//Bereken positie met dice waarde
						Game.positionp1 = Game.positionp1 + Dice.hasrolled;

						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){//ZET P1 SELECTIE: Groen
							if (positionp1 >= 20){
								imgPion1.setImageResource(R.drawable.piongroenfinish1);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else if(positionp1 < 20){imgPion1.setImageResource(R.drawable.piongroenveld1select);}
						}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){//ZET P1 SELECTIE: Rood

							if (positionp1 >= 20){
								imgPion1.setImageResource(R.drawable.pionroodfinish1);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else if(positionp1 < 20){imgPion1.setImageResource(R.drawable.pionroodveld1select);}
						}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){//ZET P1 SELECTIE: Zwart

							if (positionp1 >= 20){
								imgPion1.setImageResource(R.drawable.pionzwartfinish1);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else if(positionp1 < 20){imgPion1.setImageResource(R.drawable.pionzwartveld1select);}
						}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){//ZET P1 SELECTIE: Blauw
							if (positionp1 >= 20){
								imgPion1.setImageResource(R.drawable.pionblauwfinish1);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else if(positionp1 < 20){imgPion1.setImageResource(R.drawable.pionblauwveld1select);}
						}
						//reset dice waarde
						Dice.hasrolled = 0;
						Game.hasBeenRolled = 0;
					}
				}
			});

			/*
			 * einde pion 1 selectie
			 * ************************
			 * **BREAK VOOR OVERZICHT**
			 * ************************
			 * begin pion 2 selectie
			 */

			//Selecteer pion 2
			ImageView slcPion2 = (ImageView) findViewById(R.id.pion2);
			slcPion2.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (positionp2 >= 20)
					{
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if ((positionp2 < 20)&&(hasBeenRolled == 1))
					{
						//Bereken positie met dice waarde
						Game.positionp2 = Game.positionp2 + Dice.hasrolled;

						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){//ZET P2 SELECTIE: Groen
							if (positionp2 >= 20){
								imgPion2.setImageResource(R.drawable.piongroenfinish2);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion2.setImageResource(R.drawable.piongroenveld2select);}
						}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){//ZET P2 SELECTIE: Rood
							if (positionp2 >= 20){
								imgPion2.setImageResource(R.drawable.pionroodfinish2);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion2.setImageResource(R.drawable.pionroodveld2select);}
						}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){//ZET P2 SELECTIE: Zwart
							if (positionp2 >= 20){
								imgPion2.setImageResource(R.drawable.pionzwartfinish2);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion2.setImageResource(R.drawable.pionzwartveld2select);}
						}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){//ZET P2 SELECTIE: Blauw
							if (positionp2 >= 20){
								imgPion2.setImageResource(R.drawable.pionblauwfinish2);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion2.setImageResource(R.drawable.pionblauwveld2select);}
						}
						//reset dice waarde
						Dice.hasrolled = 0;
						Game.hasBeenRolled = 0;
					}
				}
			});

			/*
			 * einde pion 2 selectie
			 * ************************
			 * **BREAK VOOR OVERZICHT**
			 * ************************
			 * begin pion 3 selectie
			 */

			//Selecteer pion 3
			ImageView slcPion3 = (ImageView) findViewById(R.id.pion3);
			slcPion3.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (positionp3 >= 20)
					{
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if ((positionp3 < 20)&&(hasBeenRolled == 1))
					{
						//Bereken positie met dice waarde
						Game.positionp3 = Game.positionp3 + Dice.hasrolled;

						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){//ZET P3 SELECTIE: Groen
							if (positionp3 >= 20){
								imgPion3.setImageResource(R.drawable.piongroenfinish3);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion3.setImageResource(R.drawable.piongroenveld3select);}
						}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){//ZET P3 SELECTIE: Rood
							if (positionp3 >= 20){
								imgPion3.setImageResource(R.drawable.pionroodfinish3);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion3.setImageResource(R.drawable.pionroodveld3select);}
						}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){//ZET P3 SELECTIE: Zwart
							if (positionp3 >= 20){
								imgPion3.setImageResource(R.drawable.pionzwartfinish3);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}
							else{imgPion3.setImageResource(R.drawable.pionzwartveld3select);}
						}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){//ZET P3 SELECTIE: Blauw
							if (positionp3 >= 20){
								imgPion3.setImageResource(R.drawable.pionblauwfinish3);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion3.setImageResource(R.drawable.pionblauwveld3select);}
						}
						//reset dice waarde
						Dice.hasrolled = 0;
						Game.hasBeenRolled = 0;
					}
				}
			});

			/*
			 * einde pion 3 selectie
			 * ************************
			 * **BREAK VOOR OVERZICHT**
			 * ************************
			 * begin pion 4 selectie
			 */

			//Selecteer pion 4
			ImageView slcPion4 = (ImageView) findViewById(R.id.pion4);
			slcPion4.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					if (positionp4 >= 20)
					{
						Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
					}
					else if ((positionp4 < 20)&&(hasBeenRolled == 1))
					{
						//Bereken positie met dice waarde
						Game.positionp4 = Game.positionp4 + Dice.hasrolled;

						//WANNEER DE SPELER NUMMER 1 IS
						if (currentPlayer == 1){//ZET P4 SELECTIE: Groen
							if (positionp4 >= 20){
								imgPion4.setImageResource(R.drawable.piongroenfinish4);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion4.setImageResource(R.drawable.piongroenveld4select);}
						}
						//WANNEER DE SPELER NUMMER 2 IS
						else if(currentPlayer == 2){//ZET P4 SELECTIE: Rood
							if (positionp4 >= 20){
								imgPion4.setImageResource(R.drawable.pionroodfinish4);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion4.setImageResource(R.drawable.pionroodveld4select);}
						}
						//WANNEER DE SPELER NUMMER 3 IS
						else if (currentPlayer == 3){//ZET P4 SELECTIE: Zwart
							if (positionp4 >= 20){
								imgPion4.setImageResource(R.drawable.pionzwartfinish4);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion4.setImageResource(R.drawable.pionzwartveld4select);}
						}
						//WANNEER DE SPELER NUMMER 4 IS
						else if (currentPlayer == 4){//ZET P4 SELECTIE: Blauw
							if (positionp4 >= 20){
								imgPion4.setImageResource(R.drawable.pionblauwfinish4);
								tada2.play(sound_id,1.0f,1.0f,0,0,1.0f);
							}else{imgPion4.setImageResource(R.drawable.pionblauwveld4select);}
						}
						//reset dice waarde
						Dice.hasrolled = 0;
						Game.hasBeenRolled = 0;
					}
				}
			});
		}
		else if (hasTurn == 0)
		{
			//zet de rollbutton op de groene 'ok' kleur wanneer de speler mag rollen
			if ((hasBeenRolled == 0) && (hasTurn == 1))
			{
				rollButton.setImageResource(R.drawable.rollbuttongreen);
			}
			//anders zet de rollbutton op de rode 'weiger' kleur
			else if ((hasBeenRolled == 1) && (hasTurn == 1))
			{
				rollButton.setImageResource(R.drawable.rollbuttonred);
			}
			else if (((hasBeenRolled == 0) || (hasBeenRolled == 1)) && hasTurn == 0)
			{
				rollButton.setImageResource(R.drawable.rollbuttonred);
			}
		}
		/////////////////////////////////////////////////
		/////////////////////////////////////////////////
		/////////////////////////////////////////////////
		//EINDE GAME

	}
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
					Thread.sleep(2000);         
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE_Rturn, METHOD_NAME_Rturn);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_Rturn);

			SoapPrimitive resultString = null;
			try
			{
				aht.call(SOAP_ACTION_Rturn, soapEnvelope);
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
			//Is de game nog niet gestart door de lobby?
			if(!(result.equals(Lobby.localPlayerString)))
			{
				hasTurn = 0;
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
			//Is de game gestart door de lobby?
			else if (result.equals(Lobby.localPlayerString))
			{
				hasTurn = 1;
			}
		}
	}
}


/*
 * newGameGet met player ID
 * 
 * 
 * dit wordt teruggestuurd
 * 0 = niet bekend
 * 1 = toegang
 * 2 = weiger
 */

