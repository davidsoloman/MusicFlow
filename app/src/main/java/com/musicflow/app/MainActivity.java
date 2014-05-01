package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.MainSectionPagerAdapter;

public class MainActivity extends BeatsMusicActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new MainSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}
