package nl.henriarends.tgame;

	import android.app.Activity;
	import android.content.pm.ActivityInfo;
	import android.os.Bundle;
	import android.widget.ImageView;
	
public class testGame extends Activity {

		boolean player1 = false;
		boolean player2 = false;
		boolean player3 = false;
		boolean player4 = false;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.game);
			this.setRequestedOrientation(
			ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
			player1 = true;
			
			if(player1 == true)
			{
				ImageView imagePlayer1 = (ImageView) findViewById(R.id.player1);
				imagePlayer1.setImageResource(R.drawable.player1selected);
				ImageView imagePlayer2 = (ImageView) findViewById(R.id.player2);
				imagePlayer2.setImageResource(R.drawable.player2);
				ImageView imagePlayer3 = (ImageView) findViewById(R.id.player3);
				imagePlayer3.setImageResource(R.drawable.player3);
				ImageView imagePlayer4 = (ImageView) findViewById(R.id.player4);
				imagePlayer4.setImageResource(R.drawable.player4);
			}
			
			else if(player2 == true)
			{
				ImageView imagePlayer5 = (ImageView) findViewById(R.id.player1);
				imagePlayer5.setImageResource(R.drawable.player1);
				ImageView imagePlayer6 = (ImageView) findViewById(R.id.player2);
				imagePlayer6.setImageResource(R.drawable.player2selected);
				ImageView imagePlayer7 = (ImageView) findViewById(R.id.player3);
				imagePlayer7.setImageResource(R.drawable.player3);
				ImageView imagePlayer8 = (ImageView) findViewById(R.id.player4);
				imagePlayer8.setImageResource(R.drawable.player4);
			}
			else if(player3 == true)
			{
				ImageView imagePlayer9 = (ImageView) findViewById(R.id.player1);
				imagePlayer9.setImageResource(R.drawable.player1);
				ImageView imagePlayer10 = (ImageView) findViewById(R.id.player2);
				imagePlayer10.setImageResource(R.drawable.player2);
				ImageView imagePlayer11 = (ImageView) findViewById(R.id.player3);
				imagePlayer11.setImageResource(R.drawable.player3selected);
				ImageView imagePlayer12= (ImageView) findViewById(R.id.player4);
				imagePlayer12.setImageResource(R.drawable.player4);
			}
			else if(player4 == true)
			{
				ImageView imagePlayer13 = (ImageView) findViewById(R.id.player1);
				imagePlayer13.setImageResource(R.drawable.player1);
				ImageView imagePlayer14 = (ImageView) findViewById(R.id.player2);
				imagePlayer14.setImageResource(R.drawable.player2);
				ImageView imagePlayer15 = (ImageView) findViewById(R.id.player3);
				imagePlayer15.setImageResource(R.drawable.player3);
				ImageView imagePlayer16 = (ImageView) findViewById(R.id.player4);
				imagePlayer16.setImageResource(R.drawable.player4selected);
			}
		}
	}
