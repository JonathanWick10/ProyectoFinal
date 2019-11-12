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

public class LoadBrainmher extends AppCompatActivity implements OneSignal.NotificationOpenedHandler {

    private final int DURATION_SPLAH = 2000;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    //variables static
    private final String app_id = "9cd9c148-31f8-4935-b558-eb91e0798617";
    private final String authorization = "Basic MzIxMGViMGYtNjYwOC00N2YyLTkyMTctZmUzZDU4ZWVlZTgw";
    private final String template_id = "505b66c7-a5a9-4aab-8887-869fa4d299be";

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

        iniciarOneSignal();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirect();
            };
        }, DURATION_SPLAH);
    }

    private void iniciarOneSignal() {
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(this)
                .init();

        OneSignal.sendTags("{user_type : \"New User\"}");
        OneSignal.setEmail("jfcerquera2@misena.edu.co");
    }

    private void redirect() {
        if (firebaseUser != null){
            LoginManager loginManager = new LoginManager();
            loginManager.redirectByRole(LoadBrainmher.this,firebaseUser);
        }  else {
            Intent intent = new Intent(LoadBrainmher.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null) {
                Toast.makeText(this, "customkey: " + customKey, Toast.LENGTH_LONG).show();
            }
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Toast.makeText(this, "Button pressed with id: " + result.action.actionID, Toast.LENGTH_LONG).show();
        }
    }
}
