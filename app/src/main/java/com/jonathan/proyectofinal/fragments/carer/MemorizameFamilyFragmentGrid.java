package com.jonathan.proyectofinal.fragments.carer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemorizameFamilyFragmentGrid extends Fragment {

    public MemorizameFamilyFragmentGrid(){}

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MemorizameFamilyGridAdapter adapter;
    private MemorizameFamilyGridAdapter.ISelectionMemorizame iSelectionMemorizame;
    private MemorizameFamilyGridAdapter.IDeleteMemorizame iDeleteMemorizame;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Memorizame> memorizameList = new ArrayList<>();
    String userHPoCarer = "";
    Memorizame memorizameM = new Memorizame();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memorizame_grid,container,false);
        ButterKnife.bind(this, view);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());


        initAdapter();
        initRecyclerView();

        return view;
    }
    private void initAdapter() {

        if (adapter == null){
         //   adapter = new MemorizameFamilyFragmentGrid(getActivity().getApplicationContext(), this);
        }


    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas

        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReferenceMemorizame = db.collection(Constants.Memorizame);

        collectionReferenceMemorizame
                .whereArrayContains("assigns", uid)
                .orderBy("firstName")
                .get()
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


}
