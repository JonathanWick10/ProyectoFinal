package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.carer.NewCardMemorizame;
import com.jonathan.proyectofinal.fragments.hp.InformationPatientPSFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HealthProfessionalActivity extends AppCompatActivity implements IMainCarer, NavigationView.OnNavigationItemSelectedListener {

    Fragment change = null;
    Fragment contentLayout = null;
    FragmentTransaction transaction;
    //  String patientUID = "";
    String patientIdentification = "", patientUID = "";
    @BindView(R.id.second_drawer_layout_hp)
    DrawerLayout drawerLayout;
    @BindView(R.id.second_navigation_view_hp)
    NavigationView navigationView;
    @BindView(R.id.toolbar_health_professional)
    MaterialToolbar toolbar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Bundle args = new Bundle();
    Patient patientSendFragment = new Patient();
    FirebaseFirestore db;
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    boolean sesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init();
        setContentView(R.layout.activity_health_professional);
        ButterKnife.bind(this);

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//endregion
//region toolbar
        //MaterialToolbar myToolbar = findViewById(R.id.toolbar_health_professional);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        final TextView name_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_name_user);
        final TextView email_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_email_user);
        final CircleImageView image_user = navigationView.getHeaderView(0).findViewById(R.id.img_users_navigation);
        db = FirebaseFirestore.getInstance();
        db.collection(Constants.HealthcareProfesional).document(firebaseUser.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            hp = documentSnapshot.toObject(HealthcareProfessional.class);
                            name_user.setText(hp.getFirstName()+" "+hp.getLastName());
                            email_user.setText(hp.getEmail());
                            sesion = false;
                            Glide.with(HealthProfessionalActivity.this).load(hp.getUriImg()).fitCenter().into(image_user);
                        }
                    }
                });
        db.collection(Constants.Carers).document(firebaseUser.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            carer = documentSnapshot.toObject(Carer.class);
                            name_user.setText(carer.getFirstName()+" "+carer.getLastName());
                            email_user.setText(carer.getEmail());
                            sesion = true;
                            Glide.with(HealthProfessionalActivity.this).load(carer.getUriImg()).fitCenter().into(image_user);
                        }
                    }
                });
        //endregion
        BottomNavigationView navigationView = findViewById(R.id.navigation_health_professional);
        //navigationView.setOnNavigationItemSelectedListener(navListener);
        NavController navController = Navigation.findNavController(this, R.id.content_health_professional);
        NavigationUI.setupWithNavController(navigationView, navController);
        args = getIntent().getExtras();
        if (args!= null){
            patientSendFragment = (Patient) args.getSerializable("patient");
            args.putSerializable("patient",patientSendFragment);

            SharedPreferences preferences = getPreferences(0);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(patientSendFragment);
            editor.putString("serialipatient",json);
            editor.commit();
        }
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
        if(fragmentTag.equals("patient2")){
            change = new InformationPatientPSFragment();
            change.setArguments(args);
            transaction.replace(R.id.info_patient,change).commit();
        }
}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()){
            case (R.id.btn_profile):
                Intent navigation = new Intent(HealthProfessionalActivity.this, NavigationOptions.class);
                navigation.putExtra("option", "profile");
                navigation.putExtra("user_uid", hp.getHpUID());
                navigation.putExtra("user_role", hp.getRole());
                if (sesion == true) {
                    navigation.putExtra("user_uid", carer.getCarerUId());
                    navigation.putExtra("user_role", carer.getRole());
                }
                navigation.putExtra("profile_type", "personal");
                startActivity(navigation);
                break;
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(HealthProfessionalActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        }else {
            getSupportFragmentManager().popBackStack();
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        }
    }
}
