package nl.henriarends.tgame;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends Activity {

	int aantalOgen;
	ImageView[] imageViews;
	Player player1 = new Player();
	
	private static final String SOAP_ACTION_R = "http://tempuri.org/diceGet";
	private static final String METHOD_NAME_R = "diceGet";
	private static final String NAMESPACE_R = "http://tempuri.org/";
	private static final String URL_R = "http://techniek.server-ict.nl:20824/Service.asmx";

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

	// /////////////////////////////
	// Inner class AsyncTaskClass_R
	public class AsyncTaskClass_R extends AsyncTask<Boolean, Void, String> {
		@Override
		protected String doInBackground(Boolean... params) {
			if (params.length != 1) {
				throw new IllegalArgumentException("params.length != 1");
			}

			if (params[0]) {
				// Hier thread slapen i.p.v. dit in onPostExecute
				// met de Handler methodes removeCallbacks en postDelayed en
				// een Runnable met new AsyncTaskClass_R().execute();
				// te doen. Je belast hierdoor niet de main thread.
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SoapObject Request = new SoapObject(NAMESPACE_R, METHOD_NAME_R);

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE aht = new HttpTransportSE(URL_R);

			try {
				aht.call(SOAP_ACTION_R, soapEnvelope);
				SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
						.getResponse();

				return resultString.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Wanneer try faalt return message
			return ("Failed to connect");
		}

		// Implementeerd een hogere API versie dan minSdkVersion
		// Fallback voor compatibiliteit met oudere versies zelf
		// geimplementeerd
		// Anders fouten op die versies
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		protected void onPostExecute(String result) {
			TextView tv_R;
			tv_R = (TextView) findViewById(R.id.TextView_R);

			//if (("Ontvangen " + result).equals(tv_R.getText())) {
				// Voorkomt dat thread sleep ervoor zorgt dat andere AsyncTasks
				// op de thread sleep moeten wachten. (Voert beide AsyncTasks
				// parallel i.p.v. serieel uit).
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					new AsyncTaskClass_R().executeOnExecutor(
							AsyncTask.THREAD_POOL_EXECUTOR, true);
				} else {
					new AsyncTaskClass_R().execute(true);
				}
			//}

			tv_R.setText("Ontvangen " + result);
			aantalOgen = Integer.parseInt(result);
		}
	}
}
