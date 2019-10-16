package com.jonathan.proyectofinal.database;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import io.opencensus.tags.Tag;

public class AdminManager {

    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.HealthcareProfesional);
    private boolean flag = false;
    List<Admin> adminList = new ArrayList<>();
    Admin admin = new Admin();
    String rol;
    //endregion

    public String admintByUID(String uID) {
        //DocumentReference docRef = db.collection("Adminds").document(uID);
        String doc = "Adminds/"+uID;
        DocumentReference docRef = db.document(doc);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    admin = documentSnapshot.toObject(Admin.class);
                    rol = admin.getRole();
                    //Log.d("Hola", "Rol: "+rol);
                    if (rol.isEmpty()){
                        Log.d("Nada", "Nothing: ");
                    }else {
                        Log.d("Rol", "Role: "+rol);
                    }
                } else {
                    Log.d("No existe", "No exist");
                }
                //Log.d("Rol", "Role: "+rol);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ERROR:", e.toString());
            }
        });

        /*
        DocumentReference docRef = db.collection("Adminds").document(uID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Log.d("Hola", "DocumentSnapshot data: " + documentSnapshot.getData());
                        admin = documentSnapshot.toObject(Admin.class);
                        rol = admin.getRole();
                    } else {
                        Log.d("Hola", "No such document");
                    }
                } else {
                    Log.d("Hola", "get failed with ", task.getException());
                }
            }
        });
        */
        return rol;
    }

    /*
    public String admintByUID(String uID) {
        final DocumentReference docRef = db.collection("Adminds").document(uID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                admin = documentSnapshot.toObject(Admin.class);
                rol = admin.getRole();
                //Log.d("Rol", "Role: "+rol);
            }
        });
        return rol;
    }
    */

    /*
    //region Read Admin by UID
    public Admin admintByUID(String uID) {
        final DocumentReference docRef = db.collection("Adminds").document(uID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                admin = documentSnapshot.toObject(Admin.class);
                rol = admin.getRole();
                Log.d("Rol", "Role: "+rol);
            }
        });
        return admin;
    }
    */
}
