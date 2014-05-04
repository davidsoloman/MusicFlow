package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ArtistTracksFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.EssentialAlbumsFragment;
import com.musicflow.app.GenreFragment;
import com.musicflow.app.NoInternetFragment;
import com.musicflow.app.PlaylistFragment;
import com.musicflow.app.SubscribedPlaylistFragment;

import java.util.Locale;

/**
 * Pages between fragments relating to genres, which are displayed in the Genre Activity.
 */
public class PlaylistSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public PlaylistSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return PlaylistFragment.newInstance(position + 1);
            } else {
                return SubscribedPlaylistFragment.newInstance(position + 1);
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
        if (position == 0) {
            return PlaylistFragment.getTitle();
        } else {
            return SubscribedPlaylistFragment.getTitle();
        }
    }
}
