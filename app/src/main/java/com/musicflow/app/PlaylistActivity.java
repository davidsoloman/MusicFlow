package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.GenreSectionPagerAdapter;
import com.musicflow.app.pagers.PlaylistSectionPagerAdapter;

/**
 * Displays fragments related to playlists.
 */
public class PlaylistActivity extends BeatsMusicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter =
                new PlaylistSectionPagerAdapter(getSupportFragmentManager(),
                        this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(6, true);
        setTitle(navTiles[6]);
        drawerLayout.closeDrawer(drawerList);
    }
}
