package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.general.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationOptions extends AppCompatActivity {

    @BindView(R.id.toolbar_navigation_option)
    MaterialToolbar toolbar;
    String option, uid, role, profile_type;
    Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_options);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){
            option = getIntent().getExtras().getString("option");
            uid = getIntent().getExtras().getString("user_uid");
            role = getIntent().getExtras().getString("user_role");
            profile_type = getIntent().getExtras().getString("profile_type");
            switch (option){
                case "profile":
                    args.putString("userUid", uid);
                    args.putString("userRole", role);
                    args.putString("profileType", profile_type);
                    handleFrame(new ProfileFragment(), args);
                    break;
            }
        }
    }

    //Método que se encarga de gestionarl el llenado del frame con fragmentos
    private void handleFrame(Fragment fragment, Bundle args) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.content_navigation_option, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
