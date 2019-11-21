package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.CognitivesAdapter;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.data.CognitivesExcercises;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CognitiveTherapyPSFragment extends Fragment {

    CognitivesAdapter cognitivesAdapter;
    @BindView(R.id.list_cognitives)
    RecyclerView list_cognitives;
    Bundle bundle;

    public CognitiveTherapyPSFragment(Bundle bundle) {
        this.bundle = bundle;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_therapy_cognitive, container, false);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        if (bundle!=null){
            String uID = bundle.getString("patientUID");
        }
        fillRecycler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cognitivesAdapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        cognitivesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cognitivesAdapter.stopListening();
    }

    private void fillRecycler() {
        list_cognitives.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("CognitiveExcercises");

        FirestoreRecyclerOptions<CognitivesExcercises> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<CognitivesExcercises>()
                .setQuery(query, CognitivesExcercises.class).build();

        cognitivesAdapter = new CognitivesAdapter(firestoreRecyclerOptions, getActivity());
        cognitivesAdapter.notifyDataSetChanged();
        list_cognitives.setAdapter(cognitivesAdapter);
    }

}
