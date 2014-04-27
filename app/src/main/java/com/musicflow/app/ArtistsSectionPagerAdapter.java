package com.musicflow.app;

import android.support.v4.app.FragmentManager;

import java.util.Locale;

public class ArtistsSectionPagerAdapter extends SectionsPagerAdapter {
    public ArtistsSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (position == 0) {
            return ArtistFragment.newInstance(position + 1);
        } else {
            return ArtistSearchFragment.newInstance(position + 1);
        }    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        if (position == 0) {
            return ArtistFragment.getTitle();
        } else {
            return ArtistSearchFragment.getTitle();
        }
    }
}
