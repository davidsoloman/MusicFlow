package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.musicflow.app.adapters.LargeImageAlbumAdapter;
import com.musicflow.app.data.Albums;
import com.musicflow.app.mappers.AlbumsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a list view of essential albums for an artist.
 */
public class EssentialAlbumsFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected Albums albums;
    protected EssentialAlbumsNetworkAdapter networkRequest;
    protected ListView essentialAlbumsListView;

    public static EssentialAlbumsFragment newInstance(int sectionNumber) {
        EssentialAlbumsFragment fragment = new EssentialAlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static CharSequence getTitle() {
        return "Essential Albums";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        albums = new Albums();
        String artistId = getActivity().getIntent().getStringExtra("ArtistId");
        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        essentialAlbumsListView = (ListView) rootView.findViewById(R.id.generic_list_view);

        networkRequest = new EssentialAlbumsNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.artistEssentialAlbums(artistId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void setUpAdapter() {
        essentialAlbumsListView.setAdapter(new LargeImageAlbumAdapter(this.getActivity(), R.id.generic_list_view, albums.getAlbums()));
    }

    private class EssentialAlbumsNetworkAdapter extends NetworkAdapter {

        public EssentialAlbumsNetworkAdapter(Context context) {
            super(context, new AlbumsMapper(), RequestType.GET, new HashMap<String, String>(), albums);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

}
