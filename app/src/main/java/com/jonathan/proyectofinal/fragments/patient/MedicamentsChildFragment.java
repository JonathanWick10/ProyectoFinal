package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MedicamentsAdapter;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicamentsChildFragment extends Fragment {

    //regoin Variables
    //Views
    @BindView(R.id.list_notifications_medicaments)
    RecyclerView rcMedicaments;
    //Adapter
    MedicamentsAdapter medicamentsAdapter;
    MedicamentsAdapter.ISelectItemMedicaments iSelectItemMedicaments;
    //Firebase
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    //endregion

    public MedicamentsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicaments_child, container, false);
        ButterKnife.bind(this, view);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        eventLogicSelectItem();
        initRecycler();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        medicamentsAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        medicamentsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        medicamentsAdapter.startListening();
    }

    private void eventLogicSelectItem() {
        iSelectItemMedicaments = new MedicamentsAdapter.ISelectItemMedicaments() {
            @Override
            public void clickSelect(MedicationAssignment medicationAssignment) {
                Toast.makeText(getActivity(), "selecciono "+medicationAssignment.getMedicamentName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void initRecycler() {
        rcMedicaments.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcMedicine.setLayoutManager(new GridLayoutManager(getActivity(), 3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Medicines).document(user.getUid()).collection(Constants.Medicine);
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<MedicationAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MedicationAssignment>()
                .setQuery(query, MedicationAssignment.class).build();

        //Passing parameters to the adapter
        medicamentsAdapter = new MedicamentsAdapter(firestoreRecyclerOptions,getActivity(), iSelectItemMedicaments);
        medicamentsAdapter.notifyDataSetChanged();
        rcMedicaments.setAdapter(medicamentsAdapter);

    }

}
