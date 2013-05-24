package com.example.diceapp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler.Callback;

public class Dice extends Activity {
    ImageView dice_picture;
    SoundPool dice_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
    int sound_id;
    Handler handler;
    Timer timer=new Timer();
    boolean rolling=false;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice);
        sound_id=dice_sound.load(this,R.raw.shake_dice,1);
        dice_picture = (ImageView) findViewById(R.id.rollDice);
        handler=new Handler(callback);
     }

    public void HandleClick(View arg0) {
        if(!rolling) {
            rolling=true;
            dice_picture.setImageResource(R.drawable.dice3droll);
            dice_sound.play(sound_id,1.0f,1.0f,0,0,1.0f);
            timer.schedule(new Roll(), 700);
        }
    }

    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
 
    Callback callback = new Callback() {
        public boolean handleMessage(Message msg) {
            	Random randomno = new Random(System.currentTimeMillis());
            	int diceRand = randomno.nextInt(5 + 1);
            switch(diceRand+1) {
            case 1:
                dice_picture.setImageResource(R.drawable.one);
                break;
            case 2:
                dice_picture.setImageResource(R.drawable.two);
                break;
            case 3:
                dice_picture.setImageResource(R.drawable.three);
                break;
            case 4:
                dice_picture.setImageResource(R.drawable.four);
                break;
            case 5:
                dice_picture.setImageResource(R.drawable.five);
                break;
            case 6:
                dice_picture.setImageResource(R.drawable.six);
                break;
            default:
            }
            rolling=false;
            return true;
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}