package com.example.tablet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash);
        
        Handler x = new Handler();
        x.postDelayed(new SplashHandler(), 4000);
    }
    
    class SplashHandler implements Runnable {
    	public void run() {
    		startActivity(new Intent(getApplication(), Main.class));
    		Splash.this.finish();
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
