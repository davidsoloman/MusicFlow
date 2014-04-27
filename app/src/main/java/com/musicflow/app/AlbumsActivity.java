package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Returns a view pager with an albums fragment and albums search fragment. 
 */
public class AlbumsActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new AlbumsSectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(1, true);
        setTitle(navTiles[1]);
        drawerLayout.closeDrawer(drawerList);
    }
}
