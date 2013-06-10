package com.example.howtoplaylos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflating layout
		View v = inflater.inflate(R.layout.second_fragment, container, false);
		return v;
	}
}
