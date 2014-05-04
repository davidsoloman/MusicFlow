package com.musicflow.app.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.musicflow.app.R;
import com.musicflow.app.data.Genre;

/**
 *  Displays a list of genres to a list view.
 */
public class GenreAdapter extends ArrayAdapter<Genre> {
    private Context context;
    protected List<Genre> genres;
    protected int resource;

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
                // Intent intent = new Intent(context, AboutArtistActivity.class);
                // intent.putExtra("ArtistId", getItem(position).getId());
                // context.startActivity(intent);
            }
        });

        TextView genreName = (TextView) rowView.findViewById(R.id.genre_name);
        genreName.setText(current.getName());

        return rowView;
    }
}
