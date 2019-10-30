package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.jonathan.proyectofinal.R;

import butterknife.BindView;

public class Registration_Carer extends AppCompatActivity {

    @BindView(R.id.admin_createps_til_name)
    TextInputEditText firstNameHp;
    @BindView(R.id.admin_createps_til_lastname)
    TextInputEditText lastNameHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__carer);

    }
}
