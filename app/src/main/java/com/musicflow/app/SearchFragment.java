package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.vsco.cam.musicflow.app.R;

/**
 * Created by whitney on 4/4/14.
 */
public class SearchFragment extends Fragment{
    private ListView searchResults;

    public SearchFragment() {}

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_search, container, false);
        return rootView;
    }

    protected void initializeLayout() {
        final EditText searchText = (EditText) getView().findViewById(R.id.artist_search_box);
    }
}
