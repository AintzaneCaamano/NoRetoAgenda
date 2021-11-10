package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.Task;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnRegister;
    private Button btnCancel;
    private EditText txtName;
    private EditText txtDescription;
    private EditText txtDate;
    private EditText txtCost;
    private Spinner  spinPriority;
    private Spinner spinDone;
    private Task task;
    private DataManager db = new DataManager(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_Register_Register);
        btnRegister.setOnClickListener(this);
        btnCancel = findViewById(R.id.btn_Register_Cancel);
        btnCancel.setOnClickListener(this);
        txtName = findViewById(R.id.edtTxt_Register_TName);
        txtDescription = findViewById(R.id.edtTxt_Register_Description);
        txtDate = findViewById(R.id.edtTxt_Register_Date);
        txtCost = findViewById(R.id.edtTxt_Register_Cost);
        spinPriority = findViewById(R.id.spinner_Register_Priority);
        spinDone = findViewById(R.id.spinner_Register_Done);
    }

    @Override
    public void onClick(View v) {
        if (v==btnRegister){
            if (fillTask()){
                db.insertTask(task);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, "Tarea Guardada", duration);
                toast.show();
            }else {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, "Faltan datos", duration);
                toast.show();
            }
        }
        else if(v== btnCancel){

            //Intent intent2 = new Intent();
            setResult(RESULT_OK);
            finish();
        }
    }

    private boolean fillTask(){
        task = new Task();
        //Rellena el objeto Task con los datos, si falta algun dato importante devuelve false
        boolean ret=true;
        //Recoge los datos y los guarda en un objeto
        String name = txtName.getText().toString();
        if (name.length()<2){
            ret=false;
        }
        String description = txtDescription.getText().toString();
        String date = txtDate.getText().toString();
        String cost = txtCost.getText().toString();
        String priority;
        String Done;
        task.setName(name);
        task.setDescription(description);
        task.setDate(date);
        task.setCost(cost);
        return ret;
    }
}