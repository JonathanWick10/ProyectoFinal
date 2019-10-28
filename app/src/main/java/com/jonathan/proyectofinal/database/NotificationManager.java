package com.jonathan.proyectofinal.database;

import android.app.Notification;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.NotificationData;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationManager {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferenceNotification = db.collection(Constants.Notifications);
    private boolean flag = false;
    List<NotificationData> notificationList = new ArrayList<>();
    NotificationData notificationData= new NotificationData();

    public boolean createNotification(NotificationData notificationData) {

        // Create a new object with a key and value as an example
        Map<String, Object> example = new HashMap<>();
        example.put("noti", "exampleNotification");

        collectionReferenceNotification.document(notificationData.getIdNotification()).set(notificationData)
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
}
