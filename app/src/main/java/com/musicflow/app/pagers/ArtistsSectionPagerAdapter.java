package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;

import com.musicflow.app.ArtistsFragment;
import com.musicflow.app.ArtistsSearchFragment;
import com.musicflow.app.BeatsMusicFragment;

import java.util.Locale;

public class ArtistsSectionPagerAdapter extends SectionsPagerAdapter {
    public ArtistsSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {

        if (position == 0) {
            return ArtistsFragment.newInstance(position + 1);
        } else {
            return ArtistsSearchFragment.newInstance(position + 1);
        }    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        if (position == 0) {
            return ArtistsFragment.getTitle();
        } else {
            return ArtistsSearchFragment.getTitle();
        }
    }
}
