package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class MainActivity extends BeatsMusicActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new MainSectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}
