package com.jonathan.proyectofinal.fragments.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.fragments.games.Memorama;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.ui.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminHome extends AppCompatActivity implements IMainCarer,AdminAddHealthProfessional.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.babAdmin)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fabAdmin)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbarAdmin)
    MaterialToolbar toolbar;

    @BindView(R.id.drawer_layout_admin)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_admin)
    NavigationView navigationView;
    private boolean isFabTapped = false;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            handleFrame(new AdminListPSFragment(alertDelete()));
        }
    }

    // Interfas para generar alerta de eliminar
    private AdminListPSAdapter.AdminListPSAdapterI alertDelete() {
        AdminListPSAdapter.AdminListPSAdapterI psAdapterI = new AdminListPSAdapter.AdminListPSAdapterI() {
            @Override
            public void btnEliminar(HealthcareProfessional pojo) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(AdminHome.this);
                alerta.setTitle(getString(R.string.alert));
                alerta.setMessage(getString(R.string.message_delete) + " - " + pojo);
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

    //Método para gestión del click en floating action button
    @OnClick(R.id.fabAdmin)
    public void handleFab() {
        isFabTapped = !isFabTapped;
        if (isFabTapped) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            handleFrame(new AdminAddHealthProfessional());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new AdminListPSFragment(alertDelete()));
            //handleFrame(new Memorama());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    // Administrador de fragmentos
    private void handleFrame(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.admin_home_frm_contenedor, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //String itemName = (String) item.getTitle();
        closeDrawer();
        switch (item.getItemId()){
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(AdminHome.this, Login.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
        finish();
    }

    @Override
    public void inflateFragment(String fragmentTag) {
        if(fragmentTag.equals(getString(R.string.list))){
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new AdminListPSFragment(alertDelete()));
            //handleFrame(new Memorama());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavigationViewHP navigationDrawerFragment = new NavigationViewHP();
                navigationDrawerFragment.show(getSupportFragmentManager(), navigationDrawerFragment.getTag());
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
    */

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

}
