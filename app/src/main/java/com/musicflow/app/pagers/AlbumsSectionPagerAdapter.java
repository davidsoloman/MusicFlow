package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;

import com.musicflow.app.AlbumsFragment;
import com.musicflow.app.AlbumsSearchFragment;
import com.musicflow.app.BeatsMusicFragment;

import java.util.Locale;

public class AlbumsSectionPagerAdapter extends SectionsPagerAdapter{
    public AlbumsSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (position == 0) {
            return AlbumsFragment.newInstance(position + 1);
        } else {
            return AlbumsSearchFragment.newInstance(position + 1);
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
