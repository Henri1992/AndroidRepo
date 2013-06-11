package com.example.howtoplaylos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DummySectionFragment extends Fragment
{
	public static final String	ARG_SECTION_NUMBER	= "section_number";

	public DummySectionFragment()
	{
		Main mainTest = new Main(getSupportFragmentManager());
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (getArguments().getInt(ARG_SECTION_NUMBER) == 1)
		{
			View rootView = inflater.inflate(R.layout.first_fragment, container, false);
			rootView.findViewById(R.id.step1_step2);
			return rootView;
		}
		else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2)
		{
			View rootView = inflater.inflate(R.layout.second_fragment, container, false);
			rootView.findViewById(R.id.step2_step3);
			return rootView;
		}
		View rootView = inflater.inflate(R.layout.third_fragment, container, false);
		rootView.findViewById(R.id.step3_step2);
		return rootView;
	}
}