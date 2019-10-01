package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.hp.GraphPSFragment;
import com.jonathan.proyectofinal.fragments.hp.InformationPSFragment;
import com.jonathan.proyectofinal.fragments.hp.NotificationPSFragment;
import com.jonathan.proyectofinal.fragments.hp.TherapyPSFragment;

public class HealthProfessionalActivity extends AppCompatActivity {

    Fragment change = null;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_health_professional);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        BottomNavigationView navigationView = findViewById(R.id.navigation_health_professional);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void init() {

        InformationPSFragment fragment = new InformationPSFragment();
        doFragmentTransaction(fragment, true);


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

    private void doFragmentTransaction(Fragment fragment, boolean b){
        //Possibility of changing the Fragment
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_health_professional,fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId())
            {
                case R.id.item_ps_info:
                    InformationPSFragment informationPSFragment = new InformationPSFragment();
                    doFragmentTransaction(informationPSFragment, true);
                    break;

                case R.id.item_ps_therapy:
                    TherapyPSFragment therapyPSFragment = new TherapyPSFragment();
                    doFragmentTransaction(therapyPSFragment, true);
                    break;

                case R.id.item_ps_graph:
                    GraphPSFragment graphPSFragment = new GraphPSFragment();
                    doFragmentTransaction(graphPSFragment, true);
                    break;

                case R.id.item_ps_notification:
                    NotificationPSFragment notificationPSFragment = new NotificationPSFragment();
                    doFragmentTransaction(notificationPSFragment, true);
                    break;
            }
            return true;
        }
    };

/*
    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getSupportFragmentManager().beginTransaction();
        // Listen to the Button Call for other Fragments in different Views
        if(fragmentTag.equals(getString(R.string.patient))){
            change = new InformationPatientPSFragment();
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.carer))){
            change = new InformationCarerPSFragment();
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
    }*/
}
