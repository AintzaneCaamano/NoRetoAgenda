package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnList;
    private Button btnExit;
    private Button btnRegister;
    public static final int thirdActivity = 3;
    public static final int fourthActivity = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btnList = findViewById(R.id.btn_Base_List);
        btnList.setOnClickListener(this);
        btnExit = findViewById(R.id.btn_Base_Exit);
        btnExit.setOnClickListener(this);
        btnRegister = findViewById(R.id.btn_Base_Register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnList){
            Intent intento = new Intent(BaseActivity.this, ListActivity.class);
            startActivityForResult(intento, thirdActivity);
        }else if (v == btnRegister){
            Intent intento = new Intent(BaseActivity.this, RegisterActivity.class);
            startActivityForResult(intento, fourthActivity);
        }else if (v == btnExit){
            //Intent intent2 = new Intent();
            setResult(RESULT_OK);
            finish();
        }
    }
}