package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.jonathan.proyectofinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Registration_Carer extends AppCompatActivity {

    @BindView(R.id.toolbar_registration_carer)
    MaterialToolbar toolbar;

    @BindView(R.id.admin_createps_til_name)
    TextInputEditText firstNameHp;
    @BindView(R.id.admin_createps_til_lastname)
    TextInputEditText lastNameHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__carer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
