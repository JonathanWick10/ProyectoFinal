package com.jonathan.proyectofinal.database;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jonathan.proyectofinal.data.Patient;

import java.io.File;

public class ImageManager {
    //Get instance and reference of Storage
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public void uploadImageToStorage(Uri uri, Patient patient){

        StorageReference albumRef = storageReference.child(patient.getUserName()+"_"+patient.getIdentification());

        albumRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message: ", e.toString());
                    }
                });
    }
}
