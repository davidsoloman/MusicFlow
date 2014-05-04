package com.musicflow.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Base fragment for all other fragments.
 */
public abstract class BeatsMusicFragment extends Fragment {

    protected static final String ARG_POSITION = "position";

    protected int position;
    protected FrameLayout innerFrame;

    public static CharSequence getTitle() {
        return "Beats Music";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);

        innerFrame = fl;
        innerFrame.setBackgroundColor(Color.BLACK);

        return fl;
    }
}
