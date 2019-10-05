package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;

public class Login extends AppCompatActivity {

    private EditText name,pass;
    private Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    reference();
    validations();
    }

    private void validations() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String passTxt = pass.getText().toString();
                if(nameTxt.isEmpty() || passTxt.isEmpty()){
                    if(nameTxt.isEmpty()){
                        name.requestFocus();
                        name.setError(getString(R.string.required));

                    }else {
                        name.setError(null);
                    }
                    if(passTxt.isEmpty()){
                        pass.requestFocus();
                        pass.setError(getString(R.string.required));
                    }else {
                        pass.setError(null);
                    }
                }else{
                   if (nameTxt.equals("123") && passTxt.equals("123")){

                       startActivity(new Intent(Login.this, AdminHome.class));

                   }else {
                       Toast.makeText(Login.this, getString(R.string.datos_incorrectos), Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }

    private void reference() {
        name = findViewById(R.id.login_admin_edt_name);
        pass = findViewById(R.id.login_admin_edt_pass);
        enter = findViewById(R.id.login_admin_btn_enter);
    }
}
