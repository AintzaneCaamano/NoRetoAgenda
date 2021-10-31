package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnRegister;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_Register_Register);
        btnRegister.setOnClickListener(this);
        btnCancel = findViewById(R.id.btn_Register_Cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btnRegister){}
        else if(v== btnCancel){

            //Intent intent2 = new Intent();
            setResult(RESULT_OK);
            finish();
        }
    }
}