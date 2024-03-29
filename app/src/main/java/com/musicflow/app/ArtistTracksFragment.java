package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freethinking.beats.sdk.network.NetworkParts;
import com.musicflow.app.adapters.TracksAdapter;
import com.freethinking.beats.sdk.data.Tracks;
import com.freethinking.beats.sdk.mappers.TracksMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a list of tracks for which the artist is credited.
 */
public class ArtistTracksFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ArtistTrackNetworkAdapter networkRequest;
    protected Tracks tracks;
    protected ListView trackListView;

    public static ArtistTracksFragment newInstance(int sectionNumber) {
        ArtistTracksFragment fragment = new ArtistTracksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Artist Tracks";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        tracks = new Tracks();

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        trackListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        String artistId = getActivity().getIntent().getStringExtra("ArtistId");

        networkRequest = new ArtistTrackNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.artistTracks(getActivity(), artistId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        trackListView.setAdapter(new TracksAdapter(this.getActivity(), R.id.track_list_view, tracks.getTracks()));
    }

    private class ArtistTrackNetworkAdapter extends NetworkAdapter {
        public ArtistTrackNetworkAdapter(Context context) {
            super(context, new TracksMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), tracks);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
