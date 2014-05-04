package com.musicflow.app.utility;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.musicflow.app.BeatsMusicFragment;
import com.musicflow.app.R;
import com.musicflow.app.SearchResultAdapter;
import com.musicflow.app.data.SearchResults;
import com.musicflow.app.mappers.SearchResultsMapper;
import com.musicflow.app.network.NetworkAdapter;

import java.util.HashMap;

/**
 * Created by whitneyimura on 5/1/14.
 */
public abstract class BaseSearchFragment extends BeatsMusicFragment {
    protected ListView searchResultsListView;
    protected EditText searchText;
    protected SearchResultNetworkAdapter networkRequest;

    protected SearchResults searchResults;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_search, container, false);
        searchResultsListView = (ListView) rootView.findViewById(R.id.results);
        searchText = (EditText) rootView.findViewById(R.id.search_box);
        searchText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    if (!searchResults.getSearchResults().isEmpty()) {
                        searchResults = new SearchResults();
                    }

                    if (networkRequest != null) {
                        networkRequest.cancel(true);
                    }

                    networkRequest = new SearchResultNetworkAdapter(getActivity());
                    networkRequest.execute(getNetworkUrl());

                    return true;
                }
                return false;
            }
        });
        searchResults = new SearchResults();
        networkRequest = new SearchResultNetworkAdapter(getActivity());
        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        searchResultsListView.setAdapter(new SearchResultAdapter(this.getActivity(), R.id.results, searchResults.getSearchResults()));
    }

    protected void hideKeyboard() {
        InputMethodManager manager =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }

    public abstract String getNetworkUrl();

    private class SearchResultNetworkAdapter extends NetworkAdapter {
        public SearchResultNetworkAdapter(Context context) {
            super(context, new SearchResultsMapper(), RequestType.GET, new HashMap<String, String>(), searchResults);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

}
