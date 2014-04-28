package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.data.Activities;
import com.musicflow.app.mappers.ActivitiesMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.tests.ActivitiesAdapter;

import java.util.HashMap;

/**
 * Displays a list of beats music activities. 
 */
public class ActivitiesFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ActivitiesNetworkAdapter networkRequest;
    protected Activities activities;
    protected ListView activitiesListView;

    public static ActivitiesFragment newInstance(int sectionNumber) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Activities";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activities = new Activities();

        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);
        activitiesListView = (ListView) rootView.findViewById(R.id.activities_fragment_list_view);

        networkRequest = new ActivitiesNetworkAdapter();
        networkRequest
                .execute("https://partner.api.beatsmusic.com/v1/api/activities?client_id=frksnm8edw2t8ddebhkqkjwk");

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        activitiesListView.setAdapter(new ActivitiesAdapter(this.getActivity(),
                R.id.artist_fragment_list_view, activities.getActivities()));
    }

    private class ActivitiesNetworkAdapter extends NetworkAdapter {
        public ActivitiesNetworkAdapter() {
            super(new ActivitiesMapper(), RequestType.GET, new HashMap<String, String>(),
                    activities);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

}
