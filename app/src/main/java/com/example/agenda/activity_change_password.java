package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.objetos.DataManager;
import com.example.agenda.objetos.User;

public class activity_change_password extends AppCompatActivity implements View.OnClickListener{
    EditText oldPass;
    EditText newPass;
    EditText newPass2;
    Button buttonChange;
    Button buttonCancel;
    User user;
    private DataManager db = new DataManager(activity_change_password.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPass = findViewById(R.id.edtTxt_BaseDialog_OldPass);
        newPass = findViewById(R.id.edtTxt_BaseDialog_NewPass);
        newPass2 = findViewById(R.id.edtTxt_BaseDialog_NewPass2);
        buttonChange = findViewById(R.id.btn_changePass);
        buttonCancel  = findViewById(R.id.btn_changePass_Cancel);
        buttonChange.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = db.selectUserById(extras.getInt("user"));
        }
    }


    @Override
    public void onClick(View v) {
        if (v==buttonChange){
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
        }else {
            setResult(RESULT_OK);
            finish();
        }
    }
    private void showToast(String msg){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }
}