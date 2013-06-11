package com.example.howtoplaylos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Main extends FragmentPagerAdapter
{
	private Context _context;
	
	public Main(FragmentManager fm, Context c)
	{
		super(fm);
		_context = c;
	}

	@Override
	public Fragment getItem(int position)
	{
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount()
	{
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		// Locale l = Locale.getDefault();
		switch (position)
		{
			case 0:
				return _context.getString(R.string.step_1);
			case 1:
				return _context.getString(R.string.step_2);
			case 2:
				return _context.getString(R.string.step_3);
		}
		return null;
	}
}
