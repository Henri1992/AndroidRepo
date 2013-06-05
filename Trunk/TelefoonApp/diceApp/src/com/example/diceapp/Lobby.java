package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lobby extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);

		Button btnGame = (Button) findViewById(R.id.gotoGame);
		btnGame.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
		    {
		    	Intent intent = new Intent(v.getContext(), Game.class);
				startActivityForResult(intent, 0);
		    }
		});
	}
}

