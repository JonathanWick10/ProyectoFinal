package com.jonathan.proyectofinal.fragments.admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAddHealthProfessional extends Fragment {

    private View view;
    public AdminAddHealthProfessional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_add_health_professional, container, false);
        return view;
    }
}
