package com.jonathan.proyectofinal.fragments.Cu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;

public class MainCarer extends AppCompatActivity {

    Fragment activ = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carer);
        BottomNavigationView navigationView = findViewById(R.id.navigation_carer);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.Home:
                            activ = new HomeFragment();
                            break;
                        case R.id.List:
                            activ = new ListFragment();
                            break;
                        case R.id.Emergency:
                            activ = new EmergencyFragment();
                            break;
                        case R.id.Information:
                            activ = new InformationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_carer,activ).commit();
                    return true;
                }
            };
}
