package com.musicflow.app.tests;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;

import com.musicflow.app.MainActivity;
import com.musicflow.app.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    protected ViewPager viewPager;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    public void loadActivityUiElements() {
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
    }

    public void testViewPagerNotNull() {
        loadActivityUiElements();
        // fail the test to show jenkins catching the error
        assertNotNull(viewPager);
    }
}
