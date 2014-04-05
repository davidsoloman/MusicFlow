package com.musicflow.app;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends ArrayAdapter<String> {

    private Context context;
    protected List<String> coverArt;
    protected int resource;

    public ImageAdapter(Context context, int resource, List<String> coverArt) {
        super(context, resource, coverArt);
        this.context = context;
        this.coverArt = coverArt;
        this.resource = resource;
    }

//    public ImageAdapter(Context c) {
//        context = c;
//    }
//
//    public int getCount() {
//        return mThumbIds.length;
//    }
//
//    public Object getItem(int position) {
//        return mThumbIds[position];
//    }
//
//    public long getItemId(int position) {
//        return 0;
//    }

    // create a new ImageView for each item referenced by the Adapter
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

        String url = getItem(position);
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(imageView);
        return imageView;
    }

//    // references to our images
//    private Integer[] mThumbIds = {R.drawable.avicii_1, R.drawable.avicii_2,
//            R.drawable.calvin_harris, R.drawable.ellie_goulding1, R.drawable.ellie_goulding2,
//            R.drawable.karmin, R.drawable.skrillex_1, R.drawable.deadmou5,
//            R.drawable.swedish_house_mafia, R.drawable.tim_berg, R.drawable.tommy_trash,
//            R.drawable.zedd, R.drawable.kascade, R.drawable.krewella, R.drawable.steve_aoki,
//            R.drawable.tiesto, R.drawable.skrillex_2, R.drawable.britney_spears,
//            R.drawable.britney_spears2};
}
