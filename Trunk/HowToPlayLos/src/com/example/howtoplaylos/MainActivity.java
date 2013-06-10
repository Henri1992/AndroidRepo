package com.example.howtoplaylos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private Button mStep2, mStep1, mStep3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // We get UI references
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mStep1 = (Button) findViewById(R.id.step1);
        mStep2 = (Button) findViewById(R.id.step2);
        mStep3 = (Button) findViewById(R.id.step3);
        // Set Step 1 to 2 listener
        mStep1.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		mViewPager.setCurrentItem(0);
        	}
        });
        // Set step 2 to 1 listener
        mStep2.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		mViewPager.setCurrentItem(1);
        	}
        });
        // Set step 2 to 3 listener
        mStep3.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		mViewPager.setCurrentItem(2);
        	}
        });
    }
}
