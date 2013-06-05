package com.example.diceapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

	protected static int currentplayer = 0;
	public static int hasbeenrolled = 0;

	//zet welke speler dit speelt (dit is een dummy waarde)
	protected static int thisplayer = 1;


	//Hier worden de posities van de pionen opgeslagen, dit wordt later via de server geregeld.
	private static int positionp1;
	private static int positionp2;
	private static int positionp3;
	private static int positionp4;

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		if (hasbeenrolled == 1)
		{
			Toast.makeText(getApplicationContext(), "Please, select a pawn which you want to set forward.", Toast.LENGTH_LONG).show();
		}
		else if(hasbeenrolled == 0)
		{
			Toast.makeText(getApplicationContext(), "Please, wait for your turn to roll.", Toast.LENGTH_SHORT).show();
		}


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
		//posities weergeven
		TextView pos1View = (TextView)findViewById(R.id.p1pos);
		pos1View.setText("Pion 1 position: " + Game.positionp1);
		
		TextView pos2View = (TextView)findViewById(R.id.p2pos);
		pos2View.setText("Pion 2 position: " + Game.positionp2);
		
		TextView pos3View = (TextView)findViewById(R.id.p3pos);
		pos3View.setText("Pion 3 position: " + Game.positionp3);
		
		TextView pos4View = (TextView)findViewById(R.id.p4pos);
		pos4View.setText("Pion 4 position: " + Game.positionp4);

		//zoek plaatjes van de pionen
		final ImageView imgPion1 = (ImageView) findViewById(R.id.pion1);
		final ImageView imgPion2 = (ImageView) findViewById(R.id.pion2);
		final ImageView imgPion3 = (ImageView) findViewById(R.id.pion3);
		final ImageView imgPion4 = (ImageView) findViewById(R.id.pion4);

		//Zet plaatjes aan de hand van de posities

		//Alle pionen boven de positie 0 zitten in het veld
		//de waarde 0 is een dummy
		if ((Game.positionp1 > 0)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1)			{
				imgPion1.setImageResource(R.drawable.piongroenveld1);
			}
			else if (Game.thisplayer == 2){
				imgPion1.setImageResource(R.drawable.pionroodveld1);
			}
			else if (Game.thisplayer == 3){
				imgPion1.setImageResource(R.drawable.pionzwartveld1);
			}
			else if (Game.thisplayer == 4){
				imgPion1.setImageResource(R.drawable.pionblauwveld1);
			}
		}
		else if ((Game.positionp2 > 0)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion2.setImageResource(R.drawable.piongroenveld2);
			}
			else if (Game.thisplayer == 2){
				imgPion2.setImageResource(R.drawable.pionroodveld2);
			}
			else if (Game.thisplayer == 3){
				imgPion2.setImageResource(R.drawable.pionzwartveld2);
			}
			else if (Game.thisplayer == 4){
				imgPion2.setImageResource(R.drawable.pionblauwveld2);
			}			
		}
		else if ((Game.positionp3 > 0)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion3.setImageResource(R.drawable.piongroenveld3);
			}
			else if (Game.thisplayer == 2){
				imgPion3.setImageResource(R.drawable.pionroodveld3);
			}
			else if (Game.thisplayer == 3){
				imgPion3.setImageResource(R.drawable.pionzwartveld3);
			}
			else if (Game.thisplayer == 4){
				imgPion3.setImageResource(R.drawable.pionblauwveld3);
			}			
		}
		else if ((Game.positionp4 > 0)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion4.setImageResource(R.drawable.piongroenveld4);
			}
			else if (Game.thisplayer == 2){
				imgPion4.setImageResource(R.drawable.pionroodveld4);
			}
			else if (Game.thisplayer == 3){
				imgPion4.setImageResource(R.drawable.pionzwartveld4);
			}
			else if (Game.thisplayer == 4){
				imgPion4.setImageResource(R.drawable.pionblauwveld4);
			}			
		}

		//Alle pionen boven de positie 20 zitten in de finish
		//De waarde 20 is een dummy getal
		if ((Game.positionp1 > 20)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion1.setImageResource(R.drawable.piongroenfinish1);
			}
			else if (Game.thisplayer == 2){
				imgPion1.setImageResource(R.drawable.pionroodfinish1);
			}
			else if (Game.thisplayer == 3){
				imgPion1.setImageResource(R.drawable.pionzwartfinish1);
			}
			else if (Game.thisplayer == 4){
				imgPion1.setImageResource(R.drawable.pionblauwfinish1);
			}
		}
		else if ((Game.positionp2 > 20)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion2.setImageResource(R.drawable.piongroenfinish2);
			}
			else if (Game.thisplayer == 2){
				imgPion2.setImageResource(R.drawable.pionroodfinish2);
			}
			else if (Game.thisplayer == 3){
				imgPion2.setImageResource(R.drawable.pionzwartfinish2);
			}
			else if (Game.thisplayer == 4){
				imgPion2.setImageResource(R.drawable.pionblauwfinish2);
			}			
		}
		else if ((Game.positionp3 > 20)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion3.setImageResource(R.drawable.piongroenfinish3);
			}
			else if (Game.thisplayer == 2){
				imgPion3.setImageResource(R.drawable.pionroodfinish3);
			}
			else if (Game.thisplayer == 3){
				imgPion3.setImageResource(R.drawable.pionzwartfinish3);
			}
			else if (Game.thisplayer == 4){
				imgPion3.setImageResource(R.drawable.pionblauwfinish3);
			}			
		}
		else if ((Game.positionp4 > 20)&&(Game.currentplayer == Game.thisplayer))
		{
			if (Game.thisplayer == 1){
				imgPion4.setImageResource(R.drawable.piongroenfinish4);
			}
			else if (Game.thisplayer == 2){
				imgPion4.setImageResource(R.drawable.pionroodfinish4);
			}
			else if (Game.thisplayer == 3){
				imgPion4.setImageResource(R.drawable.pionzwartfinish4);
			}
			else if (Game.thisplayer == 4){
				imgPion4.setImageResource(R.drawable.pionblauwfinish4);
			}			
		}
		/* einde zetten van plaatjes voor pionnen
		 * 
		 *****************************************************************************************
		 *****************************************************************************************
		 * 
		 * begin code selectie van pionnen
		 */


		//selecteer pion (onclicklisteners)
		
		//Selecteer pion 1
		ImageView slcPion1 = (ImageView) findViewById(R.id.pion1);
		slcPion1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (positionp1 >= 20)
				{
					Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
				}
				else if ((positionp1 < 20)&&(hasbeenrolled == 1))
				{
					//WANNEER DE SPELER NUMMER 1 IS
					if (thisplayer == 1){
						//ZET P1 SELECTIE
						imgPion1.setImageResource(R.drawable.piongroenveld1select);
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.piongroenfinish2);}
						else {imgPion2.setImageResource(R.drawable.piongroenveld2);}
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.piongroenfinish3);}
						else {imgPion3.setImageResource(R.drawable.piongroenveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.piongroenfinish4);}
						else {imgPion4.setImageResource(R.drawable.piongroenveld4);}
					}
					//WANNEER DE SPELER NUMMER 2 IS
					else if(thisplayer == 2){
						//ZET P1 SELECTIE
						imgPion1.setImageResource(R.drawable.pionroodveld1select);
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionroodfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionroodveld2);}
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionroodfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionroodveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionroodfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionroodveld4);}
					}
					//WANNEER DE SPELER NUMMER 3 IS
					else if (thisplayer == 3){
						//ZET P1 SELECTIE
						imgPion1.setImageResource(R.drawable.pionzwartveld1select);
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionzwartfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionzwartveld2);}
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionzwartfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionzwartveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionzwartfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionzwartveld4);}
					}
					//WANNEER DE SPELER NUMMER 4 IS
					else if (thisplayer == 4){
						//ZET P1 SELECTIE
						imgPion1.setImageResource(R.drawable.pionblauwveld1select);
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionblauwfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionblauwveld2);}
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionblauwfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionblauwveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionblauwfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionblauwveld4);}
					}
					positionp1 = positionp1 + Dice.hasrolled;
					//reset dice waarde
					Dice.hasrolled = 0;
					//zet de waarde naar 'er is nog niets gerolt'
					hasbeenrolled = 0;
					Game.positionp1 = Game.positionp1 + Dice.hasrolled;
					TextView pos1View = (TextView)findViewById(R.id.p1pos);
					pos1View.setText("Pion 1 position: " + Game.positionp1);
				}
					
			}
		});
		
		/*
		 * einde pion 1 selectie
		 * ************************
		 * **BREAK VOOR OVERZICHT**
		 * ************************
		 * begin pion 2 selectie
		 */
		
		//Selecteer pion 2
		ImageView slcPion2 = (ImageView) findViewById(R.id.pion2);
		slcPion2.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (positionp2 >= 20)
				{
					Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
				}
				else if ((positionp2 < 20)&&(hasbeenrolled == 1))
				{
					//WANNEER DE SPELER NUMMER 1 IS
					if (thisplayer == 1){						
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.piongroenfinish1);}
						else {imgPion1.setImageResource(R.drawable.piongroenveld1);}
						//RESET P2
						imgPion2.setImageResource(R.drawable.piongroenveld2select);
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.piongroenfinish3);}
						else {imgPion3.setImageResource(R.drawable.piongroenveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.piongroenfinish4);}
						else {imgPion4.setImageResource(R.drawable.piongroenveld4);}
					}
					//WANNEER DE SPELER NUMMER 2 IS
					else if(thisplayer == 2){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionroodfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionroodveld1);}
						//RESET P2
						imgPion2.setImageResource(R.drawable.pionroodveld2select);
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionroodfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionroodveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionroodfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionroodveld4);}
					}
					//WANNEER DE SPELER NUMMER 3 IS
					else if (thisplayer == 3){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionzwartfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionzwartveld1);}
						//RESET P2
						imgPion2.setImageResource(R.drawable.pionzwartveld2select);
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionzwartfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionzwartveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionzwartfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionzwartveld4);}
					}
					//WANNEER DE SPELER NUMMER 4 IS
					else if (thisplayer == 4){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionblauwfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionblauwveld1);}
						//RESET P2
						imgPion2.setImageResource(R.drawable.pionblauwveld2select);
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionblauwfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionblauwveld3);}
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionblauwfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionblauwveld4);}
					}
					positionp2 = positionp2 + Dice.hasrolled;
					//reset dice waarde
					Dice.hasrolled = 0;
					//zet de waarde naar 'er is nog niets gerolt'
					hasbeenrolled = 0;
					Game.positionp2 = Game.positionp2 + Dice.hasrolled;
					TextView pos2View = (TextView)findViewById(R.id.p2pos);
					pos2View.setText("Pion 2 position: " + Game.positionp2);
				}
			}
		});
		
		/*
		 * einde pion 2 selectie
		 * ************************
		 * **BREAK VOOR OVERZICHT**
		 * ************************
		 * begin pion 3 selectie
		 */
		
		//Selecteer pion 3
		ImageView slcPion3 = (ImageView) findViewById(R.id.pion3);
		slcPion3.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (positionp3 >= 20)
				{
					Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
				}
				else if ((positionp3 < 20)&&(hasbeenrolled == 1))
				{
					//WANNEER DE SPELER NUMMER 1 IS
					if (thisplayer == 1){						
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.piongroenfinish1);}
						else {imgPion1.setImageResource(R.drawable.piongroenveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.piongroenfinish2);}
						else {imgPion2.setImageResource(R.drawable.piongroenveld2);}						
						//RESET P3
						imgPion3.setImageResource(R.drawable.piongroenveld3select);
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.piongroenfinish4);}
						else {imgPion4.setImageResource(R.drawable.piongroenveld4);}
					}
					//WANNEER DE SPELER NUMMER 2 IS
					else if(thisplayer == 2){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionroodfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionroodveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionroodfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionroodveld2);}						
						//RESET P3
						imgPion3.setImageResource(R.drawable.pionroodveld3select);
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionroodfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionroodveld4);}
					}
					//WANNEER DE SPELER NUMMER 3 IS
					else if (thisplayer == 3){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionzwartfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionzwartveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionzwartfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionzwartveld2);}						
						//RESET P3
						imgPion3.setImageResource(R.drawable.pionzwartveld3select);
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionzwartfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionzwartveld4);}
					}
					//WANNEER DE SPELER NUMMER 4 IS
					else if (thisplayer == 4){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionblauwfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionblauwveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionblauwfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionblauwveld2);}						
						//RESET P3
						imgPion3.setImageResource(R.drawable.pionblauwveld3select);
						//RESET P4
						if (positionp4 == 0){imgPion4.setImageResource(R.drawable.pionleeg4);}
						else if (positionp4 >= 20){imgPion4.setImageResource(R.drawable.pionblauwfinish4);}
						else {imgPion4.setImageResource(R.drawable.pionblauwveld4);}
					}
					positionp3 = positionp3 + Dice.hasrolled;
					//reset dice waarde
					Dice.hasrolled = 0;
					//zet de waarde naar 'er is nog niets gerolt'
					hasbeenrolled = 0;
					Game.positionp3 = Game.positionp3 + Dice.hasrolled;
					TextView pos3View = (TextView)findViewById(R.id.p3pos);
					pos3View.setText("Pion 3 position: " + Game.positionp3);
				}
			}
		});
		
		/*
		 * einde pion 3 selectie
		 * ************************
		 * **BREAK VOOR OVERZICHT**
		 * ************************
		 * begin pion 4 selectie
		 */
		
		//Selecteer pion 4
		ImageView slcPion4 = (ImageView) findViewById(R.id.pion4);
		slcPion4.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (positionp4 >= 20)
				{
					Toast.makeText(getApplicationContext(), "This pawn has already reached the finish line!", Toast.LENGTH_SHORT).show();
				}
				else if ((positionp4 < 20)&&(hasbeenrolled == 1))
				{
					//WANNEER DE SPELER NUMMER 1 IS
					if (thisplayer == 1){						
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.piongroenfinish1);}
						else {imgPion1.setImageResource(R.drawable.piongroenveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.piongroenfinish2);}
						else {imgPion2.setImageResource(R.drawable.piongroenveld2);}						
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.piongroenfinish3);}
						else {imgPion3.setImageResource(R.drawable.piongroenveld3);}
						//RESET P4
						imgPion4.setImageResource(R.drawable.piongroenveld4select);
					}
					//WANNEER DE SPELER NUMMER 2 IS
					else if(thisplayer == 2){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionroodfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionroodveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionroodfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionroodveld2);}						
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionroodfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionroodveld3);}
						//RESET P4
						imgPion4.setImageResource(R.drawable.pionroodveld4select);
					}
					//WANNEER DE SPELER NUMMER 3 IS
					else if (thisplayer == 3){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionzwartfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionzwartveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionzwartfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionzwartveld2);}						
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionzwartfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionzwartveld3);}
						//RESET P4
						imgPion4.setImageResource(R.drawable.pionzwartveld4select);
					}
					//WANNEER DE SPELER NUMMER 4 IS
					else if (thisplayer == 4){
						//ZET P1 SELECTIE
						if (positionp1 == 0){imgPion1.setImageResource(R.drawable.pionleeg1);}
						else if (positionp1 >= 20){imgPion1.setImageResource(R.drawable.pionblauwfinish1);}
						else {imgPion1.setImageResource(R.drawable.pionblauwveld1);}
						//RESET P2
						if (positionp2 == 0){imgPion2.setImageResource(R.drawable.pionleeg2);}
						else if (positionp2 >= 20){imgPion2.setImageResource(R.drawable.pionblauwfinish2);}
						else {imgPion2.setImageResource(R.drawable.pionblauwveld2);}						
						//RESET P3
						if (positionp3 == 0){imgPion3.setImageResource(R.drawable.pionleeg3);}
						else if (positionp3 >= 20){imgPion3.setImageResource(R.drawable.pionblauwfinish3);}
						else {imgPion3.setImageResource(R.drawable.pionblauwveld3);}
						//RESET P4
						imgPion4.setImageResource(R.drawable.pionblauwveld4select);
					}
					positionp4 = positionp4 + Dice.hasrolled;
					//reset dice waarde
					Dice.hasrolled = 0;
					//zet de waarde naar 'er is nog niets gerolt'
					hasbeenrolled = 0;
					Game.positionp4 = Game.positionp4 + Dice.hasrolled;
					TextView pos4View = (TextView)findViewById(R.id.p4pos);
					pos4View.setText("Pion 4 position: " + Game.positionp4);
				}
			}
		});
	}
}

