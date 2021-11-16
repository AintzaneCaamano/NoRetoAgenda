package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox remember;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton = findViewById(R.id.btn_Main_LogIn);
        buton.setOnClickListener(this);

        editText_Name = (EditText) findViewById(R.id.edTxt_Main_Name);
        editText_Pass = (EditText) findViewById(R.id.edTxt_Main_Pass);
        remember = findViewById(R.id.checkBx_Main_Remember);

        //Reiniciar la base de datos
        db.onUpgrade(db.getWritableDatabase(),1,2);

        isRemembered();

    }
    @Override
    protected void onResume() {
        super.onResume();
        isRemembered();
    }


        @Override
        public void onClick(View v) {
            if(v==buton){
                if (validateUser()){
                 openIntent();
                }
            }/*else if (v == butonEn){
                changeLang(v);
            }else if (v == butonEs){
                changeLang(v);
            }*/
        }

    private void isRemembered(){
        //comprueba si el usuario dejó la sesión abierta y devuelve un boolean
        ArrayList <User> users = new ArrayList<User>();
        remember.setChecked(false);
        editText_Name.setText(getText(R.string.txt_userName));
        editText_Pass.setText(getText(R.string.txt_pass));
        users.addAll(db.selectAllUserData());
        for (int i = 0 ; i< users.size(); i++) {
            if (users.get(i).isRemember()){
                editText_Name.setText(users.get(i).getName());
                editText_Pass.setText(users.get(i).getPass());
                remember.setChecked(true);
                i=users.size();
            }
        }
    }

        private boolean validateUser(){
        //Comprueba todos los users y pass para ver si es correcto y devuelve un boolean y un toast
            String toast ="";
            boolean ret=false;
            String userName = editText_Name.getText().toString();
            String userPass = editText_Pass.getText().toString();
            ArrayList <User> users = new ArrayList<User>();
            users.addAll(db.selectAllUserData());
            for (int i = 0 ; i< users.size(); i++) {
                if (userName.equals(users.get(i).getName())) {
                    toast=getString(R.string.toast_IncorrectPass);
                        if (userPass.equals(users.get(i).getPass())) {
                            ret = true;
                            user = users.get(i);
                            saveStatus(users.get(i));
                            i = users.size();
                        }
                    }else {
                    toast=getString(R.string.toast_IncorrectUser);
                    }
                }
            if (!ret){
                showToast(toast);
            }
        return ret;
        }

        private void openIntent(){
        //Crea un nuevo intent
            Intent intento = new Intent(MainActivity.this, BaseActivity.class);
            intento.putExtra("user", user.getCode() );
            startActivityForResult(intento, secondActivity);
        }

        private void saveStatus(User user){
            if (remember.isChecked()){
                user.setRemember(true);
            }else {
                user.setRemember(false);
            }
            db.updateUser(user);
        }

    private void showToast(String msg){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }

    /*
        private void changeLang(View v){
            //Cambiar idioma por defecto
            Locale locale;
            Configuration config = new Configuration();

            //Locale.getDefault().getLanguage().equalsIgnoreCase("en")
            if (v == butonEs) {
                locale = new Locale("es");
                Locale.setDefault(locale);
                config.locale = locale;
            }else {
                locale = new Locale("en");
                Locale.setDefault(locale);
                config.locale = locale;
            }
          //  getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
            this.setContentView(R.layout.activity_main);
        }*/
    }
