package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientMemorizameAdapter;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeChildFragment extends Fragment {

    //region Variables
    @BindView(R.id.recyclerViewHome)
    RecyclerView recyclerView;
    @BindView(R.id.layout_description_home)
    LinearLayout layout;
    private PatientMemorizameAdapter adapter;
    private PatientMemorizameAdapter.ISelectionMemorizame iSelectionMemorizame;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Memorizame> memorizameList = new ArrayList<>();
    Memorizame memorizameM = new Memorizame();
    Patient patient = new Patient();
    //endregion


    public HomeChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_child, container, false);
        ButterKnife.bind(this, view);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        logicEventItemSelect();
        initRecycler();
        return view;
    }

    private void logicEventItemSelect() {
        iSelectionMemorizame = new PatientMemorizameAdapter.ISelectionMemorizame() {
            @Override
            public void clickItem(Memorizame memorizame) {

            }
        };
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas

        CollectionReference collectionReferenceMemorizame = db.collection(Constants.Memorizame);

        collectionReferenceMemorizame.document(user.getUid()).collection("Home").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        memorizameList = new ArrayList<Memorizame>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            memorizameM = documentSnapshopt.toObject(Memorizame.class);
                            memorizameList.add(memorizameM);
                        }
                        adapter = new PatientMemorizameAdapter(memorizameList,getActivity(),iSelectionMemorizame);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        if (memorizameList.size()!=0){
                            layout.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }else{
                            layout.setVisibility(View.VISIBLE);
                        }
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
