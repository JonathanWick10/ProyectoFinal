package com.jonathan.proyectofinal.fragments.carer;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralInformationFragment extends Fragment {

    TextView textView;


    public GeneralInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_information, container, false);
        textView = view.findViewById(R.id.textview);
        //textView.setText(R.string.);


        return view;
    }

}
