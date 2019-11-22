package com.jonathan.proyectofinal.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MotorExcercises;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MotorAdapter extends FirestoreRecyclerAdapter<MotorExcercises, MotorAdapter.ViewHolder> {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Context context;
    String uid;
    ProgressDialog progressDialog;
    MotorExcercisesAssignment motorExcercisesAssignment = new MotorExcercisesAssignment();

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MotorAdapter(@NonNull FirestoreRecyclerOptions<MotorExcercises> options, Context context, String uid) {
        super(options);
        this.context = context;
        this.uid = uid;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View layout;
        @BindView(R.id.cardMotor)
        MaterialCardView cardMotor;
        @BindView(R.id.iv_motor)
        ImageView iv_motor;
        @BindView(R.id.tv_motor_name)
        TextView tv_motor_name;
        @BindView(R.id.tv_motor_description)
        TextView tv_motor_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final MotorAdapter.ViewHolder holder, int position, @NonNull MotorExcercises model) {
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Glide.with(context).load(model.getUriGifExcercise()).fitCenter().into(holder.iv_motor);
        holder.tv_motor_name.setText(model.getNameExcercise());
        holder.tv_motor_description.setText(model.getDescriptionExcercise());

        final DocumentSnapshot excerciseDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String idDoc = getSnapshots().getSnapshot(holder.getAdapterPosition()).getId();
        final MaterialCardView cardMotor = holder.cardMotor;
        final MotorExcercises motorExcercises = excerciseDocument.toObject(MotorExcercises.class);

        List<String> listAssignments = new ArrayList<>();
        if (motorExcercises.getAssignments() != null){
            if (motorExcercises.getAssignments().size() != 0){
                listAssignments = motorExcercises.getAssignments();
                for (String item:
                        listAssignments) {
                    if (item == uid){
                        cardMotor.setChecked(true);
                    } else {
                        cardMotor.setChecked(false);
                    }
                }
            } else {
                cardMotor.setChecked(false);
            }
        }

        cardMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardMotor.isChecked()){
                    cardMotor.setChecked(false);
                } else {
                    cardMotor.toggle();
                }
            }
        });

        //Status change listener checked on the cardview
        cardMotor.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                final DocumentReference documentExcerciseReference = db.collection("MotorExcercises").document(idDoc);

                //If checked, the assignment is made
                if (isChecked) {
                    progressDialog = ProgressDialog.show(context,
                            "Brainmher","Realizando asignación");

                    String[] assignArray = {uid};
                    List<String> assigns = Arrays.asList(assignArray);
                    documentExcerciseReference.update("assignments", assigns).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Get the data from the exercise document
                            documentExcerciseReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()){
                                        DocumentSnapshot documentExcercise = task.getResult();
                                        if (documentExcercise.exists()){
                                            MotorExcercises motorExcercises = documentExcercise.toObject(MotorExcercises.class);
                                            int idExcercise = motorExcercises.getIdExcercise();
                                            String nameExcercise = motorExcercises.getNameExcercise();
                                            String descriptionExcercise = motorExcercises.getDescriptionExcercise();
                                            String longDescriptionExcercise = motorExcercises.getLongDescriptionExcercise();
                                            int timeExcercise = motorExcercises.getTimeExcercise();
                                            String uriGifExcercise = motorExcercises.getUriGifExcercise();

                                            //Set parameters to perform the assignment
                                            setAssignment(uid, idExcercise, nameExcercise, descriptionExcercise, longDescriptionExcercise, timeExcercise, uriGifExcercise);
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Gestión fallida", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Gestión fallida", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //If unchecked, the assignment is removed
                else {
                    progressDialog = ProgressDialog.show(context,
                            "Brainmher","Realizando desasignación.");
                    //Get the data from the assignment document
                    int idExcercise = motorExcercises.getIdExcercise();
                    db.collection("MotorExcercisesAssignments")
                            .whereEqualTo("uidPatient", uid)
                            .whereEqualTo("idExcercise", idExcercise)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()){
                                for (QueryDocumentSnapshot documentSnapshot:
                                     queryDocumentSnapshots) {
                                    String idDocAssignment = documentSnapshot.getId();

                                    //The patient's uId is deleted in the MotorExcercises document
                                    documentExcerciseReference.update("assignments", FieldValue.arrayRemove(uid));

                                    //The assignment document is deleted
                                    DocumentReference documentAssignmentReference = db.collection("MotorExcercisesAssignments").document(idDocAssignment);
                                    documentAssignmentReference.delete();
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Ejercicio desasignado.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressDialog.dismiss();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                            /*addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idDocAssignment = document.getId();

                                    //The patient's uId is deleted in the MotorExcercises document
                                    documentExcerciseReference.update("assignments", FieldValue.arrayRemove(uid));

                                    //The assignment document is deleted
                                    DocumentReference documentAssignmentReference = db.collection("MotorExcercisesAssignments").document(idDocAssignment);
                                    documentAssignmentReference.delete();
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Ejercicio desasignado.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    */
                }
            }
        });
    }

    //Method to set the variables to the well and create assignment
    private void setAssignment(String uid, int idExcercise, String nameExcercise, String descriptionExcercise, String longDescriptionExcercise, int timeExcercise, String uriGifExcercise) {
        MotorExcercisesAssignment motorExcercisesAssignment = new MotorExcercisesAssignment();

        motorExcercisesAssignment.setUidPatient(uid);
        motorExcercisesAssignment.setIdExcercise(idExcercise);
        motorExcercisesAssignment.setNameExcercise(nameExcercise);
        motorExcercisesAssignment.setDescriptionExcercise(descriptionExcercise);
        motorExcercisesAssignment.setLongDescriptionExcercise(longDescriptionExcercise);
        motorExcercisesAssignment.setTimeExcercise(timeExcercise);
        motorExcercisesAssignment.setUriGifExcercise(uriGifExcercise);
        motorExcercisesAssignment.setFinished("No");
        motorExcercisesAssignment.setRating(0);

        db.collection("MotorExcercisesAssignments").document().set(motorExcercisesAssignment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Ejercicio asignado.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(context, "Asignacion fallida.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @NonNull
    @Override
    public MotorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motor_card, parent, false);
        return new ViewHolder(view);
    }
}
