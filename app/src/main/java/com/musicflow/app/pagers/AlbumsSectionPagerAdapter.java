package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.AlbumsFragment;
import com.musicflow.app.AlbumsSearchFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

/**
 * Switches between the different Albums fragments that are displayed on the Album activity.
 */
public class AlbumsSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public AlbumsSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return AlbumsFragment.newInstance(position + 1);
            } else {
                return AlbumsSearchFragment.newInstance(position + 1);
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
            return AlbumsFragment.getTitle();
        } else {
            return AlbumsSearchFragment.getTitle();
        }
    }
}
