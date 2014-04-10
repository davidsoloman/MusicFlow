package com.musicflow.app;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Artist;
import com.musicflow.app.data.Bio;
import com.musicflow.app.mappers.ArtistMapper;
import com.musicflow.app.mappers.BioMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.squareup.picasso.Picasso;

public class AlbumViewActivity extends Activity {
    protected ImageView artistHeroImage;
    protected TextView artistName;
    protected TextView artistFollowerCount;
    protected TextView artistTotalAlbums;
    protected Artist artist;
    protected Bio bio;
    protected TextView artistBio;
    protected ArtistNetworkAdapter networkRequest;
    protected BioNetworkAdapter bioNetworkRequest;
    protected TextView totalEps;
    protected TextView totalTracks;
    protected TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        String artistId = getIntent().getStringExtra("ArtistId");
        artistHeroImage = (ImageView) findViewById(R.id.artist_hero_image);
        artistName = (TextView) findViewById(R.id.artist_name);
        artistTotalAlbums = (TextView) findViewById(R.id.artist_total_albums);
        artistBio = (TextView) findViewById(R.id.artist_description);
        popularity = (TextView) findViewById(R.id.popularity);
        totalEps = (TextView) findViewById(R.id.total_eps);
        totalTracks = (TextView) findViewById(R.id.total_tracks);

        artist = new Artist();
        bio = new Bio();
        networkRequest = new ArtistNetworkAdapter();
        networkRequest.execute("https://partner.api.beatsmusic.com/v1/api/artists/" + artistId
                + "?client_id=frksnm8edw2t8ddebhkqkjwk");
        bioNetworkRequest = new BioNetworkAdapter();
        bioNetworkRequest.execute("https://partner.api.beatsmusic.com/v1/api/artists/" + artistId
                + "/bios?client_id=frksnm8edw2t8ddebhkqkjwk");
    }

    private void loadViewData() {
        artistName.setText("Introducing: " + artist.getName());
        // artistFollowerCount.setText(artist.getTotalFollowedBy());
        artistTotalAlbums.setText("Total Albums: " + artist.getTotalAlbums());
        totalTracks.setText("Total Tracks: " + artist.getTotalTracks());
        totalEps.setText("Total EPs: " + artist.getTotalEps());
        popularity.setText("Popularity: " + artist.getPopularity() + " followers");
        artistBio.setText(bio.getContent());
        String url =
                "https://partner.api.beatsmusic.com/v1/api/artists/" + artist.getId()
                        + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=large";
        Picasso.with(this).load(url).placeholder(R.drawable.placeholder).into(artistHeroImage);
    }

    private class ArtistNetworkAdapter extends NetworkAdapter {
        public ArtistNetworkAdapter() {
            super(new ArtistMapper(), RequestType.GET, new HashMap<String, String>(), artist);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }
    }

    private class BioNetworkAdapter extends NetworkAdapter {
        public BioNetworkAdapter() {
            super(new BioMapper(), RequestType.GET, new HashMap<String, String>(), bio);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadViewData();
        }

    }
}
