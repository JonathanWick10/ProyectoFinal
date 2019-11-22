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
import com.google.android.material.button.MaterialButton;
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
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.data.CognitivesExcercises;
import com.jonathan.proyectofinal.data.MotorExcercises;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CognitivesAdapter extends FirestoreRecyclerAdapter<CognitivesExcercises, CognitivesAdapter.ViewHolder> {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Context context;
    String uid;
    //ProgressDialog progressDialog;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CognitivesAdapter(@NonNull FirestoreRecyclerOptions<CognitivesExcercises> options, Context context, String uid) {
        super(options);
        this.context = context;
        this.uid = uid;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View layout;
        @BindView(R.id.cardCognitive)
        MaterialCardView cardCognitive;
        @BindView(R.id.iv_cognitive)
        ImageView iv_cognitive;
        @BindView(R.id.tv_cognitive_name)
        TextView tv_cognitive_name;
        @BindView(R.id.tv_cognitive_description)
        TextView tv_cognitive_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final CognitivesAdapter.ViewHolder holder, int position, @NonNull CognitivesExcercises model) {
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Glide.with(context).load(model.getUriImageExcercise()).fitCenter().into(holder.iv_cognitive);
        holder.tv_cognitive_name.setText(model.getNameExcercise());
        holder.tv_cognitive_description.setText(model.getDescriptionExcercise());

        final DocumentSnapshot excerciseDoument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String idDoc = getSnapshots().getSnapshot(holder.getAdapterPosition()).getId();
        final MaterialCardView cardCognitive = holder.cardCognitive;
        final CognitivesExcercises cognitivesExcercises = excerciseDoument.toObject(CognitivesExcercises.class);

        List<String> listAssignments = new ArrayList<>();
        if (cognitivesExcercises.getAssignments() != null){
            if (cognitivesExcercises.getAssignments().size() != 0){
                listAssignments = cognitivesExcercises.getAssignments();
                for (String item:
                        listAssignments) {
                    if (item == uid){
                        cardCognitive.setChecked(true);
                    } else {
                        cardCognitive.setChecked(false);
                    }
                }
            } else {
                cardCognitive.setChecked(false);
            }
        }

        cardCognitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardCognitive.toggle();
            }
        });

        //Status change listener checked on the cardview
        cardCognitive.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                final DocumentReference documentExcerciseReference = db.collection(Constants.CognitiveExcercises).document(idDoc);

                if (isChecked){
                    /*
                    progressDialog = ProgressDialog.show(context,
                            "Brainmher","Realizando asignación");
                     */

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
                                            CognitivesExcercises cognitivesExcercises = documentExcercise.toObject(CognitivesExcercises.class);
                                            int idExcercise = cognitivesExcercises.getIdExcercise();
                                            String nameExcercise = cognitivesExcercises.getNameExcercise();
                                            String descriptionExcercise = cognitivesExcercises.getDescriptionExcercise();
                                            String uriImgExcercise = cognitivesExcercises.getUriImageExcercise();

                                            //Set parameters to perform the assignment
                                            setAssignment(uid, idExcercise, nameExcercise, descriptionExcercise, uriImgExcercise);
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //progressDialog.dismiss();
                                    Toast.makeText(context, R.string.failed_management, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(context, R.string.failed_management, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //If unchecked, the assignment is removed
                else {
                    /*
                    progressDialog = ProgressDialog.show(context,
                            "Brainmher","Realizando desasignación.");
                     */

                    //Get the data from the assignment document
                    int idExcercise = cognitivesExcercises.getIdExcercise();
                    db.collection("CognitiveExcercisesAssignments")
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
                                    DocumentReference documentAssignmentReference = db.collection(Constants.CognitiveExcercisesAssignments).document(idDocAssignment);
                                    documentAssignmentReference.delete();
                                    //progressDialog.dismiss();
                                    Toast.makeText(context, R.string.unassigned_exercise, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, R.string.error_to_deallocate, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });

    }

    private void setAssignment(String uid, int idExcercise, String nameExcercise, String descriptionExcercise, String uriImgExcercise) {
        CognitiveExcercisesAssignment cognitiveExcercisesAssignment = new CognitiveExcercisesAssignment();

        cognitiveExcercisesAssignment.setUidPatient(uid);
        cognitiveExcercisesAssignment.setIdExcercise(idExcercise);
        cognitiveExcercisesAssignment.setNameExcercise(nameExcercise);
        cognitiveExcercisesAssignment.setDescriptionExcercise(descriptionExcercise);
        cognitiveExcercisesAssignment.setUriImageExcercise(uriImgExcercise);
        cognitiveExcercisesAssignment.setLevel(0);
        cognitiveExcercisesAssignment.setBestScore(0);
        cognitiveExcercisesAssignment.setStatement("Sin terminar");
        cognitiveExcercisesAssignment.setRating(0);

        db.collection(Constants.CognitiveExcercisesAssignments).document("Cognitive").set(cognitiveExcercisesAssignment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //progressDialog.dismiss();
                        Toast.makeText(context, R.string.assigned_excercise, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //progressDialog.dismiss();
                Toast.makeText(context, R.string.no_assigned_excercise, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public CognitivesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitives_card, parent, false);
        return new ViewHolder(view);
    }

}
