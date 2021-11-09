package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.Task;
import com.example.agenda.objetos.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int secondActivity = 2;
    private Button buton;
    private EditText editText_Name = null;
    private EditText editText_Pass = null;
    private DataManager db = new DataManager(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton = findViewById(R.id.btn_Main_LogIn);
        buton.setOnClickListener(this);

        editText_Name = (EditText) findViewById(R.id.edTxt_Main_Name);
        editText_Pass = (EditText) findViewById(R.id.edTxt_Main_Pass);

        if(isRemembered()){
            openIntent();
        }

    }

        @Override
        public void onClick(View v) {
            if (validateUser()){
              openIntent();
            }
        }

    private boolean isRemembered(){
        //comprueba si el usuario dejó la sesión abierta y devuelve un boolean
        boolean ret=false;
        ArrayList <User> users = new ArrayList<User>();
        users.addAll(db.selectAllUserData());
        for (int i = 0 ; i< users.size(); i++) {
            if (users.get(i).isRemember()){
                ret = true;
                i=users.size();
            }
        }
        return ret;
    }

        private boolean validateUser(){
        //Comprueba todos los users y pass para ver si es correcto y devuelve un boolean
            boolean ret=false;
            String userName = editText_Name.getText().toString();
            String userPass = editText_Pass.getText().toString();
            ArrayList <User> users = new ArrayList<User>();
            users.addAll(db.selectAllUserData());
            for (int i = 0 ; i< users.size(); i++) {
                if (userName.equals(users.get(i).getName())&& userPass.equals(users.get(i).getPass())){
                    ret = true;
                    i=users.size();
                }
            }
        return ret;
        }

        private void openIntent(){
        //Crea un nuevo intent
            Intent intento = new Intent(MainActivity.this, BaseActivity.class);
            startActivityForResult(intento, secondActivity);
        }


    }
