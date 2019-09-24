package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.PatientsListFragmentListener;

public class PatientsList extends AppCompatActivity {

    private PatientsListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);


        PatientsListFragment fragment = (PatientsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (PatientsListFragmentListener) fragment;
    }
}
