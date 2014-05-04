package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.musicflow.app.BeatsMusicFragment;

public abstract class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public abstract BeatsMusicFragment getItem(int position);

    @Override
    public abstract int getCount();

    @Override
    public abstract CharSequence getPageTitle(int position);
}
