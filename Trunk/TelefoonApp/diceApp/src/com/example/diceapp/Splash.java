package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    
    Handler x = new Handler();
    x.postDelayed(new SplashHandler(), 5000);

    }
    
    class SplashHandler implements Runnable {
    public void run() {
    startActivity(new Intent(getApplication(), Main.class));
    Splash.this.finish();
    }
    }
    
}
    
