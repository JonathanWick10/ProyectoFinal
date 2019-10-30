package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;
import com.jonathan.proyectofinal.fragments.patient.HomePFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.fragments.patient.NotificationsPFragment;
import com.jonathan.proyectofinal.interfaces.IComunicateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPatient extends AppCompatActivity implements IComunicateFragment, NavigationView.OnNavigationItemSelectedListener, PhysicalExecise.PhysicalExeciseI {

    @BindView(R.id.toolbarPatient)
    MaterialToolbar toolbar;
    @BindView(R.id.drawer_layout_patient)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_patient)
    NavigationView navigationView;
    private ViewPager viewPager;
    private BottomNavigationView navigation;
    FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_patient:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.memorizame_patient:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.notifications_patient:
                    viewPager.setCurrentItem(2);
                    return true;
                    /*
                case R.id.profile_patient:
                    viewPager.setCurrentItem(3);
                    return true;
                    */
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        /*
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, new HomePFragment()).commit();
        */
        viewPager = findViewById(R.id.view_pager);
        PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getSupportFragmentManager());
        adapter.setPhysicalExeciseI(this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // METODO PROVISIONAL CON INTERFAZ PARA REDIRECCIONAR AL JUEGO
    @Override
    public void inicarJuego() {
        //Toast.makeText(this, "Iniciar juego desde actividad", Toast.LENGTH_SHORT).show();
        //(viewPager.setCurrentItem(3);
        Intent pasar = new Intent(MainPatient.this, Games.class);
        pasar.putExtra("Game","Memorama");
        startActivity(pasar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()){
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(MainPatient.this, Login.class);
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

    @Override
    public void alert(String option) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.plantilla_physicalexersice_info, null);
                builder.setView(dialogView);
                alertDialog=builder.create();

                Button btn1=(Button)dialogView.findViewById(R.id.btn1);
                btn1.setText("Atras");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn2);
                btn2.setText("Jugar");
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation=dialogView.findViewById(R.id.text_information);
                tvInformation.setText(R.string.exersice_description);
                alertDialog.show();




            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        else{
            builder.setNeutralButton("atras", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            builder.setPositiveButton("Jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setCancelable(false);
            builder.show();

        }

        switch (option){
            case "eliminar": break;
        }

    }

    private static class PatientFragmentPageAdapter extends FragmentPagerAdapter {


        PhysicalExecise.PhysicalExeciseI physicalExeciseI ;

        public void setPhysicalExeciseI(PhysicalExecise.PhysicalExeciseI physicalExeciseI) {
            this.physicalExeciseI = physicalExeciseI;
        }

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomePFragment.newInstance(physicalExeciseI);
                case 1:
                    return MemorizamePFragment.newInstance();
                case 2:
                    return NotificationsPFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }





    /*
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
     */
}