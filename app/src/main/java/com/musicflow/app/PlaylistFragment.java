package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.musicflow.app.adapters.PlaylistAdapter;
import com.musicflow.app.data.Playlist;
import com.musicflow.app.data.Playlists;
import com.musicflow.app.mappers.PlaylistsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class PlaylistFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    protected ListView playlistListView;
    protected Playlists playlists;
    protected PlaylistNetworkRequest networkRequest;

    public static PlaylistFragment newInstance(int sectionNumber) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Playlists";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        playlists = new Playlists();

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        playlistListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        String prefKey = getString(R.string.user_preferences_key);
        String userId = getActivity().getSharedPreferences(prefKey, Context.MODE_PRIVATE).getString("user_id", "");

        networkRequest = new PlaylistNetworkRequest(getActivity());
        networkRequest.execute(UrlFactory.usersPlaylists(userId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        playlistListView.setAdapter(new PlaylistAdapter(this.getActivity(), R.id.generic_list_view,
                playlists.getPlaylists()));
    }

    private class PlaylistNetworkRequest extends NetworkAdapter {
        @Override
        public Boolean authRequired() {
            return true;
        }

        public PlaylistNetworkRequest(Context context) {
            super(context, new PlaylistsMapper(), RequestType.GET, new HashMap<String, String>(),
                    playlists);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
