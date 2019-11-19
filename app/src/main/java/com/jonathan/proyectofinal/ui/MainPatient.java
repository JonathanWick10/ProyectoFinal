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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.patient.HomePFragment;
import com.jonathan.proyectofinal.fragments.patient.MemorizamePFragment;
import com.jonathan.proyectofinal.fragments.patient.MotorChildFragment;
import com.jonathan.proyectofinal.fragments.patient.NotificationsPFragment;
import com.jonathan.proyectofinal.interfaces.IComunicateFragment;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class MainPatient extends AppCompatActivity implements IComunicateFragment, NavigationView.OnNavigationItemSelectedListener, /*PhysicalExecise.PhysicalExeciseI*/MotorChildFragment.MotorChildFragmentI {

    //region reference
    @BindView(R.id.toolbarPatient)
    MaterialToolbar toolbar;
    @BindView(R.id.drawer_layout_patient)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view_patient)
    NavigationView navigationView;
    private ViewPager viewPager;
    private BottomNavigationView navigation;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    Patient patient = new Patient();
    // endregion

    //region Variables Slider
    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.motivational_1, R.drawable.motivational_2, R.drawable.motivational_3,
            R.drawable.motivational_4, R.drawable.motivational_5, R.drawable.motivational_6, R.drawable.motivational_7,
            R.drawable.motivational_8, R.drawable.motivational_9, R.drawable.motivational_10, R.drawable.motivational_11,
            R.drawable.motivational_12, R.drawable.motivational_13, R.drawable.motivational_14, R.drawable.motivational_15,
            R.drawable.motivational_16, R.drawable.motivational_17, R.drawable.motivational_18, R.drawable.motivational_19,
            R.drawable.motivational_20, R.drawable.motivational_21, R.drawable.motivational_22, R.drawable.motivational_23};
    private int posicion;
    private static final int DURACION = 5000;
    private Timer timer = null;
    @BindView(R.id.iv_motivational)
    ImageSwitcher imgSlider;
    //endregion

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
        firebaseUser = firebaseAuth.getCurrentUser();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        final TextView name_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_name_user);
        final TextView email_user = navigationView.getHeaderView(0).findViewById(R.id.lbl_email_user);
        final CircleImageView image_user = navigationView.getHeaderView(0).findViewById(R.id.img_users_navigation);
        db = FirebaseFirestore.getInstance();
        db.collection(Constants.Patients).document(firebaseUser.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            patient = documentSnapshot.toObject(Patient.class);
                            name_user.setText(patient.getUserName() + " " + patient.getLastName());
                            Glide.with(MainPatient.this).load(patient.getUriImg()).fitCenter().into(image_user);
                        }
                    }
                });
        if (name_user != null && email_user != null) {
            email_user.setText(firebaseUser.getEmail());
        }
        drawerToggle.syncState();
        /*
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFramePatient, new HomePFragment()).commit();
        */

        viewPager = findViewById(R.id.view_pager);
        PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getSupportFragmentManager());
        //adapter.setPhysicalExeciseI(this);
        adapter.setMotorChildFragmentI(this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //region Section Slider
        imgSlider.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(MainPatient.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(MainPatient.this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(MainPatient.this, R.anim.fade_out);
        imgSlider.setInAnimation(fadeIn);
        imgSlider.setOutAnimation(fadeOut);

        startSlider();
        //endregion
    }

    private void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        imgSlider.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);
    }

    @Override
    public void inicarJuego() {
        Intent pasar = new Intent(MainPatient.this, Games.class);
        pasar.putExtra("Game", "Memorama");
        startActivity(pasar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()) {
            case (R.id.btn_profile):
                Intent navigation = new Intent(MainPatient.this, NavigationOptions.class);
                navigation.putExtra("option", "profile");
                navigation.putExtra("user_uid", patient.getPatientUID());
                navigation.putExtra("user_role", patient.getRole());
                navigation.putExtra("profile_type", "personal");
                startActivity(navigation);
                break;
            case R.id.btn_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(MainPatient.this, Login.class);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        }
        super.onBackPressed();
    }

    /*
    @Override
    public void alert(String option, final PhysicalExerciseEntity listExerciseget) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                View dialogView = this.getLayoutInflater().inflate(R.layout.plantilla_physicalexersice_info, null);
                builder.setView(dialogView);
                alertDialog = builder.create();

                Button btn1 = dialogView.findViewById(R.id.btn1);
                btn1.setText("Atras");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btn2 = dialogView.findViewById(R.id.btn2);
                btn2.setText("practicar");
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        final int time = listExerciseget.getTime();
                        final int img = listExerciseget.getImage();

                        Intent pasar = new Intent(MainPatient.this, Games.class);
                        pasar.putExtra("Game", "Physical");
                        pasar.putExtra("Time", time);
                        pasar.putExtra("Image", img);

                        startActivity(pasar);
                    }
                });
                // reference
                TextView tvInformation = dialogView.findViewById(R.id.text_information);
                GifImageView gift = dialogView.findViewById(R.id.gift);

                // Body alert dialog
                gift.setImageResource(listExerciseget.getImage());
                tvInformation.setText(listExerciseget.getDescripcion());
                //
                alertDialog.show();

            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.plantilla_physicalexersice_info, null);
            builder.setView(dialogView);
            alertDialog = builder.create();

            builder.setNeutralButton("atras", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            builder.setPositiveButton("Jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    alertDialog.dismiss();
                    Intent pasar = new Intent(MainPatient.this, Games.class);
                    pasar.putExtra("Game", "Physical");
                    startActivity(pasar);
                }
            });
            builder.setCancelable(false);
            builder.show();

        }

        switch (option) {
            case "eliminar":
                break;
        }

    }
    */

    @Override
    public void alert(String option, final MotorExcercisesAssignment listExcercises) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                View dialogView = this.getLayoutInflater().inflate(R.layout.plantilla_physicalexersice_info, null);
                builder.setView(dialogView);
                alertDialog = builder.create();

                Button btn1 = dialogView.findViewById(R.id.btn1);
                btn1.setText("Atras");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btn2 = dialogView.findViewById(R.id.btn2);
                btn2.setText("practicar");
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        final int time = listExcercises.getTimeExcercise();
                        final String img = listExcercises.getUriGifExcercise();

                        Intent pasar = new Intent(MainPatient.this, Games.class);
                        pasar.putExtra("Game", "Physical");
                        pasar.putExtra("Time", time);
                        pasar.putExtra("Image", img);

                        startActivity(pasar);
                    }
                });
                // reference
                TextView tvInformation = dialogView.findViewById(R.id.text_information);
                GifImageView gift = dialogView.findViewById(R.id.gift);

                // Body alert dialog
                //gift.setImageResource(listExcercises.getTimeExcercise());
                Glide.with(this).load(listExcercises.getUriGifExcercise()).fitCenter().into(gift);
                tvInformation.setText(listExcercises.getLongDescriptionExcercise());
                //
                alertDialog.show();

            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.plantilla_physicalexersice_info, null);
            builder.setView(dialogView);
            alertDialog = builder.create();

            builder.setNeutralButton("atras", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            builder.setPositiveButton("Jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    alertDialog.dismiss();
                    Intent pasar = new Intent(MainPatient.this, Games.class);
                    pasar.putExtra("Game", "Physical");
                    startActivity(pasar);
                }
            });
            builder.setCancelable(false);
            builder.show();

        }

        switch (option) {
            case "eliminar":
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private static class PatientFragmentPageAdapter extends FragmentPagerAdapter {

        //PhysicalExecise.PhysicalExeciseI physicalExeciseI;
        MotorChildFragment.MotorChildFragmentI motorChildFragmentI;

        /*
        public void setPhysicalExeciseI(PhysicalExecise.PhysicalExeciseI physicalExeciseI) {
            //this.physicalExeciseI = physicalExeciseI;
            this.motorChildFragmentI = motorChildFragmentI;
        }
        */

        public void setMotorChildFragmentI(MotorChildFragment.MotorChildFragmentI motorChildFragmentI) {
            this.motorChildFragmentI = motorChildFragmentI;
        }

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomePFragment.newInstance(motorChildFragmentI);
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
}