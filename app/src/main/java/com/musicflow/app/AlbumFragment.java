package com.musicflow.app;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.musicflow.app.data.AlbumList;
import com.musicflow.app.mappers.AlbumListMapper;
import com.musicflow.app.network.NetworkAdapter;

public class AlbumFragment extends Fragment {

    protected GridView gridView;
    protected AlbumListNetworkAdapter networkRequest;
    protected AlbumList albums;

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
        super.onCreateView(inflater, container, savedInstanceState);
        albums = new AlbumList();

        View rootView = inflater.inflate(R.layout.activity_albums, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridview);

        networkRequest = new AlbumListNetworkAdapter();
        networkRequest.execute(this.getString(R.string.all_albums));

        return rootView;
    }

    private void setUpAdapter() {
        gridView.setAdapter(new ImageAdapter(this.getActivity(), R.id.gridview, albums.getCoverArt()));
    }

    private class AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter() {
            super(new AlbumListMapper(), RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

}
