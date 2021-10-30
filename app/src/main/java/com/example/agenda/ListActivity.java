package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.agenda.adapters.TaskAdapter;
import com.example.agenda.objetos.Task;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    private ListView listView ;
   // private DataManager db = new DataManager(listActivity.this);
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listV_List_List);
// Construct the data source
        ArrayList<Task> arrayOfUsers = new ArrayList();

       // arrayOfUsers.addAll(db.selectAllData());
// Create the adapter to convert the array to views
        TaskAdapter adapter = new TaskAdapter(this, arrayOfUsers);
// Attach the adapter to a ListView

        listView.setAdapter(adapter);

    }
}