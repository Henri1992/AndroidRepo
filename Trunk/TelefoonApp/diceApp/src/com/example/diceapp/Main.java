package com.example.diceapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView img = (ImageView) findViewById(R.id.button_search);
		img.setOnClickListener(new OnClickListener() 
		{
		    public void onClick(View v) 
		    {
		    	Intent intent = new Intent(v.getContext(), Connect.class);
				startActivityForResult(intent, 0);
		    }
		});
	}
}
