package com.musicflow.app.pagers;

import android.support.v4.app.FragmentManager;

import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.DocumentationFragment;

import java.util.Locale;

public class MainSectionPagerAdapter extends SectionsPagerAdapter{
    public MainSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (position == 0) {
            return DocumentationFragment.newInstance(position + 1);
        } else {
            return DocumentationFragment.newInstance(position + 1);
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
            return DocumentationFragment.getTitle();
        } else {
            return DocumentationFragment.getTitle();
        }
    }
}
