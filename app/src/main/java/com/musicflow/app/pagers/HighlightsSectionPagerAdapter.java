package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ActivitiesFragment;
import com.musicflow.app.ArtistTracksFragment;
import com.musicflow.app.ArtistViewFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.EssentialAlbumsFragment;
import com.musicflow.app.HighlightsFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

/**
 * Pages between the different highlights fragments which live on the Highlights Activity.
 */
public class HighlightsSectionPagerAdapter  extends SectionsPagerAdapter{
    protected Context context;

    public HighlightsSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return HighlightsFragment.newInstance(position + 1);
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
            return HighlightsFragment.getTitle();
        } else if (position == 1) {
            return EssentialAlbumsFragment.getTitle();
        } else {
            return ArtistTracksFragment.getTitle();
        }
    }
}
