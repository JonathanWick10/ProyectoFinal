package com.jonathan.proyectofinal.database;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

public class HPManager {
    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.HealthcareProfesional);
    private boolean flag = false;
    List<HealthcareProfessional> healthcareProfessionalList = new ArrayList<>();
    HealthcareProfessional healthcareProfessional = new HealthcareProfessional();
    //endregion

    //region Read HealthcareProfessional by UID
    public HealthcareProfessional hpByUID(String uID) {

        collectionReferencePatients.whereEqualTo("hpUID", uID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        healthcareProfessionalList = new ArrayList<HealthcareProfessional>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            healthcareProfessional = documentSnapshopt.toObject(HealthcareProfessional.class);
                        }
                    }
                });
        return healthcareProfessional;
    }
    //endregion

    //region CreateHP
    public boolean createHP(HealthcareProfessional hp){
        collectionReferencePatients.document(hp.getEmail()).set(hp)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                flag = true;
            }
        });
        return flag;
    }
    //endregion
}
