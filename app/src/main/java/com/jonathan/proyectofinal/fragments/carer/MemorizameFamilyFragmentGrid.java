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

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        /*recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        recyclerView.setAdapter(adapter);*/
    }


}
