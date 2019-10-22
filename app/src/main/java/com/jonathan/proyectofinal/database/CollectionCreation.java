package com.jonathan.proyectofinal.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionCreation {

    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<String> listMnsj = new ArrayList<>();

    public List<String> createCollections(final Context context){

        // Create a new object with a key and value as an example
        Map<String, Object> example = new HashMap<>();
        example.put("examplekey", "examplevalue");

        //Collection section
        // (#1 Adminds, #2 Healthcare profesional, #3 Carers, #4 Patients,
        // #5 Terapies, #6 Notifications)

        //region Create collection Adminds
        db.collection(Constants.Adminds).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_administrators));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        //region Create collection Healthcare Profesional
        db.collection(Constants.HealthcareProfesional).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_healthcare_profeional));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        //region Create collection Carers
        db.collection(Constants.Carers).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_carers));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        //region Create collection Pacients
        db.collection(Constants.Patients).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_patients));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        //region Create collection Terapies
        db.collection(Constants.Terapies).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_terapies));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        //region Create collection Notifications
        db.collection(Constants.Notifications).document(Constants.Example).set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listMnsj.add(context.getResources().getString(R.string.collection_notications));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                        listMnsj.add(context.getResources().getString(R.string.creation_failed));
                    }
                });
        //endregion

        return listMnsj;
    }
}
