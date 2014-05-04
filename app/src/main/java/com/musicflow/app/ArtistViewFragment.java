package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Artist;
import com.musicflow.app.data.BioWrapper;
import com.musicflow.app.mappers.ArtistMapper;
import com.musicflow.app.mappers.BioMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Displays a view with artist image, name, info, and artist Bio.  Accessible by clicking
 * an artist item in the artist fragment's list view.
 */
public class ArtistViewFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected ImageView artistHeroImage;
    protected TextView artistName;
    protected TextView artistFollowerCount;
    protected TextView artistTotalAlbums;
    protected Artist artist;
    protected BioWrapper bios;
    protected WebView artistBio;
    protected ArtistNetworkAdapter networkRequest;
    protected BioNetworkAdapter bioNetworkRequest;
    protected TextView totalEps;
    protected TextView totalTracks;
    protected TextView popularity;

    public static ArtistViewFragment newInstance(int sectionNumber) {
        ArtistViewFragment fragment = new ArtistViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View rootView = inflater.inflate(R.layout.activity_album_view, container, false);
        String artistId = getActivity().getIntent().getStringExtra("ArtistId");
        artistHeroImage = (ImageView) rootView.findViewById(R.id.artist_hero_image);
        artistName = (TextView) rootView.findViewById(R.id.artist_name);
        artistTotalAlbums = (TextView) rootView.findViewById(R.id.artist_total_albums);
        artistBio = (WebView) rootView.findViewById(R.id.artist_description);
        popularity = (TextView) rootView.findViewById(R.id.popularity);
        totalEps = (TextView) rootView.findViewById(R.id.total_eps);
        totalTracks = (TextView) rootView.findViewById(R.id.total_tracks);

        artist = new Artist();
        networkRequest = new ArtistNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.artist(artistId));

        bios = new BioWrapper();
        bioNetworkRequest = new BioNetworkAdapter(getActivity());
        bioNetworkRequest.execute(UrlFactory.artistBio(artistId));

        innerFrame.addView(rootView);
        return innerFrame;
    }


    private void loadViewData() {
        artistName.setText(artist.getName());
        artistTotalAlbums.setText(artist.getTotalAlbums());
        totalTracks.setText(artist.getTotalTracks());
        totalEps.setText(artist.getTotalEps());

        int artistPopularity = artist.getPopularity();
        if (artistPopularity != 1) {
            popularity.setText(artistPopularity + getString(R.string.followers));
        } else {
            popularity.setText(artistPopularity + getString(R.string.follower));
        }

        Picasso.with(getActivity()).load(UrlFactory.imageUrl(artist.getId(), UrlFactory.EntityType.ARTIST, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.LARGE)).placeholder(R.drawable.placeholder).fit().centerCrop().into(artistHeroImage);
    }

    private void loadBioData() {
        artistBio.loadData("About the artist: " + bios.getData().get(0).getContent(), "text/html",
                "utf-8");
    }

    private class ArtistNetworkAdapter extends NetworkAdapter {
        public ArtistNetworkAdapter(Context context) {
            super(context, new ArtistMapper(), RequestType.GET, new HashMap<String, String>(), artist);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

    private class BioNetworkAdapter extends NetworkAdapter {
        public BioNetworkAdapter(Context context) {
            super(context, new BioMapper(), RequestType.GET, new HashMap<String, String>(), bios);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadBioData();
        }

    }
}
