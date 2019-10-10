package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CognitiveChildFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_cognitive_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        TextView textView = view.findViewById(R.id.tv_dashboard);
        int position =getArguments().getInt(ARGUMENT_POSITION, -1);
        textView.setText(position == 0 ? R.string.do_not_stop_believing : R.string.a8);
        */
    }
}
