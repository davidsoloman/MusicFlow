package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.AlbumDetailSectionPagerAdapter;

/**
 * Displays fragments relating to Album Detail.
 */
public class AlbumDetailActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new AlbumDetailSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(1, true);
        setTitle(navTiles[1]);
        drawerLayout.closeDrawer(drawerList);
    }
}
