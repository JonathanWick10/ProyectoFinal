package com.jonathan.proyectofinal.ui;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Toast;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.general.NotificationMgAddFragment;
import com.jonathan.proyectofinal.fragments.general.NotificationMgReadFragment;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationMg extends AppCompatActivity{

    //referencias
    @BindView(R.id.notification_add)
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //vista y butter knife
        setContentView(R.layout.notification_manager);
        ButterKnife.bind(this);

        //propiedades del toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listener el boton add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cargar el de add
                loadFragment(false);
            }
        });

        //cargar el de read
        loadFragment(true);
        iniciarOneSignal(this);
    }

    private void loadFragment(boolean isRead){
        //load new fragment
        FragmentManager fg = getSupportFragmentManager();
        FragmentTransaction ft = fg.beginTransaction();
        Fragment fragment;

        if(isRead){
            //fragmento de leer las notificationes
            fragment = new NotificationMgReadFragment();
        }else{
            //fragmento de crear una notificacion
            fragment = new NotificationMgAddFragment();
        }
        ft.replace(R.id.notification_container, fragment);
        ft.commit();
    }

    public static void iniciarOneSignal(final Context context) {
        // OneSignal Initialization
        OneSignal.startInit(context)
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
                            customKey = data.optString("itemKeyClickOne", "");
                            Toast.makeText(context, "itemKeyClickOne: " + customKey, Toast.LENGTH_LONG).show();

                            customKey = data.optString("itemKeyClickTwo", "");
                            Toast.makeText(context, "itemKeyClickTwo: " + customKey, Toast.LENGTH_LONG).show();
                        }

                        //id de los botones
                        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                            Toast.makeText(context, "Button pressed with id: " + result.action.actionID, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .init();
        OneSignal.enableVibrate(true);

        //esto es para que cuando se creer el usuario en onesignal le asigne estas propieades
        //OneSignal.sendTags("{user_type : \"New User\"}");
        //OneSignal.setEmail("jfcerquera2@misena.edu.co");
    }
}
