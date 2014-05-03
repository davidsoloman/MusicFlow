package com.musicflow.app.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.R;
import com.musicflow.app.data.Highlight;

/**
 * Displays a list of highlights, which is of type Recommendation content, which can be an album,
 * track, artist, or playlist.
 */
public class HighlightsAdapter extends ArrayAdapter<Highlight> {
    protected List<Highlight> highlights;
    protected int resource;
    private Context context;

    public HighlightsAdapter(Context context, int resource, List<Highlight> highlights) {
        super(context, resource, highlights);
        this.context = context;
        this.highlights = highlights;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Highlight current = getItem(position);
        View rowView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if ("album".equals(current.getType())) {
            rowView = inflater.inflate(R.layout.highlight_list_item, parent, false);
            TextView playlistAuthor = (TextView) rowView.findViewById(R.id.playlist_author);
            ImageView highlightImageView = (ImageView) rowView.findViewById(R.id.highlight_image_view);
            return rowView;
        } else {
            rowView = inflater.inflate(R.layout.highlight_list_item, parent, false);
            TextView playlistAuthor = (TextView) rowView.findViewById(R.id.playlist_author);
            ImageView highlightImageView = (ImageView) rowView.findViewById(R.id.highlight_image_view);
            return rowView;
        }

    }
}
