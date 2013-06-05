package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Connect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);

		Handler x = new Handler();
		x.postDelayed(new ConnectHandler(), 500);

	}
	//got to Game activity
	class ConnectHandler implements Runnable 
	{
		public void run() 
		{
			startActivity(new Intent(getApplication(), Lobby.class));
			Connect.this.finish();
		}
	}
}