package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.freethinking.beats.sdk.network.NetworkParts;
import com.musicflow.app.adapters.PlaylistAdapter;
import com.freethinking.beats.sdk.data.Playlists;
import com.freethinking.beats.sdk.mappers.PlaylistsMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.UrlFactory;

import java.util.HashMap;

public class SubscribedPlaylistFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    protected ListView playlistListView;
    protected FrameLayout genericNoDataFrame;
    protected FrameLayout genericListViewFrame;
    protected TextView genericNoDataTextView;
    protected Playlists playlists;
    protected PlaylistNetworkRequest networkRequest;

    public static SubscribedPlaylistFragment newInstance(int sectionNumber) {
        SubscribedPlaylistFragment fragment = new SubscribedPlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Subscribed Playlists";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        playlists = new Playlists();

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        genericNoDataFrame = (FrameLayout) rootView.findViewById(R.id.generic_no_data_frame);
        genericListViewFrame = (FrameLayout) rootView.findViewById(R.id.generic_list_view_frame);
        genericNoDataTextView = (TextView) rootView.findViewById(R.id.generic_no_data_text);

        playlistListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        String prefKey = getString(R.string.user_preferences_key);
        String userId = getActivity().getSharedPreferences(prefKey, Context.MODE_PRIVATE).getString("user_id", "");

        networkRequest = new PlaylistNetworkRequest(getActivity());
        networkRequest.execute(UrlFactory.usersSubscribedPlaylists(userId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        if (playlists.getPlaylists().isEmpty()) {
            genericListViewFrame.setVisibility(View.GONE);
            genericNoDataFrame.setVisibility(View.VISIBLE);
            genericNoDataTextView.setText(getActivity().getString(R.string.no_user_subscribed_playlists));
        } else {
            genericNoDataFrame.setVisibility(View.GONE);
            genericListViewFrame.setVisibility(View.VISIBLE);
            playlistListView.setAdapter(new PlaylistAdapter(this.getActivity(), R.id.generic_list_view,
                    playlists.getPlaylists()));
        }
    }

    private class PlaylistNetworkRequest extends NetworkAdapter {
        @Override
        public Boolean authRequired() {
            return true;
        }

        public PlaylistNetworkRequest(Context context) {
            super(context, new PlaylistsMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(),
                    playlists);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
