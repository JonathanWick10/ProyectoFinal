package com.jonathan.proyectofinal.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.Login;

import java.util.Arrays;
import java.util.concurrent.Executor;

public class LoginManager {

    //region Variables
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private GoogleSignInClient googleSignInClient;
    private CallbackManager callbackManager = CallbackManager.Factory.create();

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
    String role;
    //endregion

    public void emailPasswordLogin(final Context context, String email, String password) {
        // INICIO
        String uID = user.getUid();
        DocumentReference docRef = db.collection(Constants.Adminds).document(uID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    admin = documentSnapshot.toObject(Admin.class);
                    role = admin.getRole();
                    //Log.d("Hola", "Rol: "+role);
                    if (role.isEmpty()){
                        Toast.makeText(context, "Nada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Rol: "+role, Toast.LENGTH_SHORT).show();
                        //redirect(role);
                    }
                } else {
                    Toast.makeText(context, "No exist", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR:", e.toString());
            }
        });
        // FIN

        /*
        role = adminManager.admintByUID(uID);

        funcionaporfavor g = new funcionaporfavor();
        g.execute();

        if (role.isEmpty()){
            Toast.makeText(context, "Nada", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Rol: "+role, Toast.LENGTH_SHORT).show();
        }
        */
    }

    public void createUserWithEmailAndPassword(final Context context, String email, String password) {

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

    public boolean userLoggedIn() {
        Boolean loggedIn;
        if (user != null) {
            loggedIn = true;
        } else {
            loggedIn = false;
        }
        return loggedIn;
    }

    public GoogleSignInClient googleClientSettings(Context context) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);

        return googleSignInClient;
    }

    public void handleGoogleAccessToken(GoogleSignInResult result, final Context context) {
        AuthCredential credential = GoogleAuthProvider.getCredential(result.getSignInAccount().getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(context, context.getString(R.string.auth_fail), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void handleFacebookAccessToken(AccessToken token, final Context context) {
        Log.d("fb", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FB", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //  updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB", "signInWithCredential:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //     updateUI();
                        }

                        // ...
                    }
                });
    }

    public void redirect(String role){
        switch (role){
            case "Admin":
                //REDIRECCIONAR AL ADMIN HOME
                break;
        }
    }

    /*
    private class funcionaporfavor extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            String uID = user.getUid();
            //admin = adminManager.admintByUID(uID);
            role = adminManager.admintByUID(uID);
            return null;
            hp = hpManager.hpByUID(uID);
            carer = carerManager.carertByUID(uID);
            patient = patientsManager.patientByUID(uID);

            if (role != null) {
                role = admin.getRole();
            } else if (hp.getRole() != null) {
                role = hp.getRole();
            } else if (carer.getRole() != null) {
                role = carer.getRole();
            } else if (patient.getRole() != null) {
                role = patient.getRole();
            }
            //return null;
        }
    }
    */

    /*
    //region Find role
    public String findRole(String uID) {
        String role = "";
        admin = adminManager.admintByUID(uID);
        hp = hpManager.hpByUID(uID);
        carer = carerManager.carertByUID(uID);
        patient = patientsManager.patientByUID(uID);
        if (admin.getRole() != null) {
            role = admin.getRole();
        } else if (hp.getRole() != null) {
            role = hp.getRole();
        } else if (carer.getRole() != null) {
            role = carer.getRole();
        } else if (patient.getRole() != null) {
            role = patient.getRole();
        }
        return role;
    }
    //endregion
    */
}
