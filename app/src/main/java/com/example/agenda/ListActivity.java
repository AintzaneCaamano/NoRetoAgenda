package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.agenda.adapters.TaskAdapter;
import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.Task;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView ;
    private TaskAdapter adapter;
    private DataManager db = new DataManager(this);
    private Button btnCancel;
    private Button btnDone;
    private Button btnPending;
    private ArrayList<Task> arrayOfTasks;
    private ArrayList<Task> arrayOfDoneTasks;
    private ArrayList<Task> arrayOfUnDoneTasks;

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
        arrayOfTasks = new ArrayList();
        Task task = new Task(1,"Acabar el repaso", "el repaso esta en moodle", "13-11-2021", "2h", "muy importante", false);
        arrayOfTasks.add(task);
        Task task2 = new Task(2,"estudiar examen de FOL", "el repaso esta en moodle", "13-11-2021", "2h", "importante", true);
        arrayOfTasks.add(task2);
        //arrayOfTasks.addAll(db.selectAllTasks());
        // Create the adapter to convert the array to views
        adapter = new TaskAdapter(this, arrayOfTasks);
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
            arrayOfDoneTasks = new ArrayList();
            for (int i = 0; i<arrayOfTasks.size(); i++){
                if (arrayOfTasks.get(i).isDone()){
                    arrayOfDoneTasks.add(arrayOfTasks.get(i));
                }
            }
            adapter = new TaskAdapter(this, arrayOfDoneTasks);
            adapter.notifyDataSetChanged();

        }else if (v==btnPending){
            arrayOfUnDoneTasks = new ArrayList();
            for (int i = 0; i<arrayOfTasks.size(); i++){
                if (!arrayOfTasks.get(i).isDone()){
                    arrayOfUnDoneTasks.add(arrayOfTasks.get(i));
                }
            }
            adapter = new TaskAdapter(this, arrayOfUnDoneTasks);
            adapter.notifyDataSetChanged();
        }
    }
}