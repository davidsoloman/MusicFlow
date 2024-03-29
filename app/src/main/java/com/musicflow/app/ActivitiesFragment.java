package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freethinking.beats.sdk.network.NetworkParts;
import com.musicflow.app.adapters.ActivitiesAdapter;
import com.freethinking.beats.sdk.data.Activities;
import com.freethinking.beats.sdk.mappers.ActivitiesMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.UrlFactory;

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

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        activitiesListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        networkRequest = new ActivitiesNetworkAdapter(getActivity());
        networkRequest
                .execute(UrlFactory.activitiesList(getActivity()));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        activitiesListView.setAdapter(new ActivitiesAdapter(this.getActivity(),
                R.id.generic_list_view, activities.getActivities()));
    }

    private class ActivitiesNetworkAdapter extends NetworkAdapter {
        public ActivitiesNetworkAdapter(Context context) {
            super(context, new ActivitiesMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(),
                    activities);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

}
