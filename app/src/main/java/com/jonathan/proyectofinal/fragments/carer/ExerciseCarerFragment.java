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
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseCarerFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public ExerciseCarerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_exercise_carer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

}}
