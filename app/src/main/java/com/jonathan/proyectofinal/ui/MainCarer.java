package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.CallEmergencyFragment;
import com.jonathan.proyectofinal.fragments.carer.DiaryFragment;
import com.jonathan.proyectofinal.fragments.carer.ExerciseCarerFragment;
import com.jonathan.proyectofinal.fragments.carer.GeneralInformationFragment;
import com.jonathan.proyectofinal.fragments.carer.HeartFragment;
import com.jonathan.proyectofinal.fragments.carer.InformationCarerFragment;
import com.jonathan.proyectofinal.fragments.carer.ManageFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.fragments.carer.NearbyHospitalFragment;
import com.jonathan.proyectofinal.fragments.carer.PhasesEAFragment;
import com.jonathan.proyectofinal.fragments.carer.TestFragment;
import com.jonathan.proyectofinal.fragments.carer.WarningCarerFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MainCarer extends AppCompatActivity implements IMainCarer {

    Fragment change = null;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carer);
        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//endregion
        //Function to read the items of BottomNavigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_carer);
        NavController navController = Navigation.findNavController(this, R.id.content_carer);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getSupportFragmentManager().beginTransaction();
        // Listen to the Button Call for other Fragments in different Views
        if(fragmentTag.equals(getString(R.string.menu_memorizame))){
            change = new MemorizameFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        if(fragmentTag.equals(getString(R.string.my_care))){
            change = new HeartFragment();
            transaction.replace(R.id.containerHome,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.manage))){
            Intent intent = new Intent(MainCarer.this,PatientsList.class);
            startActivity(intent);
            //change = new ManageFragment();
            //transaction.replace(R.id.containerHome,change).commit();
        }
        if(fragmentTag.equals(getString(R.string.diary))){
            change = new DiaryFragment();
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
        else if(fragmentTag.equals(getString(R.string.information_carer))){
            change = new InformationCarerFragment();
            transaction.replace(R.id.viewpagerh,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.exercise_carer))){
            change = new ExerciseCarerFragment();
            transaction.replace(R.id.viewpagerh,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.advice_carer))){
            change = new WarningCarerFragment();
            transaction.replace(R.id.viewpagerh,change).commit();
        }
        else if(fragmentTag.equals(getString(R.string.test))){
            change = new TestFragment();
            transaction.replace(R.id.content_carer,change).commit();
        }
    }
}