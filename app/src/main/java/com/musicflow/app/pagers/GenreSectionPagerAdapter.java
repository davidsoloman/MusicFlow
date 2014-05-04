package com.musicflow.app.pagers;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ArtistTracksFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.EssentialAlbumsFragment;
import com.musicflow.app.GenreFragment;
import com.musicflow.app.NoInternetFragment;

/**
 * Pages between fragments relating to genres, which are displayed in the Genre Activity.
 */
public class GenreSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public GenreSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return GenreFragment.newInstance(position + 1);
            } else if (position == 1) {
                return EssentialAlbumsFragment.newInstance(position + 1);
            } else {
                return ArtistTracksFragment.newInstance(position + 1);
            }
        } else {
            return NoInternetFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        if (position == 0) {
            return GenreFragment.getTitle();
        } else if (position == 1) {
            return EssentialAlbumsFragment.getTitle();
        } else {
            return ArtistTracksFragment.getTitle();
        }
    }
}
