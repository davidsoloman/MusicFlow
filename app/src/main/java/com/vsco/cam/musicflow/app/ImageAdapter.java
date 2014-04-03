package com.vsco.cam.musicflow.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {R.drawable.avicii_1, R.drawable.avicii_2,
            R.drawable.calvin_harris, R.drawable.ellie_goulding1, R.drawable.ellie_goulding2,
            R.drawable.karmin, R.drawable.skrillex_1, R.drawable.deadmou5,
            R.drawable.swedish_house_mafia, R.drawable.tim_berg, R.drawable.tommy_trash,
            R.drawable.zedd, R.drawable.kascade, R.drawable.krewella, R.drawable.steve_aoki,
            R.drawable.tiesto, R.drawable.skrillex_2, R.drawable.britney_spears,
            R.drawable.britney_spears2};
}
