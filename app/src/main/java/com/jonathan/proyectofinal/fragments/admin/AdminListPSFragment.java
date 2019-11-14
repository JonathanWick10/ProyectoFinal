package com.jonathan.proyectofinal.fragments.admin;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminListPSFragment extends Fragment {

    private View view;
    @BindView(R.id.txt_no_hp)
    TextView noHp;
    private RecyclerView recyclerView ;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;
    private List<HealthcareProfessional> healthcareProfessionalList = new ArrayList<>();
    HealthcareProfessional hp = new HealthcareProfessional();
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uIdAdmind;
    ProgressDialog progressDialog;
    FirebaseFirestore db;
    Admin admin = new Admin();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        ButterKnife.bind(this, view);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        uIdAdmind = firebaseUser.getUid();
        eventDelete();
        reference();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reference();
    }

    private void eventDelete() {
        adapterI = new AdminListPSAdapter.AdminListPSAdapterI() {
            @Override
            public void btnEliminar( final HealthcareProfessional pojo) {
                //region dialog delete
                final AlertDialog alertDialog;

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

                String namePatient= pojo.getFirstName()+" "+ pojo.getLastName();

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
                                        "Brainmher","Realizando registro en línea");
                                firebaseAuth.signInWithEmailAndPassword(pojo.getEmail(),pojo.getPassword())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    AuthResult itask = task.getResult();
                                                    FirebaseUser ures = itask.getUser();
                                                    db.collection(Constants.HealthcareProfesional).document(pojo.getHpUID())
                                                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(getActivity(), "usuario eliminado", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.d("Message: ",e.toString());
                                                        }
                                                    });
                                                    ures.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getActivity(), "se elimino", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Message: ",e.toString());
                                    }
                                });

                                db.collection(Constants.Adminds).document(uIdAdmind).get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()){
                                                    admin = documentSnapshot.toObject(Admin.class);
                                                    firebaseAuth.signInWithEmailAndPassword(admin.getEmail(),admin.getPassword())
                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    reference();
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
                        tvInformation.setText(getString(R.string.message_delete, namePatient));
                        alertDialog.show();


                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    builder.setTitle(getString(R.string.alert));
                    builder.setMessage(getString(R.string.message_delete,namePatient));
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.setMessage("Eliminando registro en línea");
                            progressDialog.show();
                            firebaseAuth.signInWithEmailAndPassword(pojo.getEmail(),pojo.getPassword())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                AuthResult itask = task.getResult();
                                                FirebaseUser ures = itask.getUser();
                                                db.collection(Constants.HealthcareProfesional).document(pojo.getHpUID())
                                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), "usuario eliminado", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("Message: ",e.toString());
                                                    }
                                                });
                                                ures.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(getActivity(), "se elimino", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Message: ",e.toString());
                                }
                            });

                            db.collection(Constants.Adminds).document(uIdAdmind).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (documentSnapshot.exists()){
                                                admin = documentSnapshot.toObject(Admin.class);
                                                firebaseAuth.signInWithEmailAndPassword(admin.getEmail(),admin.getPassword())
                                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                reference();
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
                //endregion
            }
        };
    }


    private void reference() {
        recyclerView = view.findViewById(R.id.admin_rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.HealthcareProfesional).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        healthcareProfessionalList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot:
                             queryDocumentSnapshots) {
                            hp = documentSnapshot.toObject(HealthcareProfessional.class);
                            healthcareProfessionalList.add(hp);
                        }
                        recyclerView.setAdapter(new AdminListPSAdapter(getActivity(),healthcareProfessionalList,adapterI));
                        recyclerView.setHasFixedSize(true);
                        if (healthcareProfessionalList.size()!=0){
                            recyclerView.setVisibility(View.VISIBLE);
                            noHp.setVisibility(View.INVISIBLE);
                        }else {
                            recyclerView.setVisibility(View.INVISIBLE);
                            noHp.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

   public interface AdminListPSFragmentI{
        void onclickAddPs();
    }

}
