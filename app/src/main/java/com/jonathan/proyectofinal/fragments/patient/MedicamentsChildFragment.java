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
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MedicamentsAdapter;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicamentsChildFragment extends Fragment {

    List<MedicationAssignment> list;
    MedicamentsAdapter medicamentsAdapter;

    @BindView(R.id.list_notifications_medicaments)
    RecyclerView listMdicaments;


    public MedicamentsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicaments_child, container, false);
    }

    private void fillRecycler() {
        list = new ArrayList<>();
        list.add(new MedicationAssignment("","Donepezil",
                "Evita la descomposición de la acetilcolina en el cerebro.",
                "10-09-2019", "9:00am, 1:00pm", "Diariamente",
                "1 Píldora", "Sin terminar",""));
        list.add(new MedicationAssignment("","Rivastigmina",
                "Etapa temprana y moderada de la enfermedad de Alzheimer.",
                "11-11-2019", "7:00am, 2:00pm", "Diariamente",
                "1 Píldora", "Sin terminar",""));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        fillRecycler();
        medicamentsAdapter = new MedicamentsAdapter(list);
        listMdicaments.setAdapter(medicamentsAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listMdicaments.setLayoutManager(linearLayoutManager);
    }
}
