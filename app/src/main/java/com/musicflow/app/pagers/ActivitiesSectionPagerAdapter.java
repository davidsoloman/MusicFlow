package com.musicflow.app.pagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.musicflow.app.ActivitiesFragment;
import com.musicflow.app.ActivitiesSearchFragment;
import com.musicflow.app.BeatsMusicActivity;
import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.NoInternetFragment;

import java.util.Locale;

/**
 * Pages between the Activities Fragment and Activities Search Fragment.  Shows a no internet
 * Fragment if there is no internet available.
 */
public class ActivitiesSectionPagerAdapter extends SectionsPagerAdapter {
    protected Context context;

    public ActivitiesSectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public BeatsMusicFragment getItem(int position) {
        if (BeatsMusicActivity.isNetworkAvailable(context)) {
            if (position == 0) {
                return ActivitiesFragment.newInstance(position + 1);
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
