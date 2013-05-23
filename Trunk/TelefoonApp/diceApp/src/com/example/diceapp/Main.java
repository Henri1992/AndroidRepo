package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView imgSearch = (ImageView) findViewById(R.id.button_search);
		imgSearch.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
		    {
		    	Intent intent = new Intent(v.getContext(), Connect.class);
				startActivityForResult(intent, 0);
		    }
		});
		
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
		
		ImageView imgHowTo = (ImageView) findViewById(R.id.button_howto);
		imgHowTo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), Howto.class);
				startActivityForResult(intent, 0);
			}
		});
	}
}
