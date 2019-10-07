package com.jonathan.proyectofinal.fragments.carer;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralInformationFragment extends Fragment implements View.OnClickListener {

    TextView a1,a2,a3;
    Button q1,q2,q3;


    public GeneralInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_information, container, false);
        q1 = view.findViewById(R.id.q1);
        a1 = view.findViewById(R.id.a1);
        q2 = view.findViewById(R.id.q2);
        a2 = view.findViewById(R.id.a2);
        q3 = view.findViewById(R.id.q3);
        a3 = view.findViewById(R.id.a3);
        q1.setOnClickListener(this);
        a1.setOnClickListener(this);
        q2.setOnClickListener(this);
        a2.setOnClickListener(this);
        q3.setOnClickListener(this);
        a3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.q1:
                if(a1.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a1.setVisibility(v.GONE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a1.setVisibility(v.VISIBLE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q2:
                if(a2.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a2.setVisibility(v.GONE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a2.setVisibility(v.VISIBLE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q3:
                if(a3.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a3.setVisibility(v.GONE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a3.setVisibility(v.VISIBLE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
        }
    }

}
