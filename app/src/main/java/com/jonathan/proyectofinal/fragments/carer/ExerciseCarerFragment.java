package com.jonathan.proyectofinal.fragments.carer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseCarerFragment extends Fragment {


    public ExerciseCarerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_carer, container, false);
    }

}
