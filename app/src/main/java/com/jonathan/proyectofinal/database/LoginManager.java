package com.jonathan.proyectofinal.database;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.jonathan.proyectofinal.ui.MainCarer;
import com.jonathan.proyectofinal.ui.MainPatient;
import com.jonathan.proyectofinal.ui.PatientsList;
import com.jonathan.proyectofinal.ui.Registration_Carer;
import com.onesignal.OneSignal;

import java.util.Arrays;
import java.util.List;
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

    public void loginEmailPassword(final Context context, final String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //user = firebaseAuth.getCurrentUser();
                //emailPasswordLogin(context);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.auth_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void redirectByRole(final Context context, final FirebaseUser useruID) {
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(final String userId, String registrationId) {

                final ProgressDialog progressDialog = ProgressDialog.show(context, "Brainmher", "Ingresando");
                // INICIO
                if (useruID != null) {


                    final String uID = useruID.getUid();
                    DocumentReference docRefAd = db.collection(Constants.Adminds).document(uID);
                    docRefAd.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                admin = documentSnapshot.toObject(Admin.class);

                                if(admin.getPlayerId() == null){
                                    admin.setPlayerId(userId);
                                    db.collection(Constants.Adminds).document(uID).set(admin);
                                }

                                role = admin.getRole();
                                if (!role.isEmpty()) {
                                    Intent intent = new Intent(context, AdminHome.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    progressDialog.dismiss();
                                    context.startActivity(intent);
                                }

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("ERROR:", e.toString());
                        }
                    });

                    DocumentReference docRefHp = db.collection(Constants.HealthcareProfesional).document(uID);
                    docRefHp.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                hp = documentSnapshot.toObject(HealthcareProfessional.class);

                                if(hp.getPlayerId() == null){
                                    hp.setPlayerId(userId);
                                    db.collection(Constants.HealthcareProfesional).document(uID).set(hp);
                                }

                                role = hp.getRole();
                                if (!role.isEmpty()) {
                                    Intent intent = new Intent(context, PatientsList.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    progressDialog.dismiss();
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("ERROR:", e.toString());
                        }
                    });

                    DocumentReference docRefCr = db.collection(Constants.Carers).document(uID);
                    docRefCr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                carer = documentSnapshot.toObject(Carer.class);

                                if(carer.getPlayerId() == null){
                                    carer.setPlayerId(userId);
                                    db.collection(Constants.Carers).document(uID).set(carer);
                                }

                                role = carer.getRole();
                                if (!role.isEmpty()) {
                                    Intent intent = new Intent(context, MainCarer.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    progressDialog.dismiss();
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("ERROR:", e.toString());
                        }
                    });

                    DocumentReference docRefPt = db.collection(Constants.Patients).document(uID);
                    docRefPt.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                patient = documentSnapshot.toObject(Patient.class);

                                if(patient.getPlayerId() == null){
                                    patient.setPlayerId(userId);
                                    db.collection(Constants.Patients).document(uID).set(patient);
                                }

                                role = patient.getRole();
                                if (!role.isEmpty()) {
                                    Intent intent = new Intent(context, MainPatient.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    progressDialog.dismiss();
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("ERROR:", e.toString());
                        }
                    });
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
        final AuthCredential credential = GoogleAuthProvider.getCredential(result.getSignInAccount().getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    AuthResult iTask = task.getResult();
                    final FirebaseUser ures = iTask.getUser();
                    db.collection(Constants.Carers).document(ures.getUid()).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (!documentSnapshot.exists()) {
                                        Intent intent = new Intent(context, Registration_Carer.class);
                                        intent.putExtra("credencial", credential);
                                        context.startActivity(intent);
                                        //context.startActivity(new Intent(context,Registration_Carer.class));
                                    } else {
                                        redirectByRole(context, ures);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(context, context.getString(R.string.auth_fail), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void handleFacebookAccessToken(AccessToken token, final Context context) {
        Log.d("fb", "handleFacebookAccessToken:" + token);

        final AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            AuthResult iTask = task.getResult();
                            final FirebaseUser ures = iTask.getUser();
                            db.collection(Constants.Carers).document(ures.getUid()).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (!documentSnapshot.exists()) {
                                                Intent intent = new Intent(context, Registration_Carer.class);
                                                intent.putExtra("credencial", credential);
                                                context.startActivity(intent);
                                                //context.startActivity(new Intent(context,Registration_Carer.class));
                                            } else {
                                                redirectByRole(context, ures);
                                            }
                                        }
                                    });
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
}
