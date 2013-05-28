package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends Activity {
	
	public static int player = 0;
	private static int position;
	
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.game);
        	
        	//dummy: store the total number thrown
        	Game.position = Game.position + Dice.hasrolled;
        	TextView theCorrectAnsTextView = (TextView)findViewById(R.id.positie);
		    theCorrectAnsTextView.setText("" + Game.position);
        	
        	//Dummy buttons voor het hardcoden van de speler
        	
        	Button buttonp1 = (Button) findViewById(R.id.button_p1);
    		buttonp1.setOnClickListener(new OnClickListener() 
    		{
				@Override
    			public void onClick(View v) 
    		    {
		    		Game.player = 1;
    				TextView theCorrectAnsTextView = (TextView)findViewById(R.id.currentplayer);
    			    theCorrectAnsTextView.setText("Player: 1");
    		    }
    		});
    		
        	Button buttonp2 = (Button) findViewById(R.id.button_p2);
    		buttonp2.setOnClickListener(new OnClickListener() 
    		{
				@Override
    			public void onClick(View v) 
    		    {
    		    	Game.player = 2;
    				TextView theCorrectAnsTextView = (TextView)findViewById(R.id.currentplayer); 
    				theCorrectAnsTextView.setText("Player: 2");
    		    }
    		});
    		
        	Button buttonp3 = (Button) findViewById(R.id.button_p3);
    		buttonp3.setOnClickListener(new OnClickListener() 
    		{
				@Override
    			public void onClick(View v) 
    		    {
    		    	Game.player = 3;
    				TextView theCorrectAnsTextView = (TextView)findViewById(R.id.currentplayer); 
    				theCorrectAnsTextView.setText("Player: 3");
    		    }
    		});
    		
        	Button buttonp4 = (Button) findViewById(R.id.button_p4);
    		buttonp4.setOnClickListener(new OnClickListener() 
    		{
				@Override
    			public void onClick(View v) 
    		    {
    		    	Game.player = 4;
    				TextView theCorrectAnsTextView = (TextView)findViewById(R.id.currentplayer); 
    				theCorrectAnsTextView.setText("Player: 4");
    		    }
    		});
    		
    		//eind code dummy buttons voor hardcoden speler
    		
    		//dummy buttons voor het zetten van de pion data
    		//zet pion in het veld
        	Button insert = (Button) findViewById(R.id.button_insert);
    		insert.setOnClickListener(new OnClickListener() 
    		{
				@Override
    			public void onClick(View v) 
    		    {
    		    	Game.position = 0;
    		    	
    				ImageView setImage = (ImageView)findViewById(R.id.pion1); 
					if (Game.player == 1){
						setImage.setImageResource(R.drawable.pionroodveld);
					}
					else if (Game.player == 2){
						setImage.setImageResource(R.drawable.pionblauwveld);
					}
					else if (Game.player == 3){
						setImage.setImageResource(R.drawable.piongroenveld);
					}
					else if (Game.player == 4){
						setImage.setImageResource(R.drawable.piongeelveld);
					}
					else {
						setImage.setImageResource(R.drawable.pionzwart);
					}
    		    }
    		});
    		
    		//zet pion in finish
    		Button finish = (Button) findViewById(R.id.button_finish);
    		finish.setOnClickListener(new OnClickListener()
    		{
    			@Override
    			public void onClick(View v)
    			{
    				ImageView setImage = (ImageView)findViewById(R.id.pion1); 
    				
					if (Game.player == 1){
						setImage.setImageResource(R.drawable.pionroodfinish);
					}
					else if (Game.player == 2){
						setImage.setImageResource(R.drawable.pionblauwfinish);
					}
					else if (Game.player == 3){
						setImage.setImageResource(R.drawable.piongroenfinish);
					}
					else if (Game.player == 4){
						setImage.setImageResource(R.drawable.piongeelfinish);
					}
					else {
						setImage.setImageResource(R.drawable.pionzwart);
					}
    			}
    		});	
    		
    		//roll
    		Button roll = (Button) findViewById(R.id.button_roll);
    		roll.setOnClickListener(new OnClickListener()
    		{
    			@Override
    			public void onClick(View v)
    			{
    				startActivity(new Intent(getApplication(), Dice.class));
					Game.this.finish();
    			}
    		});
    }
}
    
