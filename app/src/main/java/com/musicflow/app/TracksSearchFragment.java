package com.musicflow.app;

import android.os.Bundle;

import com.musicflow.app.network.UrlFactory;
import com.musicflow.app.utility.BaseSearchFragment;

public class TracksSearchFragment extends BaseSearchFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TracksSearchFragment newInstance(int sectionNumber) {
        TracksSearchFragment fragment = new TracksSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public String getNetworkUrl() {
        return UrlFactory.searchPredictive(searchText.getText().toString());
    }

    public static CharSequence getTitle() {
        return "Search";
    }
}

