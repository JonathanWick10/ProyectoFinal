package com.jonathan.proyectofinal.fragments.carer;


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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
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

    private PhysicalExecise.PhysicalExeciseI physicalExeciseI;

    public ExerciseCarerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_exercise_carer, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }


    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PhysicalExerciseAdapter(entities(),physicalExeciseI));

     /*   String uid = user.getUid();
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReferenceMemorizame = db.collection(Constants.Memorizame);

        collectionReferenceMemorizame.document(patient.getPatientUID()).collection(categoria).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        memorizameList = new ArrayList<Memorizame>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            memorizameM = documentSnapshopt.toObject(Memorizame.class);
                            memorizameList.add(memorizameM);
                        }
                        adapter = new MemorizameFamilyGridAdapter(memorizameList,getActivity(),iSelectionMemorizame,iDeleteMemorizame);
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

      */

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
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise3);
        entity2.setTime(30000);
        lista.add(entity3);

        // dos
        PhysicalExerciseEntity entity4 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise4);
        entity2.setTime(30000);
        lista.add(entity4);

        // dos
        PhysicalExerciseEntity entity5 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise5);
        entity2.setTime(30000);
        lista.add(entity5);

        // dos
        PhysicalExerciseEntity entity6 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise6);
        entity2.setTime(30000);
        lista.add(entity6);

        // dos
        PhysicalExerciseEntity entity7 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise7);
        entity2.setTime(30000);
        lista.add(entity7);

        // dos
        PhysicalExerciseEntity entity8 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise8);
        entity2.setTime(30000);
        lista.add(entity8);

        // dos
        PhysicalExerciseEntity entity9 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise9);
        entity2.setTime(30000);
        lista.add(entity9);

        // dos
        PhysicalExerciseEntity entity10 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.exercise10);
        entity2.setTime(30000);
        lista.add(entity10);

        return  lista;
    }
}
