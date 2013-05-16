package com.example.tablet;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this));
	}
}
