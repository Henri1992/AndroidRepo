package nl.henriarends.tgame;

public class Pion {

	private int worp;
	private int positie;
	private int pionNummer;
	private boolean basis;
	private boolean finish;
	private String color;
	
	public Pion(){
		pionNummer = 1;
		positie = 0;
		basis = true;
		finish = false;
		color = "Groen";
		worp = 0;
	}
	
	public void verplaats()
	{
		//methode om de dobbelsteen op te halen.
		Connection co = new Connection();
		
		if(diceGet() == 6)
	}
}
