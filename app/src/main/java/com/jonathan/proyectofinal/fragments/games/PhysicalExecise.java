package com.jonathan.proyectofinal.fragments.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PhysicalExerciseAdapter;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;

import java.util.ArrayList;
import java.util.List;


public class PhysicalExecise extends Fragment  {

    private PhysicalExeciseI physicalExeciseI;
    private View view;

    public PhysicalExecise() {
        // Required empty public constructor
    }

    public PhysicalExecise(PhysicalExeciseI physicalExeciseI) {
        this.physicalExeciseI = physicalExeciseI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_physical_execise, container, false);

        recylerView();
        // Inflate the layout for this fragment
        return view;
    }

    private void recylerView() {

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.physicalExercise_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PhysicalExerciseAdapter(entities(),physicalExeciseI));
    }

    private List<PhysicalExerciseEntity> entities(){

        List<PhysicalExerciseEntity> lista = new ArrayList<>();
        PhysicalExerciseEntity entity1 = new PhysicalExerciseEntity();
        entity1.setNameExercise(getString(R.string.physical_nameExercise_resistence));
        entity1.setDescripcion(getString(R.string.description_resistance_exercice));
        entity1.setImage(R.drawable.caminata);
        entity1.setTime(300000);
        lista.add(entity1);

        // dos
        PhysicalExerciseEntity entity2 = new PhysicalExerciseEntity();
        entity2.setNameExercise(getString(R.string.physical_nameexercise_bodybuilding));
        entity2.setDescripcion(getString(R.string.description_nameexercise_bodybuilding));
        entity2.setImage(R.drawable.sentadilla);
        entity2.setTime(30000);
        lista.add(entity2);

        return  lista;
    }

    public interface PhysicalExeciseI{
        void alert(String option , PhysicalExerciseEntity listExerciseget);
    }
}
