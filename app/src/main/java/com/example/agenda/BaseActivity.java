package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.User;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnList;
    private Button btnExit;
    private Button btnRegister;
    private Toolbar toolbar;
    private User user;
    public static final int thirdActivity = 3;
    public static final int fourthActivity = 4;
    public static final int sixthActivity = 6;
    private DataManager db = new DataManager(BaseActivity.this);

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = db.selectUserById(extras.getInt("user"));
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intento = new Intent(BaseActivity.this, activity_change_password.class);
            intento.putExtra("user", user.getCode() );
            startActivityForResult(intento, sixthActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}