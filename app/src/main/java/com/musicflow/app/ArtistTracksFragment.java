package com.musicflow.app;

import java.util.HashMap;

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

/**
 * Displays a list of tracks for which the artist is credited.
 */
public class ArtistTracksFragment extends BeatsMusicFragment {
    protected ArtistTrackNetworkAdapter networkRequest;
    protected Tracks tracks;
    protected ListView trackListView;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ArtistTracksFragment newInstance(int sectionNumber) {
        ArtistTracksFragment fragment = new ArtistTracksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        tracks = new Tracks();

        View rootView = inflater.inflate(R.layout.fragment_tracks, container, false);
        trackListView = (ListView) rootView.findViewById(R.id.tracks_fragment_list_view);

        String artistId = getActivity().getIntent().getStringExtra("ArtistId");

        networkRequest = new ArtistTrackNetworkAdapter();
        networkRequest.execute(UrlFactory.artistTracks(artistId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        trackListView.setAdapter(new TracksAdapter(this.getActivity(), R.id.track_list_view, tracks.getTracks()));
    }

    private class ArtistTrackNetworkAdapter extends NetworkAdapter {
        public ArtistTrackNetworkAdapter() {
            super(new TracksMapper(), RequestType.GET, new HashMap<String, String>(), tracks);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

    public static CharSequence getTitle() {
        return "Artist Tracks";
    }
}
