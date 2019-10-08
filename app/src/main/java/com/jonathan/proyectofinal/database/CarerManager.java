package com.jonathan.proyectofinal.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarerManager {

    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.Carers);
    private boolean flag = false;
    List<Carer> carersList = new ArrayList<>();
    Carer carer = new Carer();
    //endregion

    //region Create Carer
    public boolean createCarer(Carer carer) {

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
                })
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            flag = true;
                        }
                    }
                });

        return flag;
    }
    //endregion

    //region Read Carer for Carer
    //endregion

    //region Read Carer for Healthcarer profesional
    //endregion

    //region Read Carer by UID
    public Carer carertByUID(String uID) {

        collectionReferencePatients.whereEqualTo("carerUId", uID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        carersList = new ArrayList<Carer>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            carer = documentSnapshopt.toObject(Carer.class);
                        }
                    }
                });
        return carer;
    }
    //endregion

    //region Update Carer by ID
    public boolean updateCarer(Carer carer) {

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

    //region Delete Carer by ID
    public boolean deleteCarer(Carer carer) {

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
