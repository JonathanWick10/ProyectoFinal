package com.jonathan.proyectofinal.database;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class LoginManager {

    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    public void LoginEmailPassword (String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Log.d("Login", "¡CORRECTO!");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Login", "¡ERROR!");

                        }
                    }
                });
    }

}
