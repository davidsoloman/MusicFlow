package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freethinking.beats.sdk.data.Tracks;
import com.freethinking.beats.sdk.mappers.TracksMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.NetworkParts;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.musicflow.app.adapters.TracksAdapter;

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

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        tracksListView = (ListView) rootView.findViewById(R.id.generic_list_view);
        networkRequest = new TrackListNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.trackList(getActivity()));

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        tracksListView.setAdapter(new TracksAdapter(this.getActivity(), R.id.generic_list_view, tracks.getTracks()));
    }

    private class TrackListNetworkAdapter extends NetworkAdapter {

        public TrackListNetworkAdapter(Context context) {
            super(context, new TracksMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), tracks);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }
}
