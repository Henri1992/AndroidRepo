package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity {
	
	static int errorSet = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		if (errorSet == 1)
		{
			Toast.makeText(getApplicationContext(), "No game available", Toast.LENGTH_SHORT).show();
			errorSet = 0;
		}
		else if (errorSet == 2)
		{
			Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
			errorSet = 0;
		}
		else if (errorSet == 3)//nog niet in gebruik
		{
			Toast.makeText(getApplicationContext(), "Game is full", Toast.LENGTH_SHORT).show();
			errorSet = 0;
		}
		else if (errorSet == 4)//game is afgesloten
		{
			Toast.makeText(getApplicationContext(), "Game has ended", Toast.LENGTH_SHORT).show();
			errorSet = 0;
		}
		//Search Button
		ImageView imgSearch = (ImageView) findViewById(R.id.button_search);
		imgSearch.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
		    {
		    	Intent intent = new Intent(v.getContext(), Connect.class);
				startActivityForResult(intent, 0);
				Main.this.finish();
		    }
		});
		
		//Quit Button
		ImageView imgQuit = (ImageView) findViewById(R.id.button_quitgame);
		imgQuit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
		    	finish();
	            System.exit(0);
			}
		});
		
		//HowTo Button
		ImageView imgHowTo = (ImageView) findViewById(R.id.button_howto);
		imgHowTo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), Howto1.class);
				startActivityForResult(intent, 0);
				finish();
			}
		});
	}
}
