package com.musicflow.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.musicflow.app.adapters.PlaylistAdapter;
import com.musicflow.app.data.Genre;
import com.musicflow.app.data.Playlists;
import com.musicflow.app.mappers.ActivityMapper;
import com.musicflow.app.mappers.GenreMapper;
import com.musicflow.app.mappers.PlaylistsMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Displays a genre
 */
public class GenreViewActivity extends Activity {
    protected GenreNetworkRequest networkRequest;
    protected GenrePlaylistsNetworkRequest playlistNetworkRequest;

    protected Genre genre;
    protected String genreId;
    protected Playlists playlists;

    protected TextView genreName;
    protected TextView genreUserName;
    protected ImageView coverImage;
    protected ListView playlistListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        genre = new Genre();
        playlists = new Playlists();
        genreId = getIntent().getStringExtra("GenreId");

        setContentView(R.layout.activity_genre_view);

        playlistListView = (ListView) findViewById(R.id.genre_playlist_list_view);
        genreName = (TextView) findViewById(R.id.genre_name);
        genreUserName = (TextView) findViewById(R.id.genre_username);
        coverImage = (ImageView) findViewById(R.id.genre_cover_image);

        networkRequest = new GenreNetworkRequest(this);
        networkRequest.execute(UrlFactory.genre(genreId));

        playlistNetworkRequest = new GenrePlaylistsNetworkRequest(this);
        playlistNetworkRequest.execute(UrlFactory.genrePlaylists(genreId));

        setTitle("Genre");
    }

    private void loadGenreViewData() {
        genreName.setText(genre.getName());
        genreUserName.setText('@'+genre.getUsername());
        Picasso.with(this).load(UrlFactory.imageUrl(genre.getId(), UrlFactory.EntityType.GENRE, UrlFactory.ImageType.COVER, UrlFactory.ImageSize.LARGE)).placeholder(R.drawable.placeholder).fit().centerCrop().into(coverImage);
    }

    private void loadPlaylistsData() {
        playlistListView.setAdapter(new PlaylistAdapter(this, R.id.genre_playlist_list_view, playlists.getPlaylists()));
    }

    private class GenreNetworkRequest extends NetworkAdapter {
        @Override
        public Boolean authRequired() {
            return true;
        }

        public GenreNetworkRequest(Context context) {
            super(context, new GenreMapper(), RequestType.GET, new HashMap<String, String>(), genre);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadGenreViewData();
        }
    }

    private class GenrePlaylistsNetworkRequest extends NetworkAdapter {
        @Override
        public Boolean authRequired() {
            return true;
        }

        public GenrePlaylistsNetworkRequest(Context context) {
            super(context, new PlaylistsMapper(), RequestType.GET, new HashMap<String, String>(), playlists);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadPlaylistsData();
        }
    }
}
