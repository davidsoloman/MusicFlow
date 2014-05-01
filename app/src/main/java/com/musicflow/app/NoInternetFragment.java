package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by whitneyimura on 4/30/14.
 */
public class NoInternetFragment  extends BeatsMusicFragment {

    public NoInternetFragment() {}

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static NoInternetFragment newInstance(int sectionNumber) {
        NoInternetFragment fragment = new NoInternetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_no_internet, container, false);
        return rootView;
    }
}
