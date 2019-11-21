package com.jonathan.proyectofinal.fragments.hp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.GraphAdapter;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphPSFragment extends Fragment {

//region reference variables recycler

    private GraphAdapter adapter;
    private GraphAdapter.ISelectItem iSelectItem;

    Patient patient= new Patient();


    private FirebaseFirestore db;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
/*
    @BindView(R.id.text_name_exercise)
    TextView textName;

    @BindView(R.id.graph1)
    LineChart graphRecycler;

 */
//endregion


    //region for Graph
    private View view;

    public GraphPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ps_graph, container, false);
        ButterKnife.bind(this, view);

//region for recycler
        db = FirebaseFirestore.getInstance();
        SharedPreferences preferences = getActivity().getPreferences(0);
        Gson gson = new Gson();
        String json = preferences.getString("serialipatient", "");
        patient = gson.fromJson(json, Patient.class);

        //endregion





        eventClick();
        initRecyclerView();

        return view;
    }


    //endregion


    //region for Recycler

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

    private void eventClick(){
        iSelectItem= new GraphAdapter.ISelectItem() {
            @Override
            public void clickSelectItem(CognitiveExcercisesAssignment cognitiveExcercisesAssignment) {
                Toast.makeText(getActivity(), "Funciono"+cognitiveExcercisesAssignment.getNameExcercise(), Toast.LENGTH_SHORT).show();
            }
        };
    }


    private void initRecyclerView() {

        if (patient.getPatientUID()!=null){


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Query creation
        Query query = db.collection(Constants.CognitiveExcercisesAssignments).whereEqualTo("uidPatient",patient.getPatientUID());
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<CognitiveExcercisesAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<CognitiveExcercisesAssignment>()
                .setQuery(query, CognitiveExcercisesAssignment.class).build();

        //Passing parameters to the adapter
        adapter = new GraphAdapter(firestoreRecyclerOptions, getActivity(), iSelectItem);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        }

        else {
            Toast.makeText(getActivity(), "No has jugado", Toast.LENGTH_SHORT).show();
        }
    }


    //endregion


}
