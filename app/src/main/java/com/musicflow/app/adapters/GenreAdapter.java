package com.musicflow.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.GenreViewActivity;
import com.musicflow.app.GenresActivity;
import com.musicflow.app.R;
import com.freethinking.beats.sdk.data.Genre;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Displays a list of genres to a list view.
 */
public class GenreAdapter extends ArrayAdapter<Genre> {
    protected List<Genre> genres;
    protected int resource;
    private Context context;

    public GenreAdapter(Context context, int resource, List<Genre> genres) {
        super(context, resource, genres);
        this.context = context;
        this.genres = genres;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Genre current = getItem(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.genre_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GenreViewActivity.class);
                intent.putExtra("GenreId", getItem(position).getId());
                context.startActivity(intent);
            }
        });

        TextView genreName = (TextView) rowView.findViewById(R.id.genre_name);
        genreName.setText(current.getName());
        TextView genreUserName = (TextView) rowView.findViewById(R.id.genre_username);
        genreUserName.setText('@'+current.getUsername());

        ImageView largeImage = (ImageView) rowView.findViewById(R.id.genre_default);
        Picasso.with(context).load(UrlFactory.imageUrl(getItem(position).getId(), UrlFactory.EntityType.GENRE, UrlFactory.ImageType.DEFAULT, UrlFactory.ImageSize.MEDIUM)).placeholder(R.drawable.placeholder).fit().centerCrop().into(largeImage);

        return rowView;
    }
}
