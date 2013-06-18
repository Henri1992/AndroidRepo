package nl.henriarends.tgame;

import android.content.Context;

public class Player {

	private int playerNaam;
	private boolean finishAll;
	private Context appContext;
	private Pion pion1;
	private Pion pion2;
	private Pion pion3;
	private Pion pion4;
	
	public Player(Context appContext, int playerID)
	{
		Pion pion1 = new Pion(appContext, playerNaam, 1);
		Pion pion2 = new Pion(appContext, playerNaam, 2);
		Pion pion3 = new Pion(appContext, playerNaam, 3);
		Pion pion4 = new Pion(appContext, playerNaam, 4);
		this.appContext = appContext;
		playerNaam = playerID;		
		finishAll = false;
	}
	
	public void checkFinishAll()
	{
		if
		(
		pion1.getFinish() == true &&
		pion2.getFinish() == true &&
		pion3.getFinish() == true &&
		pion4.getFinish() == true
		)
		{
			finishAll = true;
			System.out.println("Congratulations Player " + playerNaam + "");
		}
		
	}
}
