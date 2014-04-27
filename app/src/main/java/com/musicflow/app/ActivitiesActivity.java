package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Created by whitney on 4/26/14.
 */
public class ActivitiesActivity extends BeatsMusicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new ActivitiesSectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(0, true);
        setTitle(navTiles[0]);
        drawerLayout.closeDrawer(drawerList);
    }
}
