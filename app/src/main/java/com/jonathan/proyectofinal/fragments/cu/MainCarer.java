package com.jonathan.proyectofinal.fragments.cu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;

public class MainCarer extends AppCompatActivity implements IMainCarer{

    Fragment pass = null;
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
        String fragmentTag = "";
        HomeFragment fragment =new HomeFragment();
        doFragmentTransaction(fragment,fragmentTag, true);
    }

    private void doFragmentTransaction(Fragment fragment, String Tag,boolean b){
        //Possibility of changing the Fragment
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_carer,fragment);
        if (b){
            transaction.addToBackStack(Tag);
        }
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            //Listener the BottomNavigation
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.Home:
                            pass = new HomeFragment();
                            break;
                        case R.id.List:
                            pass = new ListFragment();
                            break;
                        case R.id.Emergency:
                            pass = new EmergencyFragment();
                            break;
                        case R.id.Information:
                            pass = new InformationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_carer,pass).commit();
                    return true;
                }
            };

    @Override
    public void inflateFragment(String fragmentTag) {
        // Listen to the Button Call for other Fragments in different Views
        if(fragmentTag.equals(getString(R.string.my_care))){
            HeartFragment fragment = new HeartFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
        else if(fragmentTag.equals(getString(R.string.Manage))){
            ManageFragment fragment = new ManageFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
        if(fragmentTag.equals(getString(R.string.Diary))){
            DiaryFragment fragment = new DiaryFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
        else if(fragmentTag.equals(getString(R.string.Test))){
            TestFragment fragment = new TestFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
        else if(fragmentTag.equals(getString(R.string.Nearby_hospitals))){
            NearbyHospitalFragment fragment = new NearbyHospitalFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
        else if(fragmentTag.equals(getString(R.string.Emergency_Contacts))){
            CallEmergencyFragment fragment = new CallEmergencyFragment();
            doFragmentTransaction(fragment,fragmentTag, true);
        }
    }
}