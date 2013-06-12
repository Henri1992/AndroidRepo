package nl.henriarends.tgame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class howtoplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay);
		this.setRequestedOrientation(
		ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
