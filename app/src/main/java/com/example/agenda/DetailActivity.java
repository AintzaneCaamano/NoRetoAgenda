package com.example.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDelete;
    private Button btnModify;
    private Button btnExit;
    private EditText edtTxtName;
    public static final int fifthActivity = 5;
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

    }

    @Override
    public void onClick(View v) {
        if(v==btnDelete){

        }else if(v == btnModify){

        }else if (v ==  btnExit){
            //Intent intent2 = new Intent();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Recoge los datos del activity2
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == fifthActivity){

                String taskID= data.getStringExtra("task");
            edtTxtName.setText(taskID);


        }
    }
}