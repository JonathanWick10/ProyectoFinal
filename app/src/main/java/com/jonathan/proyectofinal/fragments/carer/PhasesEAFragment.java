package com.jonathan.proyectofinal.fragments.carer;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.jonathan.proyectofinal.R;

public class PhasesEAFragment extends Fragment implements View.OnClickListener {

    TextView a1,a2,a3;
    MaterialButton q1,q2,q3;


    public PhasesEAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phases_ea, container, false);
        // Inflate the layout for this fragment
        q1 = view.findViewById(R.id.qf1);
        a1 = view.findViewById(R.id.af1);
        q2 = view.findViewById(R.id.qf2);
        a2 = view.findViewById(R.id.af2);
        q3 = view.findViewById(R.id.qf3);
        a3 = view.findViewById(R.id.af3);
        q1.setOnClickListener(this);
        a1.setOnClickListener(this);
        q2.setOnClickListener(this);
        a2.setOnClickListener(this);
        q3.setOnClickListener(this);
        a3.setOnClickListener(this);
        q1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
        q2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
        q3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qf1:
                if(a1.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a1.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a1.setVisibility(v.GONE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a1.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a1.setVisibility(v.VISIBLE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.qf2:
                if(a2.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a2.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a2.setVisibility(v.GONE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a2.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a2.setVisibility(v.VISIBLE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.qf3:
                if(a3.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a3.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a3.setVisibility(v.GONE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a3.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a3.setVisibility(v.VISIBLE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
        }
    }
}
