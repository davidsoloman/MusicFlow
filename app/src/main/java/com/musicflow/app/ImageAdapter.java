package com.musicflow.app;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private Context context;
    protected List<String> coverArt;
    protected int resource;

    public ImageAdapter(Context context, int resource, List<String> coverArt) {
        super(context, resource, coverArt);
        this.context = context;
        this.coverArt = coverArt;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setOnClickListener(this);
        String url = "https://partner.api.beatsmusic.com/v1/api/albums/" + getItem(position) + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium";
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(imageView);
        return imageView;
    }

    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context, AlbumViewActivity.class));
    }
}
