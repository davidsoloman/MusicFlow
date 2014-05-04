package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.TracksSectionPagerAdapter;

/**
 * Returns an activity with a view pager that swaps between the track list fragment and track
 * searchPredictive fragment.
 */
public class TracksActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter =
                new TracksSectionPagerAdapter(getSupportFragmentManager(),
                        this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(0, true);
        setTitle(navTiles[0]);
        drawerLayout.closeDrawer(drawerList);
    }
}
