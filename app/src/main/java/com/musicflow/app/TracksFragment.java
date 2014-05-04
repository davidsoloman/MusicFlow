package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.adapters.TracksAdapter;
import com.musicflow.app.data.Tracks;
import com.musicflow.app.mappers.TracksMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class TracksFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ListView tracksListView;
    protected TrackListNetworkAdapter networkRequest;
    protected Tracks tracks;

    public static TracksFragment newInstance(int sectionNumber) {
        TracksFragment fragment = new TracksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Tracks";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        tracks = new Tracks();

        View rootView = inflater.inflate(R.layout.fragment_tracks, container, false);
        tracksListView = (ListView) rootView.findViewById(R.id.tracks_fragment_list_view);
        networkRequest = new TrackListNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.trackList());

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        tracksListView.setAdapter(new TracksAdapter(this.getActivity(), R.id.tracks_fragment_list_view, tracks.getTracks()));
    }

    private class TrackListNetworkAdapter extends NetworkAdapter {

        public TrackListNetworkAdapter(Context context) {
            super(context, new TracksMapper(), RequestType.GET, new HashMap<String, String>(), tracks);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }
}
