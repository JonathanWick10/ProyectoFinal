package com.jonathan.proyectofinal.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.HashMap;
import java.util.Map;

public class CarerManager {

    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.Carers);
    private boolean flag = false;
    //endregion

    //region Create Carer
    public boolean createPatient(Carer carer){

        // Create a new object with a key and value as an example
        Map<String, Object> example = new HashMap<>();
        example.put("examplekey", "examplevalue");

        collectionReferencePatients.document(carer.getIdentification().toString()).set(carer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        flag = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        flag = false;
                        Log.d("Message", e.toString());
                    }
                });

        return flag;
    }
    //endregion

    //region Read Patients for Carer
    //endregion

    //region Read Patients for Healthcarer profesional
    //endregion

    //region Update Patient by ID
    public boolean updatePatient(Carer carer){

        collectionReferencePatients.document(carer.getIdentification().toString()).set(carer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        flag = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        flag = false;
                        Log.d("Message", e.toString());
                    }
                });

        return flag;
    }
    //endregion

    //region Delete Patient by ID
    public boolean deletePetient(Carer carer){

        collectionReferencePatients.document(carer.getIdentification().toString()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        flag = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                        flag = false;
                    }
                });

        return flag;
    }
    //endregion
}
