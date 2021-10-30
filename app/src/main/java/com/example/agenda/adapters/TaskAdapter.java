package com.example.agenda.adapters;

import android.widget.ArrayAdapter;

import com.example.agenda.R;
import com.example.agenda.objetos.Task;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> implements View.OnClickListener {
    public TaskAdapter(Context context, ArrayList<Task> tasks2) {
        super(context, 0, tasks2);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }
        // Lookup view for data population
        TextView txtView= (TextView) convertView.findViewById(R.id.txtV_listviewlyo_name);
        txtView.setOnClickListener((View.OnClickListener) this);
        // Populate the data into the template view using the data object
        txtView.setText(task.getName());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
