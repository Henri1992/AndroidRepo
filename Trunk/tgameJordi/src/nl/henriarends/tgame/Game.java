package nl.henriarends.tgame;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

public class Game extends Activity {

	public int aantalOgen;
	public ImageView[] imageViews;
	public Player player1 = new Player();
	Connection connectClass = new Connection();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		new AsyncTaskClass_R().execute(false);
		
		imageViews = new ImageView[]{(ImageView) findViewById(R.id.player1), (ImageView) findViewById(R.id.player2), 
				(ImageView) findViewById(R.id.player3), (ImageView) findViewById(R.id.player4)};
		activePlayer(1);
	}
	
	private void activePlayer(int _activePlayer)
	{
		for(int i = 1; i <= imageViews.length; i++)
		{
			if(i == _activePlayer)
			{
				imageViews[i-1].setImageResource(getResources().getIdentifier("player" + i + "selected", "drawable", getPackageName()));
			}
			else
			{
				imageViews[i-1].setImageResource(getResources().getIdentifier("player" + i, "drawable", getPackageName()));
			}
		}
	}

}
