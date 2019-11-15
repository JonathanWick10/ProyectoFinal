package com.jonathan.proyectofinal.fragments.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.fragments.games.Memorama;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;
import com.jonathan.proyectofinal.ui.Login;
import com.jonathan.proyectofinal.ui.MainCarer;
import com.jonathan.proyectofinal.ui.NavigationOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdminHome extends AppCompatActivity implements IMainCarer,AdminAddHealthProfessional.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.babAdmin)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fabAdmin)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbarAdmin)
    MaterialToolbar toolbar;

    @BindView(R.id.drawer_layout_admin)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_admin)
    NavigationView navigationView;
    private boolean isFabTapped = false;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Context context;
    String uIdAdmind;
    ProgressDialog progressDialog;
    FirebaseFirestore db;
    Admin admin = new Admin();
    public long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        context = this;
        progressDialog = new ProgressDialog(AdminHome.this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        uIdAdmind = firebaseUser.getUid();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        final TextView name_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_name_user);
        final TextView email_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_email_user);
        final CircleImageView image_user = navigationView.getHeaderView(0).findViewById(R.id.img_users_navigation);
        db.collection(Constants.Adminds).document(uIdAdmind).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()){
                    admin = documentSnapshot.toObject(Admin.class);
                    if (name_user!=null && email_user!=null) {
                        name_user.setText(admin.getFirstName()+" "+admin.getLastName());
                        email_user.setText(admin.getEmail());
                        Glide.with(AdminHome.this).load(admin.getUriImage()).fitCenter().into(image_user);
                    }
                }
            }
        });

        if (savedInstanceState == null) {
            handleFrame(new AdminListPSFragment());
        }
    }

    //Método para gestión del click en floating action button
    @OnClick(R.id.fabAdmin)
    public void handleFab() {
        isFabTapped = !isFabTapped;
        if (isFabTapped) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            handleFrame(new AdminAddHealthProfessional());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_go_back));
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new AdminListPSFragment());
            //handleFrame(new Memorama());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    // Administrador de fragmentos
    private void handleFrame(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.admin_home_frm_contenedor, fragment);  // remplaza un fragmento de contenedor
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //String itemName = (String) item.getTitle();
        closeDrawer();
        switch (item.getItemId()){
            case (R.id.btn_profile):
                Intent navigation = new Intent(AdminHome.this, NavigationOptions.class);
                navigation.putExtra("option", "profile");
                navigation.putExtra("user_uid", admin.getAdminUId());
                navigation.putExtra("user_role", admin.getRole());
                navigation.putExtra("profile_type", "personal");
                startActivity(navigation);
                break;
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(AdminHome.this, Login.class);
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

        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
        }else if (backPressedTime + 4000 > System.currentTimeMillis()) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                closeDrawer();
            }
            super.onBackPressed();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void inflateFragment(String fragmentTag) {
        if(fragmentTag.equals(getString(R.string.list))){
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            handleFrame(new AdminListPSFragment());
            //handleFrame(new Memorama());
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_person_add));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavigationViewHP navigationDrawerFragment = new NavigationViewHP();
                navigationDrawerFragment.show(getSupportFragmentManager(), navigationDrawerFragment.getTag());
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    /*private AdminListPSFragment.AdminListPSFragmentI addPsSalud(){
        AdminListPSFragment.AdminListPSFragmentI professionalI = new AdminListPSFragment.AdminListPSFragmentI() {
            @Override
            public void onclickAddPs() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.admin_home_frm_contenedor,new AdminAddHealthProfessional());
                fragmentTransaction.commit();
            }
        };

        return professionalI;
    }*/

}
