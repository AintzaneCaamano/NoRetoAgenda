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
            createListDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void createListDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        LayoutInflater inflater = BaseActivity.this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                .setPositiveButton(R.string.btn_Yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
/*
                        EditText oldPass = findViewById(R.id.edtTxt_BaseDialog_OldPass);
                        EditText newPass = findViewById(R.id.edtTxt_BaseDialog_NewPass);
                        EditText newPass2 = findViewById(R.id.edtTxt_BaseDialog_NewPass2);

                        String oldCode = oldPass.getText().toString();
                        String newCode= newPass.getText().toString();
                        String newCode2 = newPass2.getText().toString();
                        if (user.getPass().equals(oldCode)){
                            if(newCode.equals(newCode2)){
                            user.setPass(newCode);
                            db.updateUser(user);
                                showToast(getString(R.string.Toast_BaseDialog_Update));
                            }else {
                                showToast(getString(R.string.Toast_BaseDialog_DifferentPass));
                            }
                        }else {
                            showToast(getString(R.string.toast_IncorrectPass));
                        }
                    */}
                })
                .setNegativeButton(R.string.btn_Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(String msg){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }
}