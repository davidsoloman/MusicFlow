package com.musicflow.app;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Artist;
import com.musicflow.app.mappers.ArtistMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.squareup.picasso.Picasso;

public class AlbumViewActivity extends Activity {
    protected ImageView artistHeroImage;
    protected TextView artistName;
    protected TextView artistFollowerCount;
    protected TextView artistTotalAlbums;
    protected Artist artist;
    protected ArtistNetworkAdapter networkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        String artistId = getIntent().getStringExtra("ArtistId");
        artistHeroImage = (ImageView)  findViewById(R.id.artist_hero_image);
        artistName = (TextView) findViewById(R.id.artist_name);
        artistFollowerCount = (TextView) findViewById(R.id.artist_follower_count);
        artistTotalAlbums = (TextView) findViewById(R.id.artist_total_albums);
        artist = new Artist();
        networkRequest = new ArtistNetworkAdapter();
        networkRequest.execute("https://partner.api.beatsmusic.com/v1/api/artists/" + artistId + "?client_id=frksnm8edw2t8ddebhkqkjwk");
    }

    private void loadViewData() {
        artistName.setText(artist.getName());
//        artistFollowerCount.setText(artist.getTotalFollowedBy());
        artistTotalAlbums.setText(artist.getTotalAlbums());
        String url = "https://partner.api.beatsmusic.com/v1/api/artists/" + artist.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=large";
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
}
