package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freethinking.beats.sdk.network.NetworkParts;
import com.musicflow.app.adapters.LargeImageAlbumAdapter;
import com.freethinking.beats.sdk.data.Albums;
import com.freethinking.beats.sdk.mappers.AlbumsMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a list of albums inside the Albums Activity.
 */
public class AlbumsFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ListView albumsListView;
    protected AlbumListNetworkAdapter networkRequest;
    protected Albums albums;

    public static AlbumsFragment newInstance(int sectionNumber) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Albums";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        albums = new Albums();

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        albumsListView = (ListView) rootView.findViewById(R.id.generic_list_view);
        networkRequest = new AlbumListNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.albumList(getActivity()));

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        albumsListView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.generic_list_view, albums.getAlbums()));
    }

    private class
            AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter(Context context) {
            super(context, new AlbumsMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }
}
