package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFamilyFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameHomeFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePetsFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePlacesFragment;
import com.jonathan.proyectofinal.fragments.hp.CognitiveTherapyPSFragment;
import com.jonathan.proyectofinal.fragments.hp.InformationCarerPSFragment;
import com.jonathan.proyectofinal.fragments.hp.InformationPatientPSFragment;
import com.jonathan.proyectofinal.fragments.hp.MotorTherapyPSFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class HealthProfessionalActivity extends AppCompatActivity implements IMainCarer {

    Fragment change = null;
    FragmentTransaction transaction;
  //  String patientUID = "";
  String patientIdentification = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init();
        setContentView(R.layout.activity_health_professional);

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//endregion
//region toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar_health_professional);
        setSupportActionBar(myToolbar);
        //endregion
        BottomNavigationView navigationView = findViewById(R.id.navigation_health_professional);
        //navigationView.setOnNavigationItemSelectedListener(navListener);
        NavController navController = Navigation.findNavController(this, R.id.content_health_professional);
        NavigationUI.setupWithNavController(navigationView, navController);
       //patientUID= getIntent().getExtras().getString("patientUID");
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
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.carer))){
            change = new InformationCarerPSFragment();
            transaction.replace(R.id.containerPageInformationPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.cognitive))){
            change = new CognitiveTherapyPSFragment();
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.menu_memorizame))){
            change = new MemorizameFragment();
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.motor))){
            change = new MotorTherapyPSFragment();
            transaction.replace(R.id.containerPageTherapyPS,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_family_questions))){
            change = new MemorizameFamilyFragment();
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_family_questions))){
            change = new MemorizameFamilyFragment();
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_pets_questions))){
            change = new MemorizamePetsFragment();
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_home_questions))){
            change = new MemorizameHomeFragment();
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.tab_places_questions))){
            change = new MemorizamePlacesFragment();
            transaction.replace(R.id.containerMemorizame,change).commit();
        }
    }
}
