package com.jonathan.proyectofinal.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.ui.Login;

import java.util.concurrent.Executor;

public class LoginManager {

    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    public void loginEmailPassword (final Context context, String email, String password){


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            context.startActivity(new Intent(context, AdminHome.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            context.startActivity(new Intent(context, Login.class));

                        }

                        // ...
                    }
                });
    }

}
