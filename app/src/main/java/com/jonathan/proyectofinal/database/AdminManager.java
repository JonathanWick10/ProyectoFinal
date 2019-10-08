package com.jonathan.proyectofinal.database;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {

    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.HealthcareProfesional);
    private boolean flag = false;
    List<Admin> adminList = new ArrayList<>();
    Admin admin = new Admin();
    //endregion

    //region Read Admin by UID
    public Admin admintByUID(String uID) {

        collectionReferencePatients.whereEqualTo("hpUID", uID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        adminList = new ArrayList<Admin>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            admin = documentSnapshopt.toObject(Admin.class);
                        }
                    }
                });
        return admin;
    }
    //endregion
}
