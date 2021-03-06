package com.example.diceapp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler.Callback;

public class Dice extends Activity 
{
	public static int hasrolled;
	
	ImageView dice_picture;
	SoundPool dice_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_id;
	
	SoundPool tada = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_id2;
	
	//ispressed 0 = not-pressed  /  ispressed 1 = pressed
	int ispressed = 0;
	
	//timer
	Handler handler;
	Timer timer=new Timer();
	boolean rolling=false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Toast.makeText(getApplicationContext(), "Tap dice to start rolling!", Toast.LENGTH_SHORT).show();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dice);
		
		//make sound so the code can use it
		sound_id=dice_sound.load(this,R.raw.shake_dice,1);
		sound_id2=tada.load(this,R.raw.tada,1);
		
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
				ispressed = 1;//Er is op de dobbelsteen gedrukt.
				Game.hasBeenRolled = 1;//Er is gerolt.
			switch(diceRand+1) 
			{//Bepaal gegevens door middel van het gegooide getal en een case.
			case 1:
				dice_picture.setImageResource(R.drawable.one);
				Dice.hasrolled = 1;
				break;
			case 2:
				dice_picture.setImageResource(R.drawable.two);
				Dice.hasrolled = 2;
				break;
			case 3:
				dice_picture.setImageResource(R.drawable.three);
				Dice.hasrolled = 3;
				break;
			case 4:
				dice_picture.setImageResource(R.drawable.four);
				Dice.hasrolled = 4;
				break;
			case 5:
				dice_picture.setImageResource(R.drawable.five);
				Dice.hasrolled = 5;
				break;
			case 6:
				dice_picture.setImageResource(R.drawable.six);
				Dice.hasrolled = 6;
				tada.play(sound_id2,1.0f,1.0f,0,0,1.0f);
				break;
			default:
			}
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
	

}