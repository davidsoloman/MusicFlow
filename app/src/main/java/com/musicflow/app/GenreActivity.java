package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.musicflow.app.pagers.GenreSectionPagerAdapter;

/**
 * Displays fragments related to genre.
 */
public class GenreActivity extends BeatsMusicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionsPagerAdapter =
                new GenreSectionPagerAdapter(getSupportFragmentManager(),
                        this.getApplicationContext());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        drawerList.setItemChecked(2, true);
        setTitle(navTiles[2]);
        drawerLayout.closeDrawer(drawerList);
    }
}
