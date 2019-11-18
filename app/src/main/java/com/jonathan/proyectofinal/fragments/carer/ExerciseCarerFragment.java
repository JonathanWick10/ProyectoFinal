package com.jonathan.proyectofinal.fragments.carer;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.StretchingAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.data.StretchingExercise;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.adapters.PhysicalExerciseAdapter;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseCarerFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View view;

    private StretchingAdapter adapter;
    private StretchingAdapter.ISelectionStretching iSelectionStretching;
    private StretchingAdapter.IDeleteStretching iDeleteStretching;

    private IMainCarer mIMainCarer;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<StretchingExercise> stretchingList = new ArrayList<>();
    String userHPoCarer = "";
    StretchingExercise stretchingM = new StretchingExercise();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    ProgressDialog progressDialog;
    Patient patient = new Patient();
    String categoria="Stretching";
    IMainCarer iMainCarer;

    private PhysicalExecise.PhysicalExeciseI physicalExeciseI;

    public ExerciseCarerFragment(PhysicalExecise.PhysicalExeciseI physicalExeciseI ) {
        this.physicalExeciseI = physicalExeciseI;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_exercise_carer, container, false);
        ButterKnife.bind(this, view);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());

       // initRecyclerView();

        initRecyclerView();
        return view;
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PhysicalExerciseAdapter(entities(),physicalExeciseI));


        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReferenceStretching = db.collection(Constants.Stretching);

        collectionReferenceStretching.document(carer.getCarerUId()).collection(categoria).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        stretchingList = new ArrayList<StretchingExercise>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            stretchingM = documentSnapshopt.toObject(StretchingExercise.class);
                            stretchingList.add(stretchingM);
                        }
                        adapter = new StretchingAdapter(stretchingList,getActivity(),iSelectionStretching,iDeleteStretching);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainCarer = (IMainCarer) getActivity();
        mIMainCarer = (IMainCarer) getActivity();
    }






    private List<PhysicalExerciseEntity> entities(){

        List<PhysicalExerciseEntity> lista = new ArrayList<>();
        PhysicalExerciseEntity entity1 = new PhysicalExerciseEntity();
        entity1.setNameExercise(getString(R.string.physical_nameExercise_resistence));
        entity1.setDescripcion(getString(R.string.description_resistance_exercice));
        entity1.setImage(R.drawable.exercise1);
        entity1.setTime(300000);
        lista.add(entity1);

        // dos
        PhysicalExerciseEntity entity2 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise2);
        entity2.setTime(30000);
        lista.add(entity2);

        // dos
        PhysicalExerciseEntity entity3 = new PhysicalExerciseEntity();
        entity3.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity3.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity3.setImage(R.drawable.exercise3);
        entity3.setTime(30000);
        lista.add(entity3);

        // dos
        PhysicalExerciseEntity entity4 = new PhysicalExerciseEntity();
        entity4.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity4.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity4.setImage(R.drawable.exercise4);
        entity4.setTime(30000);
        lista.add(entity4);

        // dos
        PhysicalExerciseEntity entity5 = new PhysicalExerciseEntity();
        entity5.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity5.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity5.setImage(R.drawable.exercise5);
        entity5.setTime(30000);
        lista.add(entity5);

        // dos
        PhysicalExerciseEntity entity6 = new PhysicalExerciseEntity();
        entity6.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity6.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity6.setImage(R.drawable.exercise6);
        entity6.setTime(30000);
        lista.add(entity6);

        // dos
        PhysicalExerciseEntity entity7 = new PhysicalExerciseEntity();
        entity7.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity7.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity7.setImage(R.drawable.exercise7);
        entity7.setTime(30000);
        lista.add(entity7);

        // dos
        PhysicalExerciseEntity entity8 = new PhysicalExerciseEntity();
        entity8.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity8.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity8.setImage(R.drawable.exercise8);
        entity8.setTime(30000);
        lista.add(entity8);

        // dos
        PhysicalExerciseEntity entity9 = new PhysicalExerciseEntity();
        entity9.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity9.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity9.setImage(R.drawable.exercise9);
        entity9.setTime(30000);
        lista.add(entity9);

        // dos
        PhysicalExerciseEntity entity10 = new PhysicalExerciseEntity();
        entity10.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity10.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity10.setImage(R.drawable.exercise10);
        entity10.setTime(30000);
        lista.add(entity10);

        return  lista;
    }
}
