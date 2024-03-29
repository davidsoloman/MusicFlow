package com.musicflow.app;

import android.os.Bundle;

import com.freethinking.beats.sdk.network.UrlFactory;
import com.musicflow.app.utility.BaseSearchFragment;

/**
 * Displays a search fragment connected to the Artist Activity where a user can search.
 */
public class ArtistsSearchFragment extends BaseSearchFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ArtistsSearchFragment newInstance(int sectionNumber) {
        ArtistsSearchFragment fragment = new ArtistsSearchFragment();
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
