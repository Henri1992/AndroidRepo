package nl.henriarends.tgame;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(
		ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void mainMusic()
	{
		
	}
	
	public void init()
	{
		Button start = (Button) findViewById(R.id.newGameButton);
		start.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), Lobby.class);
				startActivity(intent);
			}
			
		});
		
		Button howtoplay = (Button) findViewById(R.id.howtoplayButton);
		howtoplay.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View b) {
				// TODO Auto-generated method stub
				Intent hIntent = new Intent(b.getContext(), howtoplay.class);
				startActivity(hIntent);
			}
			
		});
		
		Button exit = (Button) findViewById(R.id.exitButton);
		exit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
	            System.exit(0);
			}
			
		});
	}

}
