package com.musicflow.app.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.musicflow.app.AlbumDetailActivity;
import com.musicflow.app.AlbumDetailFragment;
import com.musicflow.app.ArtistViewActivity;
import com.musicflow.app.R;
import com.musicflow.app.data.Album;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

/**
 * Injects large images into list view.  On click, opens up the album detail activity and
 * corresponding fragments.
 */
public class LargeImageAlbumAdapter extends ArrayAdapter<Album> {

    private Context context;
    protected List<Album> albums;
    protected int resource;

    public LargeImageAlbumAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.full_image_view, parent, false);
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

        ImageView largeImage = (ImageView) rowView.findViewById(R.id.full_sized_image);
        Picasso.with(context).load(UrlFactory.imageUrl(getItem(position).getId(), UrlFactory.EntityType.ALBUM, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.LARGE)).placeholder(R.drawable.placeholder).fit().centerCrop().into(largeImage);
        return rowView;
    }
}
