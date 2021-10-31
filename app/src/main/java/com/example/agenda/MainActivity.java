package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int secondActivity = 2;
    private Button buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton = findViewById(R.id.btn_Main_LogIn);
        buton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (true){
        Intent intento = new Intent(MainActivity.this, BaseActivity.class);
        startActivityForResult(intento, secondActivity);
        }
    }
}