package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whitney on 4/26/14.
 */
public class AlbumsSearchFragment extends BeatsMusicFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumsSearchFragment newInstance(int sectionNumber) {
        AlbumsSearchFragment fragment = new AlbumsSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_search, container, false);
        innerFrame.addView(rootView);
        return innerFrame;
    }
}
