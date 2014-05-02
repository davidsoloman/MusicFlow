package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;

import com.musicflow.app.AlbumDetailFragment;
import com.musicflow.app.AlbumReviewFragment;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.CompanionAlbumsFragment;

import java.util.Locale;

/**
 * Links an album detail view, album review, and related albums fragment.
 */
public class AlbumDetailSectionPagerAdapter extends SectionsPagerAdapter {

    public AlbumDetailSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (position == 0) {
            return AlbumDetailFragment.newInstance(position + 1);
        } else if (position == 1) {
            return AlbumReviewFragment.newInstance(position + 1);
        } else {
            return CompanionAlbumsFragment.newInstance(position + 1);
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
            return AlbumDetailFragment.getTitle();
        } else if (position == 1) {
            return AlbumReviewFragment.getTitle();
        } else {
            return CompanionAlbumsFragment.getTitle();
        }
    }
}
