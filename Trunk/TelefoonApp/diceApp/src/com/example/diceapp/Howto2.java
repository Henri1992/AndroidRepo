package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Howto2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howto2);
		getWindow().getAttributes().windowAnimations = R.style.Fade;

		ImageView imgHowTo = (ImageView) findViewById(R.id.howtoplay2mobiel);
		imgHowTo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), Howto3.class);
				startActivityForResult(intent, 0);
				Howto2.this.finish();
			}
		});
	}
}

