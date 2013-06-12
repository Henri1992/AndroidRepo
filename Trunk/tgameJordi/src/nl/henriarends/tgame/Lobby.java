package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lobby extends Activity {

	int count = 0;
	private AsyncTaskClass_R asyncTaskClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AsyncTaskClass_R AsyncTask = new AsyncTaskClass_R().execute(false);

	}
	
	public void checkPlayer(String player, boolean enabled)
	{
		if (!enabled) {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier(player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.INVISIBLE);
		} else {
			ImageView imagePlayer = (ImageView) findViewById(getResources().getIdentifier(player + "button", "id", getPackageName()));
			imagePlayer.setVisibility(View.VISIBLE);
			count++;
		}
	}
}
