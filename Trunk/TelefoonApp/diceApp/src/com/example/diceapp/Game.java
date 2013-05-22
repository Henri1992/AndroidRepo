package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Game extends Activity implements OnClickListener {
	
	boolean rollAcces = false;

	Button setTrue, setFalse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);


		Button button1 = (Button) findViewById(R.id.setTrue);
		Button button2 = (Button) findViewById(R.id.setFalse);
		Button button3 = (Button) findViewById(R.id.startRoll);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);

	}
	
	class ConnectHandler implements Runnable {
        public void run() {
        startActivity(new Intent(getApplication(), Dice.class));
        Game.this.finish();
        }
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.setTrue : 
			this.rollAcces = true;
			Toast.makeText(this, "Acces set to TRUE", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setFalse : 
			this.rollAcces = false;
			Toast.makeText(this, "Acces set to FALSE", Toast.LENGTH_SHORT).show();
			break;
		case R.id.startRoll : 
			if (rollAcces == true)
			{
				Handler x = new Handler();
			    x.postDelayed(new ConnectHandler(), 100);
			}
			else if (rollAcces == false)
			{
				Toast.makeText(this, "You have no acces", Toast.LENGTH_SHORT).show();
			}
		}
	}
}