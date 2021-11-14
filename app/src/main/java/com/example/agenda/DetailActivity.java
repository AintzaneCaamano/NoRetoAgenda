package com.example.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.Task;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDelete;
    private Button btnModify;
    private Button btnExit;
    private EditText edtTxtName;
    private EditText edtTxtDescription;
    private EditText edtTxtDate;
    private EditText edtTxtCost;
    private Spinner spinPriority;
    private Spinner spinDone;
    private String txtModified;
    private String txtErased;
    public static final int fifthActivity = 5;
    private DataManager db = new DataManager(this);
    private Task task;
    private int idData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnDelete = findViewById(R.id.btn_Detail_Delete);
        btnDelete.setOnClickListener(this);
        btnModify = findViewById(R.id.btn_Detail_Modify);
        btnModify.setOnClickListener(this);
        btnExit = findViewById(R.id.btn_Detail_Exit);
        btnExit.setOnClickListener(this);
        edtTxtName = findViewById(R.id.edtTxt_Detail_TName);
        edtTxtDescription=findViewById(R.id.edtTxt_Detail_Description);
        edtTxtDate=findViewById(R.id.edtTxt_Detail_Date);
        edtTxtCost=findViewById(R.id.edtTxt_Detail_Cost);
        spinPriority = findViewById(R.id.spinner_Detail_Priority);
        spinDone = findViewById(R.id.spinner_Detail_Done);
        txtModified=getString(R.string.toast_Save);
        txtErased=getString(R.string.toast_Erasing);
        fillSpinners();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idData = 0;
            finish();
            //Toast
        } else {
            task = db.selectTaskById(extras.getInt("task"));
            fillData();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btnDelete){
            showToast(txtErased);
            db.deleteTask(task.getCode());
            setResult(RESULT_OK);
            finish();
        }else if(v == btnModify){
            getData();
            db.updateTask(task);
           showToast(txtModified);
        }else if (v ==  btnExit){
            setResult(RESULT_OK);
            finish();
        }
    }

    private void fillData() {
        edtTxtName.setText(task.getName());
        edtTxtDescription.setText(task.getDescription());
        edtTxtDate.setText(task.getDate());
        edtTxtCost.setText(task.getCost());
        spinPriority.setSelection(task.getPriority());
        int done = 0;
        if(task.isDone()){
            done=1;
        }
        spinDone.setSelection(done);
    }

    private void fillSpinners() {
        //Prioridades
        ArrayList<String> prioritiesName = new ArrayList<>();

        prioritiesName.add(getString(R.string.txt_priority0));
        prioritiesName.add(getString(R.string.txt_Priority1));
        prioritiesName.add(getString(R.string.txt_Priority2));
        prioritiesName.add(getString(R.string.txt_Priority3));

        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prioritiesName);
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPriority.setAdapter(aAdapter);

        //Done
        ArrayList<String> state = new ArrayList<>();
        state.add(getString(R.string.txt_Undone));
        state.add(getString(R.string.txt_Done));

        ArrayAdapter<String> aAdapterDone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        aAdapterDone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDone.setAdapter(aAdapterDone);
    }

    private void getData() {
        task.setName(edtTxtName.getText().toString());
        task.setDescription(edtTxtDescription.getText().toString());
        task.setDate(edtTxtDate.getText().toString());
        task.setCost(edtTxtCost.getText().toString());
        task.setPriority(spinPriority.getSelectedItemPosition());
        task.setDone(spinDone.getSelectedItemPosition() == 0 ? false : true);
    }

        private void showToast(String msg){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, msg, duration);
            toast.show();
        }

}