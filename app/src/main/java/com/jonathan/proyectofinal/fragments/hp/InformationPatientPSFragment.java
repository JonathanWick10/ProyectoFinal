package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationPatientPSFragment extends Fragment {

    @BindView(R.id.tv_ps_agePatient)
    TextView carolina;
    @BindView(R.id.tv_ps_genderPatient)
    TextView caroObarato;
    String agePatient="";

    public InformationPatientPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ps_information_patient, container, false);
        ButterKnife.bind(this, view);
        initDatas();

        return view;

    }


    public void initDatas() {
       agePatient="25";


   // HELP ME!!!!!
        carolina.setText(": " +agePatient+" años");
        caroObarato.setText("jonathan");

    }


}
