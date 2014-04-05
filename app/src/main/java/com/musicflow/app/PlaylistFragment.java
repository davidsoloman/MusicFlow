package com.musicflow.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.vsco.cam.musicflow.app.R;

/**
 * Created by whitney on 4/3/14.
 */
public class PlaylistFragment extends Fragment {

    public PlaylistFragment() {}

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ListView listView;

    public static PlaylistFragment newInstance(int sectionNumber) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_playlist, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.playlist_textview);
        return rootView;
    }

    protected void initializeListView() {
        listView = (ListView) getView().findViewById(R.id.playlist_listview);
    }
}
