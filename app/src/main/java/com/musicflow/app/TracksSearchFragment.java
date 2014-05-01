package com.musicflow.app;

import android.content.Context;
import android.net.Uri;
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

import com.musicflow.app.data.SearchResults;
import com.musicflow.app.mappers.SearchResultsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.utility.BaseSearchFragment;

import java.util.HashMap;

public class TracksSearchFragment extends BaseSearchFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TracksSearchFragment newInstance(int sectionNumber) {
        TracksSearchFragment fragment = new TracksSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public String getNetworkUrl() {
        return "https://partner.api.beatsmusic.com/v1/api/search/predictive?q=" + Uri.encode(searchText.getText().toString()) + "&client_id=frksnm8edw2t8ddebhkqkjwk";
    }

    public static CharSequence getTitle() {
        return "Search";
    }
}

