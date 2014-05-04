package com.musicflow.app;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.adapters.ArtistAdapter;
import com.musicflow.app.data.Artists;
import com.musicflow.app.mappers.ArtistsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

/**
 * Displays artists in a list view.
 */
public class ArtistsFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ArtistsResultNetworkAdapter networkRequest;
    protected Artists artists;
    protected ListView artistListView;

    public static ArtistsFragment newInstance(int sectionNumber) {
        ArtistsFragment fragment = new ArtistsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Artist";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        artists = new Artists();
        View rootView = inflater.inflate(R.layout.fragment_artists, container, false);
        artistListView = (ListView) rootView.findViewById(R.id.artists_fragment_list_view);
        networkRequest = new ArtistsResultNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.artistList());
        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        artistListView.setAdapter(new ArtistAdapter(this.getActivity(),
                R.id.artists_fragment_list_view, artists.getArtists()));
    }

    private class ArtistsResultNetworkAdapter extends NetworkAdapter {
        public ArtistsResultNetworkAdapter(Context context) {
            super(context, new ArtistsMapper(), RequestType.GET, new HashMap<String, String>(),
                    artists);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
