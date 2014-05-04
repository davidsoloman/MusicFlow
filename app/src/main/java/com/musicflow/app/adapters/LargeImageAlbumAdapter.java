package com.musicflow.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.AlbumDetailActivity;
import com.musicflow.app.R;
import com.musicflow.app.data.Album;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Injects large images into list view.  On click, opens up the album detail activity and
 * corresponding fragments.
 */
public class LargeImageAlbumAdapter extends ArrayAdapter<Album> {

    protected List<Album> albums;
    protected int resource;
    private Context context;

    public LargeImageAlbumAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Album current = getItem(position);

        View rowView = inflater.inflate(R.layout.fragment_albums, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumDetailActivity.class);
                intent.putExtra("AlbumId", getItem(position).getId());
                intent.putExtra("ArtistName", getItem(position).getArtistDisplayName());
                intent.putExtra("AlbumTitle", getItem(position).getTitle());

                context.startActivity(intent);
            }
        });

        ImageView largeImage = (ImageView) rowView.findViewById(R.id.album_cover_art);
        Picasso.with(context).load(UrlFactory.imageUrl(getItem(position).getId(), UrlFactory.EntityType.ALBUM, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.MEDIUM)).placeholder(R.drawable.placeholder).fit().centerCrop().into(largeImage);

        TextView albumName = (TextView) rowView.findViewById(R.id.album_name);
        albumName.setText(current.getTitle());

        TextView artistName = (TextView) rowView.findViewById(R.id.artist_name);
        artistName.setText(current.getArtistDisplayName());

        return rowView;
    }
}
