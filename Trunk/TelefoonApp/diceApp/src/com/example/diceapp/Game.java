package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Game extends Activity {
	
	protected static int currentplayer = 0;
	private static int positionp1;
	private static int positionp2;
	private static int positionp3;
	private static int positionp4;
	
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.game);
        	if (Game.currentplayer == 0)
        	{
        		Game.currentplayer = 1;
        		LinearLayout rl1 = (LinearLayout) findViewById(R.id.pRow1);
                rl1.setVisibility(View.VISIBLE);
                
                LinearLayout rl2 = (LinearLayout) findViewById(R.id.pRow2);
                rl2.setVisibility(View.INVISIBLE);
                
                LinearLayout rl3 = (LinearLayout) findViewById(R.id.pRow3);
                rl3.setVisibility(View.INVISIBLE);
                
                LinearLayout rl4 = (LinearLayout) findViewById(R.id.pRow4);
                rl4.setVisibility(View.INVISIBLE);
        	}
        	else if (Game.currentplayer == 1)
        	{
        		Game.currentplayer = 2;
        		LinearLayout rl1 = (LinearLayout) findViewById(R.id.pRow1);
                rl1.setVisibility(View.INVISIBLE);
                
                LinearLayout rl2 = (LinearLayout) findViewById(R.id.pRow2);
                rl2.setVisibility(View.VISIBLE);
                
                LinearLayout rl3 = (LinearLayout) findViewById(R.id.pRow3);
                rl3.setVisibility(View.INVISIBLE);
                
                LinearLayout rl4 = (LinearLayout) findViewById(R.id.pRow4);
                rl4.setVisibility(View.INVISIBLE);
        	}
        	else if (Game.currentplayer == 2)
        	{
        		Game.currentplayer = 3;
        		LinearLayout rl1 = (LinearLayout) findViewById(R.id.pRow1);
                rl1.setVisibility(View.INVISIBLE);
                
                LinearLayout rl2 = (LinearLayout) findViewById(R.id.pRow2);
                rl2.setVisibility(View.INVISIBLE);
                
                LinearLayout rl3 = (LinearLayout) findViewById(R.id.pRow3);
                rl3.setVisibility(View.VISIBLE);
                
                LinearLayout rl4 = (LinearLayout) findViewById(R.id.pRow4);
                rl4.setVisibility(View.INVISIBLE);
        	}
        	else if (Game.currentplayer == 3)
        	{
        		Game.currentplayer = 4;
        		LinearLayout rl1 = (LinearLayout) findViewById(R.id.pRow1);
                rl1.setVisibility(View.INVISIBLE);
                
                LinearLayout rl2 = (LinearLayout) findViewById(R.id.pRow2);
                rl2.setVisibility(View.INVISIBLE);
                
                LinearLayout rl3 = (LinearLayout) findViewById(R.id.pRow3);
                rl3.setVisibility(View.INVISIBLE);
                
                LinearLayout rl4 = (LinearLayout) findViewById(R.id.pRow4);
                rl4.setVisibility(View.VISIBLE);
        	}
        	else if (Game.currentplayer == 4)
        	{
        		Game.currentplayer = 1;
        		LinearLayout rl1 = (LinearLayout) findViewById(R.id.pRow1);
                rl1.setVisibility(View.VISIBLE);
                
                LinearLayout rl2 = (LinearLayout) findViewById(R.id.pRow2);
                rl2.setVisibility(View.INVISIBLE);
                
                LinearLayout rl3 = (LinearLayout) findViewById(R.id.pRow3);
                rl3.setVisibility(View.INVISIBLE);
                
                LinearLayout rl4 = (LinearLayout) findViewById(R.id.pRow4);
                rl4.setVisibility(View.INVISIBLE);
        	}
        	
        	//positie p1
        	Game.positionp1 = Game.positionp1 + Dice.hasrolledp1;
        	TextView pos1View = (TextView)findViewById(R.id.p1pos);
		    pos1View.setText("Player 1 position: " + Game.positionp1);

        	//positie p2
        	Game.positionp2 = Game.positionp2 + Dice.hasrolledp2;
        	TextView pos2View = (TextView)findViewById(R.id.p2pos);
		    pos2View.setText("Player 2 position: " + Game.positionp2);
		    
        	//positie p3
        	Game.positionp3 = Game.positionp3 + Dice.hasrolledp3;
        	TextView pos3View = (TextView)findViewById(R.id.p3pos);
		    pos3View.setText("Player 3 position: " + Game.positionp3);
		    
        	//positie p4
        	Game.positionp4 = Game.positionp4 + Dice.hasrolledp4;
        	TextView pos4View = (TextView)findViewById(R.id.p4pos);
		    pos4View.setText("Player 4 position: " + Game.positionp4);
    		
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
    
