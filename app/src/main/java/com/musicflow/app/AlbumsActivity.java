package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.AlbumsSectionPagerAdapter;

/**
 * Returns a view pager with an albums fragment and albums searchPredictive fragment.
 */
public class AlbumsActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new AlbumsSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(1, true);
        setTitle(navTiles[1]);
        drawerLayout.closeDrawer(drawerList);
    }
}
