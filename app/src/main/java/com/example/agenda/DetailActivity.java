package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDelete;
    private Button btnModify;
    private Button btnExit;
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
}