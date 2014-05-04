package com.musicflow.app.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.R;
import com.musicflow.app.data.Track;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

/**
 * Takes a list of tracks and produces one layout for each single track.
 */
public class TracksAdapter extends ArrayAdapter<Track> {
    protected List<Track> tracks;
    protected int resource;
    private Context context;

    public TracksAdapter(Context context, int resource, List<Track> tracks) {
        super(context, resource, tracks);
        this.context = context;
        this.tracks = tracks;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Track current = getItem(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.track_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement reason to click a track
            }
        });

        ImageView trackCoverArt = (ImageView) rowView.findViewById(R.id.track_cover_art);
        Picasso.with(context)
                .load(UrlFactory.imageUrl(current.getId(), UrlFactory.EntityType.TRACK,
                        UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.SMALL))
                .placeholder(R.drawable.placeholder).fit().centerCrop().into(trackCoverArt);
        TextView trackName = (TextView) rowView.findViewById(R.id.track_title);
        trackName.setText(current.getTitle());

        return rowView;
    }
}
