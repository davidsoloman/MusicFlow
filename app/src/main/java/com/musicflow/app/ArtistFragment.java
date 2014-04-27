package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Displays artists in a list view. 
 */
public class ArtistFragment extends BeatsMusicFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ArtistFragment newInstance(int sectionNumber) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);
        innerFrame.addView(rootView);
        return innerFrame;
    }
}
