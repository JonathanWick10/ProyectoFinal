package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFamilyFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameHomeFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePetsFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePlacesFragment;
import com.jonathan.proyectofinal.fragments.carer.NewCardMemorizame;
import com.jonathan.proyectofinal.fragments.hp.CognitiveTherapyPSFragment;
import com.jonathan.proyectofinal.fragments.hp.InformationCarerPSFragment;
import com.jonathan.proyectofinal.fragments.hp.InformationPatientPSFragment;
import com.jonathan.proyectofinal.fragments.hp.MotorTherapyPSFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthProfessionalActivity extends AppCompatActivity implements IMainCarer, NavigationView.OnNavigationItemSelectedListener {

    Fragment change = null;
    FragmentTransaction transaction;
    //  String patientUID = "";
    String patientIdentification = "", patientUID = "";
    @BindView(R.id.second_drawer_layout_hp)
    DrawerLayout drawerLayout;
    @BindView(R.id.second_navigation_view_hp)
    NavigationView navigationView;
    @BindView(R.id.toolbar_health_professional)
    MaterialToolbar toolbar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init();
        setContentView(R.layout.activity_health_professional);
        ButterKnife.bind(this);

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//endregion
//region toolbar
        //MaterialToolbar myToolbar = findViewById(R.id.toolbar_health_professional);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        TextView name_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_name_user);
        TextView email_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_email_user);
        if (name_user!=null && email_user!=null) {
            name_user.setText(firebaseUser.getDisplayName());
            email_user.setText(firebaseUser.getEmail());
        }
        //endregion
        BottomNavigationView navigationView = findViewById(R.id.navigation_health_professional);
        //navigationView.setOnNavigationItemSelectedListener(navListener);
        NavController navController = Navigation.findNavController(this, R.id.content_health_professional);
        NavigationUI.setupWithNavController(navigationView, navController);
        patientUID = getIntent().getExtras().getString("patientUID");
        args.putString("patientUID",patientUID);
      //  Toast.makeText(this, "patientUID:"+patientUID, Toast.LENGTH_LONG).show();
        patientIdentification=getIntent().getExtras().getString("patientIdentification");
        Toast.makeText(this, "patientIdentification:  "+patientIdentification, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ps_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ps_action_patient_list:
                startSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startSettings() {
        startActivity(new Intent(HealthProfessionalActivity.this, PatientsList.class));
    }

    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getSupportFragmentManager().beginTransaction();
        // Listen to the Button Call for other Fragments in different Views
        if(fragmentTag.equals(getString(R.string.patient))){
            change = new InformationPatientPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
        else if(fragmentTag.equals("patient2")){
            change = new InformationPatientPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.info_patient,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.carer))){
            change = new InformationCarerPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.cognitive))){
            change = new CognitiveTherapyPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.menu_memorizame))){
            change = new MemorizameFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.motor))){
            change = new MotorTherapyPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_family_questions))){
            change = new MemorizameFamilyFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_family_questions))){
            change = new MemorizameFamilyFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_pets_questions))){
            change = new MemorizamePetsFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_home_questions))){
            change = new MemorizameHomeFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_places_questions))){
            change = new MemorizamePlacesFragment();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.family_questions_img))){
            change = new NewCardMemorizame();
            change.setArguments(args);
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()){
            case (R.id.btn_profile):
                Intent navigation = new Intent(HealthProfessionalActivity.this, NavigationOptions.class);
                navigation.putExtra("option", "profile");
                startActivity(navigation);
                break;
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(HealthProfessionalActivity.this, Login.class);
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
}
