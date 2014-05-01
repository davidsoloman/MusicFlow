package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.data.AlbumList;
import com.musicflow.app.mappers.AlbumsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class AlbumsFragment extends BeatsMusicFragment {
    protected ListView albumsListView; 
    protected AlbumListNetworkAdapter networkRequest;
    protected AlbumList albums;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumsFragment newInstance(int sectionNumber) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        albums = new AlbumList();

        View rootView = inflater.inflate(R.layout.fragment_albums, container, false);
        albumsListView = (ListView) rootView.findViewById(R.id.albums_fragment_list_view);
        networkRequest = new AlbumListNetworkAdapter();
        networkRequest.execute(UrlFactory.albumList());

        innerFrame.addView(rootView);
        return innerFrame;
        
    }

    private void setUpAdapter() {
        albumsListView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.albums_fragment_list_view, albums.getAlbums()));
    }

    private class AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter() {
            super(new AlbumsMapper(), RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

    public static CharSequence getTitle() {
        return "Albums";
    }
}
