package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.patient.HomePFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.fragments.patient.NotificationsPFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPatient extends AppCompatActivity {

    @BindView(R.id.bottonNavigationViewPatient)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, new HomePFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home_patient:
                            selectedFragment = new HomePFragment();
                            break;
                        case R.id.memorizame_patient:
                            selectedFragment = new MemorizamePFragment();
                            break;
                        case R.id.notifications_patient:
                            selectedFragment = new NotificationsPFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, selectedFragment).commit();
                    return true;
                }
            };
}
