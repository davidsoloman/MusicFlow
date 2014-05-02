package com.musicflow.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.musicflow.app.adapters.TracksAdapter;
import com.musicflow.app.data.Albums;
import com.musicflow.app.data.Tracks;
import com.musicflow.app.mappers.TracksMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Displays a list of tracks, an album image, album title, and artist name.
 */
public class AlbumDetailFragment extends BeatsMusicFragment {
    protected ImageView albumCoverArt;
    protected TextView artistName;
    protected TextView albumTitle;
    protected ListView trackList;
    protected TrackListNetworkAdapter networkRequest;
    protected Tracks tracks;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumDetailFragment newInstance(int sectionNumber) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        String albumId = getActivity().getIntent().getStringExtra("AlbumId");
        String artistNameText = getActivity().getIntent().getStringExtra("ArtistName");
        String albumTitleText = getActivity().getIntent().getStringExtra("AlbumTitle");

        tracks = new Tracks();

        View rootView = inflater.inflate(R.layout.activity_album_detail, container, false);
        trackList = (ListView) rootView.findViewById(R.id.track_list_view);

        albumCoverArt = (ImageView) rootView.findViewById(R.id.album_cover_art);
        Picasso.with(getActivity()).load(UrlFactory.imageUrl(albumId, UrlFactory.EntityType.ALBUM, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.LARGE)).placeholder(R.drawable.placeholder).fit().centerCrop().into(albumCoverArt);

        artistName = (TextView) rootView.findViewById(R.id.artist_name);
        artistName.setText(artistNameText + getResources().getString(R.string.spacer));

        albumTitle = (TextView) rootView.findViewById(R.id.album_title);
        albumTitle.setText(albumTitleText);

        networkRequest = new TrackListNetworkAdapter();
        networkRequest.execute(UrlFactory.albumTracks(albumId));

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void setUpAdapter() {
        trackList.setAdapter(new TracksAdapter(this.getActivity(), R.id.track_list_view, tracks.getTracks()));
    }

    private class TrackListNetworkAdapter extends NetworkAdapter {

        public TrackListNetworkAdapter() {
            super(new TracksMapper(), RequestType.GET, new HashMap<String, String>(), tracks);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setUpAdapter();
        }
    }

    public static CharSequence getTitle() {
        return "AlbumDetail";
    }
}
