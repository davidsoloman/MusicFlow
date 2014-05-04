package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.adapters.GenreAdapter;
import com.musicflow.app.data.Genres;
import com.musicflow.app.mappers.GenresMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a list of genres.
 */
public class GenreFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    protected GenreResultsNetworkAdapter networkRequest;
    protected Genres genres;
    protected ListView genreListView;

    public static GenreFragment newInstance(int sectionNumber) {
        GenreFragment fragment = new GenreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Genres";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        genres = new Genres();

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        genreListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        networkRequest = new GenreResultsNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.genresCollection());

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadViewData() {
        genreListView.setAdapter(new GenreAdapter(this.getActivity(), R.id.generic_list_view,
                genres.getGenres()));
    }

    private class GenreResultsNetworkAdapter extends NetworkAdapter {
        public GenreResultsNetworkAdapter(Context context) {
            super(context, new GenresMapper(), RequestType.GET, new HashMap<String, String>(),
                    genres);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }
}
