package com.jonathan.proyectofinal.fragments.carer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MainCarer extends AppCompatActivity implements IMainCarer {

    Fragment active = null, change = null;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(); //Star to the first Fragment
        setContentView(R.layout.activity_main_carer);
        //Function to read the items of BottomNavigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_carer);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void init(){
        //Star to the first Fragment
        HomeFragment fragment =new HomeFragment();
        doFragmentTransaction(fragment, true);
    }

    private void doFragmentTransaction(Fragment fragment, boolean b){
        //Possibility of changing the Fragment
        transaction = getSupportFragmentManager().beginTransaction();
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
                            active = new HomeFragment();
                            break;
                        case R.id.List:
                            active = new ListFragment();
                            break;
                        case R.id.Emergency:
                            active = new EmergencyFragment();
                            break;
                        case R.id.Information:
                            active = new InformationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_carer,active).commit();
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
    }
}