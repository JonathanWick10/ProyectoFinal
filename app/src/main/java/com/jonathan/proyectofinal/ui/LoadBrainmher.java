package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.database.LoginManager;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalNotificationManager;

import org.json.JSONObject;

public class LoadBrainmher extends AppCompatActivity  {

    private final int DURATION_SPLAH = 2000;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_brainmher);

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //endregion
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        setContentView(R.layout.activity_load_brainmher);
        //Método para definir parametros y funcionalidad al ejecutar el splash screen

        redirect();
    }



    private void redirect() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser != null) {
                    LoginManager loginManager = new LoginManager();
                    loginManager.redirectByRole(LoadBrainmher.this, firebaseUser);
                } else {
                    Intent intent = new Intent(LoadBrainmher.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            };
        }, DURATION_SPLAH);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
