package nl.henriarends.tgame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lobby extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);
		this.setRequestedOrientation(
		ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		Button start = (Button) findViewById(R.id.startButton);
		start.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), Game.class);
				startActivity(intent);
			}
			
		});
	}
	
}
