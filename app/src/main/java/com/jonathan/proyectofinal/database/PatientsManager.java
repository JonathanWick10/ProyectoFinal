package com.jonathan.proyectofinal.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientsManager {

    //region Variables
    // Create the instance to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencePatients = db.collection(Constants.Patients);
    private boolean flag = true;
    List<Patient> patientList = new ArrayList<>();
    Patient patientM = new Patient();
    //endregion

    //region Create Patient
    public boolean createPatient(Patient patient) {

        // Create a new object with a key and value as an example
        Map<String, Object> example = new HashMap<>();
        example.put("examplekey", "examplevalue");

        collectionReferencePatients.document(patient.getIdentification().toString()).set(patient)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        flag = true;
                        Log.d("Message",String.valueOf(flag));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        flag = false;
                        Log.d("Message", e.toString());
                    }
                });

        collectionReferencePatients.document(patient.getIdentification().toString())
                .collection(Constants.Memorizame)
                .document("Example").set(example)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });

        return flag;
    }
    //endregion

    //region Read Patients for Carer
    public List<Patient> listForCarer(Carer carer) {
        collectionReferencePatients
                .whereEqualTo("assigns.id", carer.getIdentification().toString())
                .orderBy("firstName")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        patientList = new ArrayList<Patient>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            patientM = documentSnapshopt.toObject(Patient.class);
                            patientList.add(patientM);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });
        return patientList;
    }
    //endregion

    //region Read Patients for Healthcarer profesional
    public List<Patient> listForHP(HealthcareProfessional healthcareProfessional) {
        collectionReferencePatients
                .whereEqualTo("assigns.id", healthcareProfessional.getIdentification())
                .orderBy("firstName")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        patientList = new ArrayList<Patient>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            patientM = documentSnapshopt.toObject(Patient.class);
                            patientList.add(patientM);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });
        return patientList;
    }
    //endregion

    //region Read Patient by identification
    public Patient patientByID(String id) {

        collectionReferencePatients.whereEqualTo("identification", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        patientList = new ArrayList<Patient>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            patientM = documentSnapshopt.toObject(Patient.class);
                        }
                    }
                });
        return patientM;
    }

    //endregion

    //region Update Patient by ID
    public boolean updatePatient(Patient patient) {

        collectionReferencePatients.document(patient.getIdentification().toString()).set(patient)
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
    public boolean deletePetient(Patient patient) {

        collectionReferencePatients.document(patient.getIdentification().toString())
                .collection(Constants.Memorizame).document("Example").delete();

        collectionReferencePatients.document(patient.getIdentification().toString()).delete()
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

    //region Assign Patient
    public boolean assignPatient(Patient patient, String id) {
        patientM = patientByID(patient.getIdentification().toString());
        Map<String, Object> ids = patientM.getAssigns();
        ids.put("id", id);
        flag = createPatient(patientM);
        return flag;
    }
    //endregion
}
