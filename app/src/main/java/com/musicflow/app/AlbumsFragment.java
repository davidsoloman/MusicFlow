package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.freethinking.beats.sdk.network.NetworkParts;
import com.musicflow.app.adapters.LargeImageAlbumAdapter;
import com.freethinking.beats.sdk.data.Albums;
import com.freethinking.beats.sdk.mappers.AlbumsMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.musicflow.app.utility.DoubleActionPullToRefresh;

import java.util.HashMap;

/**
 * Displays a list of albums inside the Albums Activity.
 */
public class AlbumsFragment extends BeatsMusicFragment implements DoubleActionPullToRefresh.OnRefreshListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ListView albumsListView;
    protected AlbumListNetworkAdapter networkRequest;
    protected Albums albums;

    protected DoubleActionPullToRefresh swipeRefreshLayout;

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
        swipeRefreshLayout = (DoubleActionPullToRefresh) rootView.findViewById(R.id.generic_list_view_frame);
        swipeRefreshLayout.setOnRefreshListener(this);
        albumsListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        networkRequest = new AlbumListNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.albumList(getActivity()));

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        albumsListView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.generic_list_view, albums.getAlbums()));
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Phase One", Toast.LENGTH_LONG).show();
//        networkRequest = new AlbumListNetworkAdapter(getActivity());
//        networkRequest.execute(UrlFactory.albumList(getActivity()));
    }

    @Override
    public void onPhaseTwo() {
        Toast.makeText(getActivity(), "Phase Two", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPercentageChanged(float phaseOne, float phaseTwo) {
        Toast.makeText(getActivity(), "1: " + phaseOne + ", 2: " + phaseTwo, Toast.LENGTH_SHORT).show();
    }

    private class
            AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter(Context context) {
            super(context, new AlbumsMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            setUpAdapter();
        }
    }
}
