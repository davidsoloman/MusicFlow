package com.musicflow.app.tests;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;

import com.musicflow.app.MainActivity;
import com.musicflow.app.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("com.musicflow.app", MainActivity.class);
    }

    public void testViewPagerNotNull() {
        MainActivity activity;
        activity = (MainActivity) getActivity();

        ViewPager viewPager;
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        assertNotNull(viewPager);
    }
}
