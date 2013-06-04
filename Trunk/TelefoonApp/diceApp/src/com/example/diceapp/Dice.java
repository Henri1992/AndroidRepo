package com.example.diceapp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler.Callback;

public class Dice extends Activity 
{
	private static final String SOAP_ACTION_W = "http://tempuri.org/Write";
	private static final String METHOD_NAME_W = "Write";
	private static final String NAMESPACE_W = "http://tempuri.org/";
	private static final String URL_W = "http://techniek.server-ict.nl:20824/Service.asmx";
	
	public static int hasrolled;
	public static int hasrolledp1;
	public static int hasrolledp2;
	public static int hasrolledp3;
	public static int hasrolledp4;
	
	ImageView dice_picture;
	SoundPool dice_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_id;
	
	//ispressed 0 = not-pressed  /  ispressed 1 = pressed
	int ispressed = 0;
	
	//timer
	Handler handler;
	Timer timer=new Timer();
	boolean rolling=false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dice);
		
		//play sound
		sound_id=dice_sound.load(this,R.raw.shake_dice,1);
		
		//set image to rolling dice
		dice_picture = (ImageView) findViewById(R.id.rollDice);
		handler=new Handler(callback);
	}

	public void HandleClick(View arg0)
	{
		if(!rolling) 
		{
			rolling=true;
			//if the dice hasnt been pressed yet
			if (ispressed == 0)
			{
				dice_picture.setImageResource(R.drawable.dice3droll);
				dice_sound.play(sound_id,1.0f,1.0f,0,0,1.0f);
				timer.schedule(new Roll(), 700);
			}
			//else nothing
		}
	}

	class Roll extends TimerTask 
	{
		public void run() 
		{
			//wait to go back to the Game activity after a roll
			handler.sendEmptyMessage(0);
			handler.postDelayed(new GameHandler(), 2500);
		}
		class GameHandler implements Runnable 
		{
			public void run() 
			{
				//go to Game activity
				startActivity(new Intent(getApplication(), Game.class));
				Dice.this.finish();
			}
		}
	}

	Callback callback = new Callback() 
	{
		public boolean handleMessage(Message msg) 
		{
			//make random
			Random randomno = new Random(System.currentTimeMillis());
			int diceRand = randomno.nextInt(5 + 1);
			if (ispressed == 0)
				ispressed = 1;
			Dice.hasrolledp1 = 0;
			Dice.hasrolledp2 = 0;
			Dice.hasrolledp3 = 0;
			Dice.hasrolledp4 = 0;
			switch(diceRand+1) 
			{
			//do stuff with the thrown number
			case 1:
				dice_picture.setImageResource(R.drawable.one);
				Dice.hasrolled = 1;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 1;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 1;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 1;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 1;
				}
				break;
			case 2:
				dice_picture.setImageResource(R.drawable.two);
				Dice.hasrolled = 2;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 2;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 2;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 2;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 2;
				}
				break;
			case 3:
				dice_picture.setImageResource(R.drawable.three);
				Dice.hasrolled = 3;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 3;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 3;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 3;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 3;
				}
				break;
			case 4:
				dice_picture.setImageResource(R.drawable.four);
				Dice.hasrolled = 4;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 4;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 4;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 4;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 4;
				}
				break;
			case 5:
				dice_picture.setImageResource(R.drawable.five);
				Dice.hasrolled = 5;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 5;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 5;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 5;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 5;
				}
				break;
			case 6:
				dice_picture.setImageResource(R.drawable.six);
				Dice.hasrolled = 6;
				if (Game.currentplayer == 1)
				{
					Dice.hasrolledp1 = 6;
				}
				else if (Game.currentplayer == 2)
				{
					Dice.hasrolledp2 = 6;
				}
				else if (Game.currentplayer == 3)
				{
					Dice.hasrolledp3 = 6;
				}
				else if (Game.currentplayer == 4)
				{
					Dice.hasrolledp4 = 6;
				}
				break;
			default:
			}
			new AsyncTaskClass_W().execute();
			rolling=false;
			return true;
		}
	};
	@Override
	protected void onPause() 
	{
		super.onPause();
		dice_sound.pause(sound_id);
	}

	protected void onDestroy() 
	{
		super.onDestroy();
		timer.cancel();
	}
	
	///////////////////////////////
	//Inner class AsyncTaskClass_W
	public class AsyncTaskClass_W extends AsyncTask<Void, Void, String>
	{
		@Override
		protected String doInBackground(Void... params) {
			
			
			SoapObject Request = new SoapObject(NAMESPACE_W, METHOD_NAME_W);
			Request.addProperty("worp", Dice.hasrolled);
			
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			
			HttpTransportSE aht = new HttpTransportSE(URL_W);
			try
			{
				aht.call(SOAP_ACTION_W, soapEnvelope);
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

		protected void onPostExecute(String result) {
			TextView tv_W;
			tv_W = (TextView)findViewById(R.id.TextView_W);
			tv_W.setText("Verstuurd: " + result);
		}
	}
}