package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.musicflow.app.adapters.LargeImageAlbumAdapter;
import com.musicflow.app.data.Activities;
import com.musicflow.app.data.Albums;
import com.musicflow.app.mappers.AlbumsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a grid view of companion albums.
 */
public class CompanionAlbumsFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected CompanionAlbumsNetworkAdapter networkRequest;
    protected Activities activities;
    protected Albums albums;
    protected ListView companionAlbumsGridView;

    public static CompanionAlbumsFragment newInstance(int sectionNumber) {
        CompanionAlbumsFragment fragment = new CompanionAlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        albums = new Albums();
        String albumId = getActivity().getIntent().getStringExtra("AlbumId");
        View rootView = inflater.inflate(R.layout.fragment_albums, container, false);

        companionAlbumsGridView = (ListView) rootView.findViewById(R.id.albums_fragment_list_view);

        networkRequest = new CompanionAlbumsNetworkAdapter();
        networkRequest.execute(UrlFactory.albumCompanionAlbums(albumId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void setUpAdapter() {
        companionAlbumsGridView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.albums_fragment_list_view, albums.getAlbums()));
    }

    private class CompanionAlbumsNetworkAdapter extends NetworkAdapter {

        public CompanionAlbumsNetworkAdapter() {
            super(new AlbumsMapper(), RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

    public static CharSequence getTitle() {
        return "Companion Albums";
    }
}
