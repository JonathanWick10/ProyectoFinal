package com.jonathan.proyectofinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.data.CarerEvent;
import com.jonathan.proyectofinal.fragments.carer.DiaryFragment;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

public class CarerEventsManager {

    private CarerEvent carerEvent = new CarerEvent();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String uidCarer = firebaseUser.getUid();
    private List<CarerEvent> carerEventList;
    private DiaryFragment diaryFragment= new DiaryFragment();

    public List<CarerEvent> listEvents(final ListView carerEventsList, final String date){

        CollectionReference collectionReferenceCarerEvents = db.collection(Constants.Carers).document(uidCarer)
                .collection("Events");

        collectionReferenceCarerEvents
                .whereEqualTo("eventDate", date)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        carerEventList = new ArrayList<CarerEvent>();
                        ArrayList eventsId = new ArrayList<String>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            String idEvent = documentSnapshopt.getId();
                            carerEvent.setEventId(idEvent);
                            carerEvent = documentSnapshopt.toObject(CarerEvent.class);
                            carerEventList.add(carerEvent);
                            eventsId.add(idEvent);
                            Log.d("ID Evento:", idEvent);
                        }

                        diaryFragment.sortEventList(carerEventList, carerEventsList, eventsId);
                        Log.d("Message", String.valueOf(carerEventList));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });

        return carerEventList;
    }


}
