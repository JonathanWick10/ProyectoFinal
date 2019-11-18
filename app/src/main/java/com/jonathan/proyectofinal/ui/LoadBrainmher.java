package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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

    //region Variables for permissions
    final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 10;
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 11;
    final int MY_PERMISSIONS_REQUEST_CWRITE_EXTERNAL_STORAGE = 12;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_brainmher);

        iniciarOneSignal();

        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //endregion
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        //Método para definir parametros y funcionalidad al ejecutar el splash screen

        //region Permission before in to redirect by role
        if(ActivityCompat.checkSelfPermission(LoadBrainmher.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            // Aquí ya está concedido, procede a realizar lo que tienes que hacer
            redirect();
        }else{
            // Aquí lanzamos un dialog para que el usuario confirme si permite o no el realizar llamadas
            ActivityCompat.requestPermissions(LoadBrainmher.this, new String[]{ Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
        //endregion
    }

    public void iniciarOneSignal() {
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {

                        //cuando se abre una notificacion
                        OSNotificationAction.ActionType actionType = result.action.type;
                        JSONObject data = result.notification.payload.additionalData;
                        String customKey;

                        //meta data, osea datos ocultos
                        if (data != null) {
                            customKey = data.optString("keyMetaData", "");
                        }

                        //id de los botones
                        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                            //Toast.makeText(LoadBrainmher.this, "Button pressed with id: " + result.action.actionID, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .init();
        OneSignal.enableVibrate(true);
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

    // Y finalmente recibimos la respuesta del usuario en un método de tipo `@Override` así:

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // PERMISO CONCEDIDO, procede a realizar lo que tienes que hacerred
                    redirect();
                } else {
                    // PERMISO DENEGADO
                    redirect();
                }
                return;
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
