package com.musicflow.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Takes a list of artists and produces one layout for each single artist.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> {
    private Context context;
    protected List<Artist> artists;
    protected int resource;

    public ArtistAdapter(Context context, int resource, List<Artist> artists) {
        super(context, resource, artists);
        this.context = context;
        this.artists = artists;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Artist current = getItem(position);

        View rowView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.artist_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement
            }
        });

        ImageView artistProfileImage = (ImageView) rowView.findViewById(R.id.artist_profile_image);
        String url = "https://partner.api.beatsmusic.com/v1/api/artists/" + current.getId() + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(artistProfileImage);

        TextView artistName = (TextView) rowView.findViewById(R.id.artist_name);
        artistName.setText(current.getName());

        return rowView;
    }
}
