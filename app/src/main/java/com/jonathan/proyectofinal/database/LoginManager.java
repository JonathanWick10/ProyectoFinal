package com.jonathan.proyectofinal.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.ui.Login;

import java.util.concurrent.Executor;

public class LoginManager {

    //region Variables
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private GoogleSignInClient googleSignInClient;

    //Instance Managers
    AdminManager adminManager = new AdminManager();
    HPManager hpManager = new HPManager();
    CarerManager carerManager = new CarerManager();
    PatientsManager patientsManager = new PatientsManager();
    //Instance Pojo
    Admin admin = new Admin();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    Patient patient = new Patient();
    //endregion

    public void emailPasswordLogin (final Context context, String email, String password){


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = firebaseAuth.getCurrentUser();
                            String jona = findRole(user.getUid());
                            context.startActivity(new Intent(context, AdminHome.class));

                        } else {
                            Toast.makeText(context, context.getString(R.string.auth_fail),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void createUserWithEmailAndPassword(final Context context, String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, context.getString(R.string.success_regis),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "ERROR EN EL REGISTRO!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public boolean userLoggedIn(){
        Boolean loggedIn;
        if (user != null) {
            loggedIn = true;
        }
        else {
            loggedIn = false;
        }
        return loggedIn;
    }

    public void loginwithGoogle(final Context context){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    //region Find role
    public String findRole(String uID){
        String role="";
        admin = adminManager.admintByUID(uID);
        hp = hpManager.hpByUID(uID);
        carer = carerManager.carertByUID(uID);
        patient = patientsManager.patientByUID(uID);
        if(admin.getRol()!= null){
            role = admin.getRol();
        }else if (hp.getRol()!= null){
            role = hp.getRol();
        }else if (carer.getRol()!= null){
            role = carer.getRol();
        }else if (patient.getRol()!= null){
            role = patient.getRol();
        }
        return role;
    }
    //endregion

}
