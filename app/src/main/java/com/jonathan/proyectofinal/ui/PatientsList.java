package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;

public class PatientsList extends AppCompatActivity {

    private IPatientsListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);

        //region Fragment PatientsList
        PatientsListFragment fragment = (PatientsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (IPatientsListFragmentListener) fragment;

        //endregion
    }
    public void fragmentClick(Patient patient){
        Toast.makeText(this,"seleccionó artículo",Toast.LENGTH_LONG).show();
    }
}
