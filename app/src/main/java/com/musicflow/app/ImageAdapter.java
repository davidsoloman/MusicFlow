package com.musicflow.app;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.musicflow.app.data.Album;
import com.squareup.picasso.Picasso;

public class ImageAdapter extends ArrayAdapter<Album> {

    private Context context;
    protected List<Album> albums;
    protected int resource;

    public ImageAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
        this.context = context;
        this.albums = albums;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumViewActivity.class);
                intent.putExtra("ArtistId", getItem(position).getRefs().getArtists().get(0).getId());
                context.startActivity(intent);
            }
        });
        // TODO: put into a function
        String url =
                "https://partner.api.beatsmusic.com/v1/api/albums/" + getItem(position).getId()
                        + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(imageView);
        return imageView;
    }

}
