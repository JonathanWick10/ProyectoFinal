package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.fragments.admin.AdminListPSFragment;

import java.net.ConnectException;

public class AdminHome extends AppCompatActivity {

    private BottomAppBar babadmin;
    private FloatingActionButton fabadmin;
    private boolean isFabTapped = false;
    AdminListPSAdapter.AdminListPSAdapterI psAdapterI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        reference();

        AdminListPSAdapter.AdminListPSAdapterI psAdapterI = new AdminListPSAdapter.AdminListPSAdapterI() {
            @Override
            public void btnEliminar(String pojo) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(AdminHome.this);
                alerta.setTitle(getString(R.string.alert));
                alerta.setMessage(getString(R.string.message_delete)+" - "+pojo);
                alerta.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminHome.this, "Caiste prro", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.show();
            }
        };

        if (savedInstanceState == null){
            handleFrame(new AdminListPSFragment(psAdapterI));
        }
        
        handleFab();
    }

    private void handleFab() {
        fabadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFabTapped = !isFabTapped;
                if (isFabTapped){
                    babadmin.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                    handleFrame(new AdminAddHealthProfessional());
                    fabadmin.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
                } else {
                    babadmin.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    handleFrame(new AdminListPSFragment(psAdapterI));
                    fabadmin.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
                }
            }
        });
    }

    private void reference() {
        babadmin = findViewById(R.id.babAdmin);
        fabadmin = findViewById(R.id.fabAdmin);
    }

    private void handleFrame(Fragment fragment){
        // obligatorios
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.admin_home_frm_contenedor, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }
}
