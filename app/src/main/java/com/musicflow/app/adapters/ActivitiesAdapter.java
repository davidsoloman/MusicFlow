package com.musicflow.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.musicflow.app.ActivityViewActivity;
import com.musicflow.app.R;
import com.musicflow.app.data.Activity;

import java.util.List;

public class ActivitiesAdapter extends ArrayAdapter<Activity> {
    protected List<Activity> activities;
    protected int resource;
    private Context context;

    public ActivitiesAdapter(Context context, int resource, List<Activity> activities) {
        super(context, resource, activities);
        this.context = context;
        this.activities = activities;
        this.resource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Activity current = getItem(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_item, parent, false);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityViewActivity.class);
                intent.putExtra("ActivityId", getItem(position).getId());
                context.startActivity(intent);
            }
        });

        TextView activityName = (TextView) rowView.findViewById(R.id.activity_name);
        activityName.setText(current.getName());

        return rowView;
    }
}
