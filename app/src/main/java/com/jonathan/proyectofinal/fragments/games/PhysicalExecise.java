package com.jonathan.proyectofinal.fragments.games;

import android.app.AlertDialog;
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


public class PhysicalExecise extends Fragment {



    public PhysicalExecise() {
        // Required empty public constructor
    }

    private View view;
    private RecyclerView rv;
    private PhysicalExecise.PhysicalExeciseI physicalExeciseI ;

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
        List<PhysicalExerciseEntity> lista = new ArrayList<>();
        PhysicalExerciseEntity entity = new PhysicalExerciseEntity();
        PhysicalExerciseEntity entity1 = new PhysicalExerciseEntity();
        entity1.setNameExercise("Sentadillas");
        lista.add(entity1);
        entity.setNameExercise("Flexiones de pecho");
        entity.setNameExercise("abdominales");
        lista.add(entity);
        rv = view.findViewById(R.id.physicalExercise_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(new PhysicalExerciseAdapter(lista));
    }

    public interface PhysicalExeciseI{
        void alert();
    }
}
