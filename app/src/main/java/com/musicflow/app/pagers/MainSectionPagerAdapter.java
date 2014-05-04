package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.DocumentationFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

public class MainSectionPagerAdapter extends SectionsPagerAdapter{
    protected Context context;

    public MainSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return DocumentationFragment.newInstance(position + 1);
            } else {
                return DocumentationFragment.newInstance(position + 1);
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
            return DocumentationFragment.getTitle();
        } else {
            return DocumentationFragment.getTitle();
        }
    }
}
