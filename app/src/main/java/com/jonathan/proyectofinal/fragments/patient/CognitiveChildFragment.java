package com.jonathan.proyectofinal.fragments.patient;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.Distribution;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.CognitiveExcercisesAdapter;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import com.jonathan.proyectofinal.fragments.games.Memorama;
import com.jonathan.proyectofinal.interfaces.IComunicateFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CognitiveChildFragment extends Fragment {

    /*
    @BindView(R.id.expandableView)
    LinearLayout expandableView;
    @BindView(R.id.btnExpand)
    Button btnExpand;
    @BindView(R.id.cardActivity)
    CardView cardActivity;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.data_rating)
    TextView ratingTxt;
    */

    List<CognitiveExcercisesAssignment> list;
    CognitiveExcercisesAdapter cognitiveExcercisesAdapter;

    @BindView(R.id.list_cognitive_excercises)
    RecyclerView listCognitiveExcercises;

    IComunicateFragment interfaceComunicateFragments;
    View vista;
    Activity activity;

    CognitiveExcercisesAdapter.ISelectionItem iSelectionItem;

    /*
    private static final String ARGUMENT_POSITION = "argument_position";

    public static CognitiveChildFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        CognitiveChildFragment fragment = new CognitiveChildFragment();
        fragment.setArguments(args);
        return fragment;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_cognitive_child, container, false);
        ButterKnife.bind(this, vista);
        eventLogicSelectItem();
        fillRecycler();
        return vista;

    }

    private void eventLogicSelectItem() {
        iSelectionItem = new CognitiveExcercisesAdapter.ISelectionItem() {
            @Override
            public void clickSelect() {
                interfaceComunicateFragments.inicarJuego();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        cognitiveExcercisesAdapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        cognitiveExcercisesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cognitiveExcercisesAdapter.stopListening();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void fillRecycler(){
        listCognitiveExcercises.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection("CognitiveExcercisesAssignments")
                .whereEqualTo("uidPatient", "nzRl9DrDlTSjxfySnLhWTJgeNEi1");

        FirestoreRecyclerOptions<CognitiveExcercisesAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<CognitiveExcercisesAssignment>()
                .setQuery(query, CognitiveExcercisesAssignment.class).build();

        //Passing parameters to the adapter
        cognitiveExcercisesAdapter = new CognitiveExcercisesAdapter(firestoreRecyclerOptions, getActivity(), iSelectionItem);
        cognitiveExcercisesAdapter.notifyDataSetChanged();
        listCognitiveExcercises.setAdapter(cognitiveExcercisesAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity = (Activity) context;
            interfaceComunicateFragments = (IComunicateFragment) activity;
        }
    }
}
