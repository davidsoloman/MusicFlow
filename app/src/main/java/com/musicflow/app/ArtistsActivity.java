package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.ArtistsSectionPagerAdapter;

/**
 * Returns an activity with a view pager that swaps between the artist list fragment
 * and artist search fragment.
 */
public class ArtistsActivity extends BeatsMusicActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new ArtistsSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(0, true);
        setTitle(navTiles[0]);
        drawerLayout.closeDrawer(drawerList);
    }
}
