package com.musicflow.app;

import android.os.Bundle;

import com.freethinking.beats.sdk.network.UrlFactory;
import com.musicflow.app.utility.BaseSearchFragment;

public class AlbumsSearchFragment extends BaseSearchFragment {
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
        return UrlFactory.searchPredictive(getActivity(), searchText.getText().toString());
    }
}