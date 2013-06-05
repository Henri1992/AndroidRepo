package com.example.diceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	private static final String NULL = null;
	String dummypass = "Abc";
	String pass = NULL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		//Login Button
		Button btnLogin = (Button) findViewById(R.id.button_lobbylogin);
		btnLogin.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				EditText mEdit = (EditText)findViewById(R.id.lobbyLogin);
				pass = mEdit.getText().toString();
				
			    if( pass.equals(dummypass.toString()))
				{
			    	finish();
					Intent intent = new Intent(v.getContext(), Connect.class);
					startActivityForResult(intent, 0);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Something went wrong, please check your internet connection and the inserted code. Also make sure the host has internet acces too.", Toast.LENGTH_LONG).show();
				}				
			}
		});
	}
}

