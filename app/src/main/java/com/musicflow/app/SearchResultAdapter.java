package com.musicflow.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Album;
import com.musicflow.app.data.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {

    private Context context;
    protected List<SearchResult> searchResults;
    protected int resource;

    public SearchResultAdapter(Context context, int resource, List<SearchResult> searchResults) {
        super(context, resource, searchResults);
        this.context = context;
        this.searchResults = searchResults;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        SearchResult current = getItem(position);
        View rowView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if ("artist".equals(current.getResultType())) {
            rowView = inflater.inflate(R.layout.artist_list_item, parent, false);

            ImageView artistProfileImage = (ImageView) rowView.findViewById(R.id.artist_profile_image);
            String url = "https://partner.api.beatsmusic.com/v1/api/artists/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(artistProfileImage);

            TextView artistName = (TextView) rowView.findViewById(R.id.artist_name);
            artistName.setText(current.getDisplay());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Implement
                }
            });
        } else if ("album".equals(current.getResultType())) {
            rowView = inflater.inflate(R.layout.album_list_item, parent, false);

            ImageView albumCoverArt = (ImageView) rowView.findViewById(R.id.album_cover_art);
            String url = "https://partner.api.beatsmusic.com/v1/api/albums/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(albumCoverArt);

            TextView albumTitle = (TextView) rowView.findViewById(R.id.album_title);
            albumTitle.setText(current.getDisplay());
            TextView artistTitle = (TextView) rowView.findViewById(R.id.artist_title);
            artistTitle.setText(current.getDetail());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Implement
                }
            });

        } else if ("playlist".equals(current.getResultType())) {
            rowView = inflater.inflate(R.layout.playlist_list_item, parent, false);

            ImageView playlistCoverArt = (ImageView) rowView.findViewById(R.id.playlist_cover_art);
            String url = "https://partner.api.beatsmusic.com/v1/api/playlists/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(playlistCoverArt);

            TextView playlistTitle = (TextView) rowView.findViewById(R.id.playlist_title);
            playlistTitle.setText(current.getDisplay());
            TextView curator = (TextView) rowView.findViewById(R.id.curator);
            curator.setText(current.getDetail());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Implement
                }
            });

        } else if ("user".equals(current.getResultType())) {
            rowView = inflater.inflate(R.layout.user_list_item, parent, false);

            ImageView userProfileImage = (ImageView) rowView.findViewById(R.id.user_profile_image);
            String url = "https://partner.api.beatsmusic.com/v1/api/users/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(userProfileImage);

            TextView userFullName = (TextView) rowView.findViewById(R.id.user_full_name);
            userFullName.setText(current.getDisplay());
            TextView username = (TextView) rowView.findViewById(R.id.username);
            username.setText(current.getDetail());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Implement
                }
            });

        } else if ("track".equals(current.getResultType())) {
            rowView = inflater.inflate(R.layout.song_list_item, parent, false);

            ImageView songCoverArt = (ImageView) rowView.findViewById(R.id.song_cover_art);
            String url = "https://partner.api.beatsmusic.com/v1/api/tracks/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
            Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(songCoverArt);

            TextView songTitle = (TextView) rowView.findViewById(R.id.song_title);
            songTitle.setText(current.getDisplay());
            TextView albumTitle = (TextView) rowView.findViewById(R.id.album_title);
            albumTitle.setText(current.getDetail());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Implement
                }
            });

        } else {
            throw new IllegalArgumentException("Search result type is not: artist, album, playlist, user, or song. Was " + current.getResultType());
        }
        return rowView;
    }
}
