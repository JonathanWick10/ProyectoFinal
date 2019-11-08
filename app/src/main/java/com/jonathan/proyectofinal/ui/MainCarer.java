package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.fragments.carer.CallEmergencyFragment;
import com.jonathan.proyectofinal.fragments.carer.DiaryFragment;
import com.jonathan.proyectofinal.fragments.carer.ExerciseCarerFragment;
import com.jonathan.proyectofinal.fragments.carer.GeneralInformationFragment;
import com.jonathan.proyectofinal.fragments.carer.HeartFragment;
import com.jonathan.proyectofinal.fragments.carer.InformationCarerFragment;
import com.jonathan.proyectofinal.fragments.carer.ManageFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFamilyFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameHomeFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePetsFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizamePlacesFragment;
import com.jonathan.proyectofinal.fragments.carer.NearbyHospitalFragment;
import com.jonathan.proyectofinal.fragments.carer.PhasesEAFragment;
import com.jonathan.proyectofinal.fragments.carer.TestFragment;
import com.jonathan.proyectofinal.fragments.carer.WarningCarerFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainCarer extends AppCompatActivity implements IMainCarer, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout_career)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_career)
    NavigationView navigationView;
    @BindView(R.id.toolbarCareer)
    MaterialToolbar toolbar;
    Fragment change = null;
    FragmentTransaction transaction;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    Carer carer = new Carer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carer);
        ButterKnife.bind(this);
        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


//endregion
        //Function to read the items of BottomNavigation
        BottomNavigationView bottomnNavigationView = findViewById(R.id.navigation_carer);
        NavController navController = Navigation.findNavController(this, R.id.content_carer);
        NavigationUI.setupWithNavController(bottomnNavigationView, navController);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        final TextView name_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_name_user);
        final TextView email_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_email_user);
        final CircleImageView image_user = navigationView.getHeaderView(0).findViewById(R.id.img_users_navigation);
        db = FirebaseFirestore.getInstance();
        db.collection(Constants.Carers).document(firebaseUser.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            carer = documentSnapshot.toObject(Carer.class);
                            name_user.setText(carer.getUserName()+" "+carer.getLastName());
                            email_user.setText(carer.getEmail());
                            Glide.with(MainCarer.this).load(carer.getUriImg()).fitCenter().into(image_user);
                        }
                    }
                });
        drawerToggle.syncState();
    }

    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getSupportFragmentManager().beginTransaction();
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
        else if(fragmentTag.equals(getString(R.string.diary))){
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()) {
            case (R.id.btn_profile):
                Intent navigation = new Intent(MainCarer.this, NavigationOptions.class);
                navigation.putExtra("option", "profile");
                navigation.putExtra("user_uid", carer.getCarerUId());
                navigation.putExtra("user_role", carer.getRole());
                startActivity(navigation);
                break;
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(MainCarer.this, Login.class);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
        finish();
    }
}