package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.fragments.admin.AdminListPSFragment;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;

import java.util.List;

public class PatientsList extends AppCompatActivity {

    private BottomAppBar babHProfessional;
    private FloatingActionButton fabHProfessional;
    private boolean isFabTapped = false;
    private IPatientsListFragmentListener fragmentListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);

        //region Fragment PatientsList
        /*
        PatientsListFragment fragment = (PatientsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (IPatientsListFragmentListener) fragment;
        */
        //endregion

        reference();

        if (savedInstanceState == null){
            handleFrame(new PatientsListFragment());
        }

        handleFab();
    }

    private void handleFab() {
        fabHProfessional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFabTapped = !isFabTapped;
                if (isFabTapped){
                    babHProfessional.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                    handleFrame(new AdminAddHealthProfessional());
                    fabHProfessional.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
                } else {
                    babHProfessional.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    handleFrame(new PatientsListFragment());
                    fabHProfessional.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
                }
            }
        });
    }

    private void reference() {
        babHProfessional = findViewById(R.id.babHProfessional);
        setSupportActionBar(babHProfessional);
        fabHProfessional = findViewById(R.id.fabHProfessional);
    }

    public void fragmentClick(Patient patient){
        Toast.makeText(this,"seleccionó paciente",Toast.LENGTH_LONG).show();
    }

    private void handleFrame(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragmentHProfessional, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }
}
