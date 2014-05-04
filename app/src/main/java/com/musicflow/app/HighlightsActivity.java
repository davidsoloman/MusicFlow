package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.HighlightsSectionPagerAdapter;

/**
 * Displays fragments relating to Highlights.
 */
public class HighlightsActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new HighlightsSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(3, true);
        setTitle(navTiles[3]);
        drawerLayout.closeDrawer(drawerList);
    }
}
