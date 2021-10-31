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

public class ListActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView ;
   // private DataManager db = new DataManager(listActivity.this);
    private Button btnCancel;
    private Button btnDone;
    private Button btnPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btnCancel = findViewById(R.id.btn_List_Cancel);
        btnCancel.setOnClickListener(this);
        btnDone = findViewById(R.id.btn_List_Done);
        btnDone.setOnClickListener(this);
        btnPending = findViewById(R.id.btn_List_Pending);
        btnPending.setOnClickListener(this);


        //Adapter
        listView = findViewById(R.id.listV_List_List);
// Construct the data source
        ArrayList<Task> arrayOfTasks = new ArrayList();
        Task task = new Task(1,"Acabar el repaso", "el repaso esta en moodle", "13-11-2021", "2h", "muy importante", false);
         arrayOfTasks.add(task);
       // arrayOfUsers.addAll(db.selectAllData());
// Create the adapter to convert the array to views
        TaskAdapter adapter = new TaskAdapter(this, arrayOfTasks);
// Attach the adapter to a ListView

        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v==btnCancel){
            //Intent intent2 = new Intent();
            setResult(RESULT_OK);
            finish();
        }else if (v == btnDone){

        }else if (v==btnPending){

        }
    }
}