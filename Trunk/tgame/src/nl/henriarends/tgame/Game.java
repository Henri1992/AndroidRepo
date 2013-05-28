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

	boolean player1 = false;
	boolean player2 = false;
	boolean player3 = false;
	boolean player4 = false;
	
	private static final String SOAP_ACTION_R = "http://tempuri.org/Read";
	private static final String METHOD_NAME_R = "Read";
	private static final String NAMESPACE_R = "http://tempuri.org/";
	private static final String URL_R = "http://techniek.server-ict.nl:20824/Service.asmx";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		this.setRequestedOrientation(
		ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		new AsyncTaskClass_R().execute(false);
		
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
					Thread.sleep(10000);
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
				SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();

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

			tv_R.setText("Ontvangen " + result.toString());
		}
	}
}
