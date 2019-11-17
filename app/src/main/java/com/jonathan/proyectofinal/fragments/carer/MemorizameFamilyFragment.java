package com.jonathan.proyectofinal.fragments.carer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemorizameFamilyFragment extends Fragment {

    CardView addquestion;

    private IMainCarer mIMainCarer;

    public MemorizameFamilyFragment(){}


//region for fragment grid

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_add_image)
    TextView titleAddImage;
    @BindView(R.id.iv_question)
    ImageView ivQuestion;

    private MemorizameFamilyGridAdapter adapter;
    private MemorizameFamilyGridAdapter.ISelectionMemorizame iSelectionMemorizame;
    private MemorizameFamilyGridAdapter.IDeleteMemorizame iDeleteMemorizame;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Memorizame> memorizameList = new ArrayList<>();
    String userHPoCarer = "", uidGenerate;
    Memorizame memorizameM = new Memorizame();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    ProgressDialog progressDialog;
    Patient patient = new Patient();
    String categoria="";
    IMainCarer iMainCarer;
    //endregion


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame_family,container,false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle!=null){
            patient = (Patient) bundle.getSerializable("patient");
        }
//region for flags
        HealthProfessionalActivity healthProfessionalActivity = new HealthProfessionalActivity();
        healthProfessionalActivity=(HealthProfessionalActivity)getActivity();
        int h=healthProfessionalActivity.flagActivity;

        switch (h){
            case 1:
                categoria="Family";
                ivQuestion.setImageResource(R.drawable.img_family_question);
                titleAddImage.setText("Agregar pregunta de familia");
                break;
            case 2:
                categoria="Pets";
                ivQuestion.setImageResource(R.drawable.img_pets_question);
                titleAddImage.setText("Agregar pregunta de mascotas");
                break;
            case 3:
                categoria="Home";
                ivQuestion.setImageResource(R.drawable.img_home_question);
                titleAddImage.setText("Agregar pregunta de hogar");
                break;
            case 4:
                categoria="Places";
                ivQuestion.setImageResource(R.drawable.img_places_question);
                titleAddImage.setText("Agregar pregunta de lugar");
                break;

        }
        //endregion


        addquestion = view.findViewById(R.id.cv_add_image);
        addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.family_questions_img));
            }
        });

//region for fragment grid
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());




        //initAdapter();
        logicEventSelecItem();

        eventDeleteItem();
        initRecyclerView();

      //endregion
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //reference();
        adapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void eventDeleteItem() {
        iDeleteMemorizame = new MemorizameFamilyGridAdapter.IDeleteMemorizame() {
            @Override
            public void clickdelete(final Memorizame memorizame) {
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

//region for flags
                        HealthProfessionalActivity healthProfessionalActivity = new HealthProfessionalActivity();

                        healthProfessionalActivity=(HealthProfessionalActivity)getActivity();
                        int h=healthProfessionalActivity.flagActivity;
                        Toast.makeText(getActivity(), "Memorizame"+h, Toast.LENGTH_SHORT).show();
                        String categoria="";
                        switch (h){
                            case 1:
                                categoria="Family";
                                break;
                            case 2:
                                categoria="Pets";
                                break;
                            case 3:
                                categoria="Home";
                                break;
                            case 4:
                                categoria="Places";
                                break;

                        }
                        final String categoria2=categoria;
                        //endregion

                        Button btn2 = (Button) dialogView.findViewById(R.id.btn2);
                        btn2.setText(R.string.yes);
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                        "Brainmher", "Eliminando registro en línea");
                                                    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                                    StorageReference deleteImage = storageReference.child(categoria2+"/" + memorizame.getUuidGenerated() + ".jpg");
                                                    deleteImage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            db.collection(Constants.Memorizame).document(patient.getPatientUID()).collection(categoria2)
                                                                    .document(memorizame.getUuidGenerated()).delete()
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            progressDialog.dismiss();
                                                                            db.collection(Constants.Memorizame).document(patient.getPatientUID()).delete();
                                                                            Toast.makeText(getActivity(), "memorizame eliminado", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {

                                                                        }
                                                                    });

                                                        }
                                                    });

                                alertDialog.dismiss();
                            }
                        });
                        TextView tvInformation = dialogView.findViewById(R.id.textView);
                        tvInformation.setText(getString(R.string.message_delete_memorizame));
                        alertDialog.show();


                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {

                    builder.setTitle(getString(R.string.alert));
                    builder.setMessage(getString(R.string.message_delete));
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                    "Brainmher", "Eliminando registro en línea");


                        }
                    });
                    builder.show();
                }
            }
        };

    }
    //endregion


    private void logicEventSelecItem() {
        iSelectionMemorizame = new MemorizameFamilyGridAdapter.ISelectionMemorizame() {
            @Override
            public void clickItem(Memorizame memorizame) {
                iMainCarer.inflateFragment("memorizamee");
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainCarer = (IMainCarer) getActivity();
        mIMainCarer = (IMainCarer) getActivity();
    }


//region region for fragment grid

    private void initRecyclerView() {

        String uid = user.getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Memorizame).document(patient.getPatientUID()).collection(categoria);
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<Memorizame> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Memorizame>()
                .setQuery(query, Memorizame.class).build();

        //Passing parameters to the adapter
        adapter = new MemorizameFamilyGridAdapter(firestoreRecyclerOptions, getActivity(),iSelectionMemorizame,iDeleteMemorizame);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
//endregion
}
