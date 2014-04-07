package com.musicflow.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.musicflow.app.data.SearchResult;
import com.musicflow.app.data.SearchResults;
import com.musicflow.app.mappers.SearchResultsMapper;
import com.musicflow.app.network.NetworkAdapter;

public class SearchFragment extends Fragment{
    protected ListView searchResultsListView;
    protected EditText searchText;
    protected SearchResultNetworkAdapter networkRequest;
    protected SearchResults searchResults;
    protected ImageButton searchButton;

    public SearchFragment() {}

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_search, container, false);
        searchResultsListView = (ListView) rootView.findViewById(R.id.results);
        searchText = (EditText) rootView.findViewById(R.id.artist_search_box);
        searchResults = new SearchResults();
        networkRequest = new SearchResultNetworkAdapter();
        searchButton = (ImageButton) rootView.findViewById(R.id.search_magnifier);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchText.getText().toString().length()!=0) {
                    if (!searchResults.getSearchResults().isEmpty()) {
                        // Throw away the previous search results
                        searchResults = new SearchResults();
                    }
                    if (networkRequest != null) {
                        networkRequest.cancel(true);
                    }
                    networkRequest = new SearchResultNetworkAdapter();
                    networkRequest.execute("https://partner.api.beatsmusic.com/v1/api/search/predictive?q=" + Uri.encode(searchText.getText().toString()) + "&client_id=frksnm8edw2t8ddebhkqkjwk");
                }
            }
        });
        return rootView;
    }

    private void loadViewData() {
        searchResultsListView.setAdapter(new SearchResultAdapter(this.getActivity(), R.id.results, searchResults.getSearchResults()));
    }

    private class SearchResultNetworkAdapter extends NetworkAdapter {
        public SearchResultNetworkAdapter() {
            super(new SearchResultsMapper(), RequestType.GET, new HashMap<String, String>(), searchResults);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
