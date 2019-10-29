package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.AddPatients;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.fragments.admin.AdminListPSFragment;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.List;

public class PatientsList extends AppCompatActivity implements IMainCarer,AddPatients.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.babHProfessional)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fabHProfessional)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.drawer_layout_hp)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_hp)
    NavigationView navigationView;
    @BindView(R.id.toolbarHP)
    MaterialToolbar toolbar;
    FirebaseAuth firebaseAuth;
    private boolean isFabTapped = false;
    private IPatientsListFragmentListener fragmentListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);
        ButterKnife.bind(this);

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

//endregion

        //region Fragment PatientsList
        /*
        PatientsListFragment fragment = (PatientsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (IPatientsListFragmentListener) fragment;
        */
        //endregion

        if (savedInstanceState == null) {
            handleFrame(new PatientsListFragment());
        }
    }

    //Método para gestión del click en floating action button
    @OnClick(R.id.fabHProfessional)
    void handleFab() {
        isFabTapped = !isFabTapped;
        if (isFabTapped) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            handleFrame(new AddPatients());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new PatientsListFragment());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    public void fragmentClick(Patient patient) {
        Toast.makeText(this, "seleccionó paciente", Toast.LENGTH_LONG).show();
    }

    //Administrador de fragmentos
    private void handleFrame(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragmentHProfessional, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()){
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(PatientsList.this, Login.class);
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
        if(fragmentTag.equals("prueba")){
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new PatientsListFragment());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
