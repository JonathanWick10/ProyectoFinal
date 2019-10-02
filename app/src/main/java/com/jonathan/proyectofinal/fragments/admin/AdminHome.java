package com.jonathan.proyectofinal.fragments.admin;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminHome extends AppCompatActivity {

   @BindView(R.id.babAdmin)
   BottomAppBar bottomAppBar;
   @BindView(R.id.admin_fab)
   FloatingActionButton floatingActionButton;
   private boolean isFabTapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);

        if (savedInstanceState == null){
            handleFrame(new AdminListPSFragment(alertDelete()));
        }

        handleFab();
    }


    // Administrador de fragmentos
    private void handleFrame(Fragment fragment) {
        // obligatorios
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.admin_home_frm_contenedor, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }

    /*private AdminListPSFragment.AdminListPSFragmentI addPsSalud(){
        AdminListPSFragment.AdminListPSFragmentI professionalI = new AdminListPSFragment.AdminListPSFragmentI() {
            @Override
            public void onclickAddPs() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.admin_home_frm_contenedor,new AdminAddHealthProfessional());
                fragmentTransaction.commit();
            }
        };

        return professionalI;
    }*/


    // Interfas para generar alerta de eliminar
    private AdminListPSAdapter.AdminListPSAdapterI  alertDelete() {
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
        return psAdapterI;
    }
    //@OnClick(R.id.admin_fab)
    public void handleFab() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFabTapped = !isFabTapped;
                if (isFabTapped){
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                    handleFrame(new AdminAddHealthProfessional());
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
                } else {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    handleFrame(new AdminListPSFragment(alertDelete()));
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
                }
            }
        });
    }


}
