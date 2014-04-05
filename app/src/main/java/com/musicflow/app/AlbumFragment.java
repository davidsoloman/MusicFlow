package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.vsco.cam.musicflow.app.R;

/**
 * Created by whitney on 4/3/14.
 */
public class AlbumFragment extends Fragment {
    public AlbumFragment() {
    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumFragment newInstance(int sectionNumber) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_albums, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(gridView.getContext()));
        return rootView;
    }
}
