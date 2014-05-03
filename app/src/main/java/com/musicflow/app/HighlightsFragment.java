package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.adapters.HighlightsAdapter;
import com.musicflow.app.adapters.LargeImageAlbumAdapter;
import com.musicflow.app.data.Albums;
import com.musicflow.app.data.Highlights;
import com.musicflow.app.mappers.AlbumsMapper;
import com.musicflow.app.mappers.HighlightsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a view of highlights.
 */
public class HighlightsFragment extends BeatsMusicFragment{

    protected ListView highlightsListView;
    protected HighlightsNetworkAdapter networkRequest;
    protected Highlights highlights;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static HighlightsFragment newInstance(int sectionNumber) {
        HighlightsFragment fragment = new HighlightsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        highlights = new Highlights();

        View rootView = inflater.inflate(R.layout.fragment_highlights, container, false);
        highlightsListView = (ListView) rootView.findViewById(R.id.fragment_highlights_list_view);
        networkRequest = new HighlightsNetworkAdapter();
        networkRequest.execute(UrlFactory.highlightsFeatured());

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        highlightsListView.setAdapter(new HighlightsAdapter(this.getActivity(), R.id.fragment_highlights_list_view, highlights.getHighlights()));
    }

    private class HighlightsNetworkAdapter extends NetworkAdapter {

        public HighlightsNetworkAdapter() {
            super(new HighlightsMapper(), RequestType.GET, new HashMap<String, String>(), highlights);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

    public static CharSequence getTitle() {
        return "Highlights";
    }
}
