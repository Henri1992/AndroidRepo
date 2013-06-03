package nl.henriarends.tgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
    
        Handler x = new Handler();
        x.postDelayed(new SplashHandler(), 2000);
    }
    
    class SplashHandler implements Runnable {
    	public void run() {
    		startActivity(new Intent(getApplication(), MainActivity.class));
    		Splashscreen.this.finish();
    	}
    }  
}
