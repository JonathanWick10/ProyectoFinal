package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.CallEmergencyFragment;
import com.jonathan.proyectofinal.fragments.carer.DiaryFragment;
import com.jonathan.proyectofinal.fragments.carer.EmergencyFragment;
import com.jonathan.proyectofinal.fragments.carer.GeneralInformationFragment;
import com.jonathan.proyectofinal.fragments.carer.HeartFragment;
import com.jonathan.proyectofinal.fragments.carer.HomeFragment;
import com.jonathan.proyectofinal.fragments.carer.InformationFragment;
import com.jonathan.proyectofinal.fragments.carer.ListFragment;
import com.jonathan.proyectofinal.fragments.carer.ManageFragment;
import com.jonathan.proyectofinal.fragments.carer.NearbyHospitalFragment;
import com.jonathan.proyectofinal.fragments.carer.PhasesEAFragment;
import com.jonathan.proyectofinal.fragments.carer.TestFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MainCarer extends AppCompatActivity implements IMainCarer {

    Fragment change = null;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carer);
        //Function to read the items of BottomNavigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_carer);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void doFragmentTransaction(Fragment fragment, boolean b){
        //Possibility of changing the Fragment
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_carer,fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            //Listener the BottomNavigation
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.Home:
                            //active = new HomeFragment();
                            HomeFragment homeFragment = new HomeFragment();
                            doFragmentTransaction(homeFragment, true);
                            break;
                        case R.id.List:
                            //active = new ListFragment();
                            ListFragment listFragment = new ListFragment();
                            doFragmentTransaction(listFragment, true);
                            break;
                        case R.id.Emergency:
                            //active = new EmergencyFragment();
                            EmergencyFragment emergencyFragment = new EmergencyFragment();
                            doFragmentTransaction(emergencyFragment, true);
                            break;
                        case R.id.Information:
                            //active = new InformationFragment();
                            InformationFragment informationFragment = new InformationFragment();
                            doFragmentTransaction(informationFragment, true);
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.content_carer,active).commit();
                    return true;
                }
            };

    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getSupportFragmentManager().beginTransaction();
        // Listen to the Button Call for other Fragments in different Views
        if(fragmentTag.equals(getString(R.string.my_care))){
            change = new HeartFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.manage))){
            change = new ManageFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        if(fragmentTag.equals(getString(R.string.diary))){
            change = new DiaryFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.test))){
            change = new TestFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.nearby_hospitals))){
            change = new NearbyHospitalFragment();
            transaction.replace(R.id.containerpage,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.emergency_contacts))){
            change = new CallEmergencyFragment();
            transaction.replace(R.id.containerpage,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.phases_ea))){
            change = new PhasesEAFragment();
            transaction.replace(R.id.viewpager,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.general_information))){
            change = new GeneralInformationFragment();
            transaction.replace(R.id.viewpager,change).commit();
        }
    }
}