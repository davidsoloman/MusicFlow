package com.musicflow.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.AboutArtistActivity;
import com.musicflow.app.R;
import com.freethinking.beats.sdk.data.Artist;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Takes a list of artists and produces one layout for each single artist.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> {
    protected List<Artist> artists;
    protected int resource;
    private Context context;

    public ArtistAdapter(Context context, int resource, List<Artist> artists) {
        super(context, resource, artists);
        this.context = context;
        this.artists = artists;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Artist current = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.artist_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AboutArtistActivity.class);
                intent.putExtra("ArtistId", getItem(position).getId());
                context.startActivity(intent);
            }
        });

        ImageView artistProfileImage = (ImageView) rowView.findViewById(R.id.artist_profile_image);
        Picasso.with(context).load(UrlFactory.imageUrl(context, current.getId(), UrlFactory.EntityType.ARTIST, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.SMALL)).placeholder(R.drawable.placeholder).fit().centerCrop().into(artistProfileImage);
        TextView artistName = (TextView) rowView.findViewById(R.id.artist_name);
        artistName.setText(current.getName());

        return rowView;
    }
}
