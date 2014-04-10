package com.musicflow.app;

import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.musicflow.app.data.AlbumList;
import com.musicflow.app.mappers.AlbumListMapper;
import com.musicflow.app.network.NetworkAdapter;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class AlbumFragment extends Fragment implements OnRefreshListener{

    protected GridView gridView;
    protected AlbumListNetworkAdapter networkRequest;
    protected AlbumList albums;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PullToRefreshLayout pullToRefreshLayout;

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

        pullToRefreshLayout = (PullToRefreshLayout) rootView.findViewById(R.id.ptr_layout);

        ActionBarPullToRefresh.from(getActivity())
                .listener(this)
                .allChildrenArePullable()
                .setup(pullToRefreshLayout);

        return rootView;
    }

    private void setUpAdapter() {
        gridView.setAdapter(new ImageAdapter(this.getActivity(), R.id.gridview, albums.getAlbums()));
    }

    @Override
    public void onRefreshStarted(View view) {
        if (!albums.getAlbums().isEmpty()) {
            albums = new AlbumList();
        }
        if (networkRequest != null) {
            networkRequest.cancel(true);
        }
        networkRequest = new AlbumListNetworkAdapter();
        networkRequest.execute(this.getString(R.string.all_albums));
    }

    private class AlbumListNetworkAdapter extends NetworkAdapter {

        public AlbumListNetworkAdapter() {
            super(new AlbumListMapper(), RequestType.GET, new HashMap<String, String>(), albums);
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

}
