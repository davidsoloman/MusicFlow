package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;

import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.TracksFragment;
import com.musicflow.app.TracksSearchFragment;

import java.util.Locale;

public class TracksSectionPagerAdapter extends SectionsPagerAdapter {
    public TracksSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (position == 0) {
            return TracksFragment.newInstance(position + 1);
        } else {
            return TracksSearchFragment.newInstance(position + 1);
        }    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        if (position == 0) {
            return TracksFragment.getTitle();
        } else {
            return TracksSearchFragment.getTitle();
        }
    }
}
