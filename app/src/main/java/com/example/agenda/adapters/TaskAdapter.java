package com.example.agenda.adapters;

import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import com.example.agenda.BaseActivity;
import com.example.agenda.DetailActivity;
import com.example.agenda.ListActivity;
import com.example.agenda.MainActivity;
import com.example.agenda.R;
import com.example.agenda.RegisterActivity;
import com.example.agenda.objetos.Task;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task>{
    private  TextView txtViewName;
    private Context context;
    private Task task;

    public TaskAdapter(Context context, ArrayList<Task> tasks2) {
        super(context, 0, tasks2);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // super(itemView);
        // Get the data item for this position
         task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }
        // Lookup view for data population
        txtViewName= (TextView) convertView.findViewById(R.id.txtV_listviewlyo_name);

        // Populate the data into the template view using the data object
        txtViewName.setText(task.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
