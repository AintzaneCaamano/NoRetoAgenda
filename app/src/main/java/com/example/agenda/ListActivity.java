package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.agenda.adapters.TaskAdapter;
import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.Task;
import com.example.agenda.objetos.User;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView ;
    private TaskAdapter adapter;
    private DataManager db = new DataManager(this);
    private Button btnCancel;
    private Button btnDone;
    private Button btnPending;
    private Button btnAll;
    private ArrayList<Task> arrayOfTasks;
    private ArrayList<Task> arrayOfDoneTasks;
    private ArrayList<Task> arrayOfPendingTasks;
    private Task task;
    private User user;
    public static final int fifthActivity = 5;
    public String erasing;
    public boolean longC;
    public boolean erase;


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
        btnAll = findViewById(R.id.btn_List_AllTasks);
        btnAll.setOnClickListener(this);
        erasing = getString(R.string.toast_Erasing);
        longC=false;

        listView = findViewById(R.id.listV_List_List);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = db.selectUserById(extras.getInt("user"));
        }

        showPendingTasks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!longC){
                Intent intento = new Intent(getApplicationContext(), DetailActivity.class);
                task =(Task) listView.getItemAtPosition(position);
                intento.putExtra("task", task.getCode() );
                startActivityForResult(intento, fifthActivity);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longC=true;
                task =(Task) listView.getItemAtPosition(position);
                createListDialog();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPendingTasks();

    }

    @Override
    public void onClick(View v) {
        if (v==btnCancel){
            setResult(RESULT_OK);
            finish();
        }else if (v == btnDone){
            showDoneTasks();
        }else if (v==btnPending){
            showPendingTasks();
        }else if (v==btnAll){
            showAll();
        }
    }

    private void showPendingTasks(){
        arrayOfPendingTasks = db.selectTaskByStatus(false, user.getCode());
        adapter = new TaskAdapter(this, arrayOfPendingTasks);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnDone.setEnabled(true);
        btnPending.setEnabled(false);
        btnAll.setEnabled(true);
    }

    private void showDoneTasks(){
        arrayOfDoneTasks = db.selectTaskByStatus(true, user.getCode());
        adapter = new TaskAdapter(this, arrayOfDoneTasks);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnDone.setEnabled(false);
        btnPending.setEnabled(true);
        btnAll.setEnabled(true);
    }
    private void showAll(){
        arrayOfTasks=db.selectAllTaskData(user.getCode());
        adapter = new TaskAdapter(this, arrayOfTasks);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnDone.setEnabled(true);
        btnPending.setEnabled(true);
        btnAll.setEnabled(false);
    }

    private void printToast(){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, erasing, duration);
        toast.show();
    }

    public void createListDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setMessage(R.string.dlog_List_Msg).setTitle(R.string.dlog_List_Title);
        builder.setPositiveButton(R.string.btn_Yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                erase();
                longC=false;
            }
        });
        builder.setNegativeButton(R.string.btn_Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                longC=false;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void erase(){
        db.deleteTask(task.getCode());
        printToast();
        showAll();
        longC=false;
    }

}