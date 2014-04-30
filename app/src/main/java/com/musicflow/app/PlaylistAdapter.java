package com.musicflow.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Artist;
import com.musicflow.app.data.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Takes a list of artists and produces one layout for each single artist.
 */
public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    private Context context;
    protected List<Playlist> playlists;
    protected int resource;

    public PlaylistAdapter(Context context, int resource, List<Playlist> playlists) {
        super(context, resource, playlists);
        this.context = context;
        this.playlists = playlists;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Playlist current = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.playlist_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement
            }
        });

        ImageView playlistImage = (ImageView) rowView.findViewById(R.id.playlist_cover_art);
        String url = "https://partner.api.beatsmusic.com/v1/api/playlists/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).fit().centerCrop().into(playlistImage);

        TextView playlistTitle = (TextView) rowView.findViewById(R.id.playlist_title);
        playlistTitle.setText(current.getName());

        TextView curatorName = (TextView) rowView.findViewById(R.id.curator);
        curatorName.setText(current.getRefs().getAuthor().getDisplay());

        return rowView;
    }
}
