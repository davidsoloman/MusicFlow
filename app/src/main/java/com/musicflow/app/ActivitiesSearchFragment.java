package com.musicflow.app;

import android.os.Bundle;

import com.musicflow.app.network.UrlFactory;
import com.musicflow.app.utility.BaseSearchFragment;

public class ActivitiesSearchFragment extends BaseSearchFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumsSearchFragment newInstance(int sectionNumber) {
        AlbumsSearchFragment fragment = new AlbumsSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Search";
    }

    public String getNetworkUrl() {
        return UrlFactory.searchPredictive(searchText.getText().toString());
    }
}
