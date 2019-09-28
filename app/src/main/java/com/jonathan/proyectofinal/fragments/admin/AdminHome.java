package com.jonathan.proyectofinal.fragments.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
        fragmentMethos();

    }
    // Administrador de fragmentos
    private void fragmentMethos() {
        // obligatorios
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.admin_home_frm_contenedor,new AdminListPSFragment(alertDelete()));  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }

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
}
