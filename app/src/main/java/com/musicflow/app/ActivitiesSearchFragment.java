package com.musicflow.app;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public String getNetworkUrl() {
        return UrlFactory.search(searchText.getText().toString());
    }

    public static CharSequence getTitle() {
        return "Search";
    }
}
