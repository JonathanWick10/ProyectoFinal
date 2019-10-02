package com.jonathan.proyectofinal.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jonathan.proyectofinal.R;

public class AddPatients extends Fragment {
    public AddPatients() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_add_patients,container,false);

        String[] documentos = new String[] {"Cédula de ciudadania", "Cédula de etranjeria"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        AutoCompleteTextView tipoIdentificacion = view.findViewById(R.id.edit_identification_type_patient);
        tipoIdentificacion.setAdapter(adapter);

        return view;
    }

}
