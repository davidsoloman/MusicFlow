package com.musicflow.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.R;
import com.freethinking.beats.sdk.data.Playlist;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Takes a list of artists and produces one layout for each single artist.
 */
public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    protected List<Playlist> playlists;
    protected int resource;
    private Context context;

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
        Picasso.with(context).load(UrlFactory.imageUrl(current.getId(), UrlFactory.EntityType.PLAYLIST, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.SMALL)).placeholder(R.drawable.placeholder).fit().centerCrop().into(playlistImage);

        TextView playlistTitle = (TextView) rowView.findViewById(R.id.playlist_title);
        playlistTitle.setText(current.getName());

        TextView curatorName = (TextView) rowView.findViewById(R.id.curator);
        curatorName.setText(current.getRefs().getAuthor().getDisplay());

        return rowView;
    }
}
