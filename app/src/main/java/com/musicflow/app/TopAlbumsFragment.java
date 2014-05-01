package com.musicflow.app;

import java.util.HashMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.musicflow.app.data.Albums;
import com.musicflow.app.mappers.AlbumsMapper;
import com.musicflow.app.network.NetworkAdapter;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class TopAlbumsFragment extends BeatsMusicFragment implements OnRefreshListener{

    protected GridView gridView;
    protected AlbumListNetworkAdapter networkRequest;
    protected Albums albums;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PullToRefreshLayout pullToRefreshLayout;

    public static TopAlbumsFragment newInstance(int sectionNumber) {
        TopAlbumsFragment fragment = new TopAlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        albums = new Albums();

        View rootView = inflater.inflate(R.layout.fragment_top_albums, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridview);

        networkRequest = new AlbumListNetworkAdapter();
        networkRequest.execute(this.getString(R.string.albums_collection));

        pullToRefreshLayout = (PullToRefreshLayout) rootView.findViewById(R.id.ptr_layout);

        ActionBarPullToRefresh.from(getActivity())
                .listener(this)
                .allChildrenArePullable()
                .setup(pullToRefreshLayout);

        innerFrame.addView(rootView);

        return innerFrame;
    }

    private void setUpAdapter() {
        gridView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.gridview, albums.getAlbums()));
    }

    @Override
    public void onRefreshStarted(View view) {
        if (!albums.getAlbums().isEmpty()) {
            albums = new Albums();
        }
        if (networkRequest != null) {
            networkRequest.cancel(true);
        }
        networkRequest = new AlbumListNetworkAdapter();
        networkRequest.execute(this.getString(R.string.albums_collection));
    }

    private class AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter() {
            super(new AlbumsMapper(), RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
            if (pullToRefreshLayout.isRefreshing()) {
                pullToRefreshLayout.setRefreshComplete();
            }
        }
    }

    public static CharSequence getTitle() {
        return "Albums";
    }
}
