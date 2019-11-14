package com.jonathan.proyectofinal.fragments.hp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.PatientsManager;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;
import com.jonathan.proyectofinal.ui.PatientsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientsListFragment extends Fragment {

    //region Variables
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_no_patient)
    TextView noPatient;
    LinearLayoutManager linearLayoutManager;
    private PatientsAdapter adapter;
    private PatientsAdapter.ISelectionPatient iSelectionPatient;
    private PatientsAdapter.IDeletePatient iDeletePatient;
    private View view;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Patient> patientList = new ArrayList<>();
    Patient patientM = new Patient();
    String userHPoCarer = "";
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    //endregion

    public PatientsListFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patients, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();
        reference();

        return view;
    }

    private void reference() {
        ButterKnife.bind(this, view);
        eventSelectedItem();
        eventDeleteItem();
        initRecyclerView();
    }

    //region Events Onclick
    private void eventSelectedItem() {
        iSelectionPatient = new PatientsAdapter.ISelectionPatient() {
            @Override
            public void clickItem(Patient patient) {
                Intent goPatient = new Intent(getActivity(), HealthProfessionalActivity.class);
                Bundle patientSend = new Bundle();
                patientSend.putSerializable("patient", patient);
                goPatient.putExtras(patientSend);
                startActivity(goPatient);
            }
        };
    }

    private void eventDeleteItem() {
        iDeletePatient = new PatientsAdapter.IDeletePatient() {
            @Override
            public void clickdelete(final Patient patient) {
                final AlertDialog alertDialog;

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // only for Lollipop and newer versions
                    try {
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                        builder.setView(dialogView);
                        alertDialog = builder.create();

                        Button btn1 = (Button) dialogView.findViewById(R.id.btn1);
                        btn1.setText(R.string.no);
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                alertDialog.dismiss();
                            }
                        });
                        Button btn2 = (Button) dialogView.findViewById(R.id.btn2);
                        btn2.setText(R.string.yes);
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                        "Brainmher", "Eliminando registro en línea");

                                firebaseAuth.signInWithEmailAndPassword(patient.getEmail(), patient.getPassword())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    AuthResult itask = task.getResult();
                                                    FirebaseUser ures = itask.getUser();
                                                    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                                    StorageReference deleteImage = storageReference.child("Users/Patients/" + patient.getPatientUID() + ".jpg");
                                                    deleteImage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                        }
                                                    });
                                                    db.collection(Constants.Patients).document(patient.getPatientUID()).delete()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    Toast.makeText(getActivity(), "usuario eliminado", Toast.LENGTH_SHORT).show();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {

                                                                }
                                                            });
                                                    ures.delete()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    Toast.makeText(getActivity(), "se elimino", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            }
                                        });

                                db.collection(Constants.HealthcareProfesional).document(userHPoCarer).get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    hp = documentSnapshot.toObject(HealthcareProfessional.class);
                                                    firebaseAuth.signInWithEmailAndPassword(hp.getEmail(), hp.getPassword())
                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    initRecyclerView();
                                                                    progressDialog.dismiss();
                                                                }
                                                            });
                                                }
                                            }
                                        });

                                db.collection(Constants.Carers).document(userHPoCarer).get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    carer = documentSnapshot.toObject(Carer.class);
                                                    firebaseAuth.signInWithEmailAndPassword(carer.getEmail(), carer.getPassword())
                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    initRecyclerView();
                                                                    progressDialog.dismiss();
                                                                }
                                                            });
                                                }
                                            }
                                        });

                                alertDialog.dismiss();
                            }
                        });
                        TextView tvInformation = dialogView.findViewById(R.id.textView);
                        String namePatient= patient.getFirstName()+" "+ patient.getLastName();
                        tvInformation.setText(getString(R.string.message_delete, namePatient));
                        alertDialog.show();


                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {

                    builder.setTitle(getString(R.string.alert));
                    builder.setMessage(getString(R.string.message_delete, patient.getFirstName()));
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                    "Brainmher", "Eliminando registro en línea");

                            firebaseAuth.signInWithEmailAndPassword(patient.getEmail(), patient.getPassword())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                AuthResult itask = task.getResult();
                                                FirebaseUser ures = itask.getUser();
                                                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                                StorageReference deleteImage = storageReference.child("Users/Patients/" + patient.getPatientUID() + ".jpg");
                                                deleteImage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                });
                                                db.collection(Constants.Patients).document(patient.getPatientUID()).delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(getActivity(), "usuario eliminado", Toast.LENGTH_SHORT).show();
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });
                                                ures.delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                Toast.makeText(getActivity(), "se elimino", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }
                                    });

                            db.collection(Constants.HealthcareProfesional).document(userHPoCarer).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (documentSnapshot.exists()) {
                                                hp = documentSnapshot.toObject(HealthcareProfessional.class);
                                                firebaseAuth.signInWithEmailAndPassword(hp.getEmail(), hp.getPassword())
                                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                initRecyclerView();
                                                                progressDialog.dismiss();
                                                            }
                                                        });
                                            }
                                        }
                                    });

                            db.collection(Constants.Carers).document(userHPoCarer).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (documentSnapshot.exists()) {
                                                carer = documentSnapshot.toObject(Carer.class);
                                                firebaseAuth.signInWithEmailAndPassword(carer.getEmail(), carer.getPassword())
                                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                initRecyclerView();
                                                                progressDialog.dismiss();
                                                            }
                                                        });
                                            }
                                        }
                                    });
                        }
                    });
                    builder.show();
                }
            }
            }

            ;

    }
    //endregion

    private void initRecyclerView() {
        //------------------------------------------
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                "Brainmher","Consultando registros en línea");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReferencePatients = db.collection(Constants.Patients);
        collectionReferencePatients
                .whereArrayContains("assigns", uid)
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
                        adapter = new PatientsAdapter(patientList, getActivity(), iSelectionPatient, iDeletePatient);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        if (patientList.size() != 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            noPatient.setVisibility(View.INVISIBLE);
                        } else {
                            recyclerView.setVisibility(View.INVISIBLE);
                            noPatient.setVisibility(View.VISIBLE);
                        }
                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });


    }
}
