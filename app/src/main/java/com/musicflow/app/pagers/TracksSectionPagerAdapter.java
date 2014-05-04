package com.musicflow.app.pagers;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.NoInternetFragment;
import com.musicflow.app.TracksFragment;
import com.musicflow.app.TracksSearchFragment;

/**
 * Adapter for the TracksActivity that switches between different tracks fragments.
 */
public class TracksSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public TracksSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return TracksFragment.newInstance(position + 1);
            } else {
                return TracksSearchFragment.newInstance(position + 1);
            }
        } else {
            return NoInternetFragment.newInstance(position + 1);
        }
    }

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
