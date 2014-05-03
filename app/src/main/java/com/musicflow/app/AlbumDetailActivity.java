package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.AlbumDetailSectionPagerAdapter;

/**
 * Created by whitneyimura on 5/1/14.
 */
public class AlbumDetailActivity extends BeatsMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter = new AlbumDetailSectionPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(0, true);
        setTitle(navTiles[0]);
        drawerLayout.closeDrawer(drawerList);
    }
}
