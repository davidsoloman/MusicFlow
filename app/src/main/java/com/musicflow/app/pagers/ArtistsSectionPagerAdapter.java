package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ArtistsFragment;
import com.musicflow.app.ArtistsSearchFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

public class ArtistsSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public ArtistsSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return ArtistsFragment.newInstance(position + 1);
            } else {
                return ArtistsSearchFragment.newInstance(position + 1);
            }
        } else {
            return NoInternetFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            return 2;
        } else {
            return 1;
        }
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
