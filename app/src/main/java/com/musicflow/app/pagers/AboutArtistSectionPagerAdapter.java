package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ActivitiesFragment;
import com.musicflow.app.ActivitiesSearchFragment;
import com.musicflow.app.ArtistViewFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

/**
 * Created by whitneyimura on 5/2/14.
 */
public class AboutArtistSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public AboutArtistSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return ArtistViewFragment.newInstance(position + 1);
            } else {
                return ActivitiesSearchFragment.newInstance(position + 1);
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
            return ActivitiesFragment.getTitle();
        } else {
            return ActivitiesSearchFragment.getTitle();
        }
    }
}