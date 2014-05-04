package com.musicflow.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.musicflow.app.adapters.PlaylistAdapter;
import com.musicflow.app.data.Playlists;
import com.musicflow.app.mappers.ActivityMapper;
import com.musicflow.app.mappers.PlaylistsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a list view of Beats Music Activities.
 */
public class ActivityViewActivity extends Activity {
    protected ActivityNetworkRequest activityNetworkRequest;
    protected PlaylistNetworkRequest playlistNetworkRequest;

    protected com.musicflow.app.data.Activity activity;
    protected String activityId;
    protected Playlists playlists;

    protected ListView playlistListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_view);
        activity = new com.musicflow.app.data.Activity();
        playlists = new Playlists();
        activityId = getIntent().getStringExtra("ActivityId");

        playlistListView = (ListView) findViewById(R.id.activity_activity_list_view);

        activityNetworkRequest = new ActivityNetworkRequest(this);
        activityNetworkRequest.execute(UrlFactory.activity(activityId));

        playlistNetworkRequest = new PlaylistNetworkRequest(this);
        playlistNetworkRequest.execute(UrlFactory.activityEditorialPlaylists(activityId));

        setTitle("Activity");
    }

    private void loadViewData() {
        setTitle(activity.getName());
    }

    private void loadPlaylistViewData() {
        playlistListView.setAdapter(new PlaylistAdapter(this, R.id.activity_activity_list_view, playlists.getPlaylists()));
    }

    private class ActivityNetworkRequest extends NetworkAdapter {
        public ActivityNetworkRequest(Context context) {
            super(context, new ActivityMapper(), RequestType.GET, new HashMap<String, String>(), activity);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

    private class PlaylistNetworkRequest extends NetworkAdapter {
        public PlaylistNetworkRequest(Context context) {
            super(context, new PlaylistsMapper(), RequestType.GET, new HashMap<String, String>(), playlists);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadPlaylistViewData();
        }
    }
}
