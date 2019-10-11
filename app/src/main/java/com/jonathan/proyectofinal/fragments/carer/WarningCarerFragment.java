package com.jonathan.proyectofinal.fragments.carer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WarningCarerFragment extends Fragment implements View.OnClickListener{

    TextView a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
    Button q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;

    public WarningCarerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warning_carer, container, false);

        q1 = view.findViewById(R.id.q1); q1.setOnClickListener(this);
        a1 = view.findViewById(R.id.a1); a1.setOnClickListener(this);
        q2 = view.findViewById(R.id.q2); q2.setOnClickListener(this);
        a2 = view.findViewById(R.id.a2); a2.setOnClickListener(this);
        q3 = view.findViewById(R.id.q3); q3.setOnClickListener(this);
        a3 = view.findViewById(R.id.a3); a3.setOnClickListener(this);
        q4 = view.findViewById(R.id.q4); q4.setOnClickListener(this);
        a4 = view.findViewById(R.id.a4); a4.setOnClickListener(this);
        q5 = view.findViewById(R.id.q5); q5.setOnClickListener(this);
        a5 = view.findViewById(R.id.a5); a5.setOnClickListener(this);
        q6 = view.findViewById(R.id.q6); q6.setOnClickListener(this);
        a6 = view.findViewById(R.id.a6); a6.setOnClickListener(this);
        q7 = view.findViewById(R.id.q7); q7.setOnClickListener(this);
        a7 = view.findViewById(R.id.a7); a7.setOnClickListener(this);
        q8 = view.findViewById(R.id.q8); q8.setOnClickListener(this);
        a8 = view.findViewById(R.id.a8); a8.setOnClickListener(this);
        q9 = view.findViewById(R.id.q9); q9.setOnClickListener(this);
        a9 = view.findViewById(R.id.a9); a9.setOnClickListener(this);
        q10 = view.findViewById(R.id.q10); q10.setOnClickListener(this);
        a10 = view.findViewById(R.id.a10); a10.setOnClickListener(this);
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
            case R.id.q4:
                if(a4.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a4.setVisibility(v.GONE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a4.setVisibility(v.VISIBLE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q5:
                if(a5.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a5.setVisibility(v.GONE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a5.setVisibility(v.VISIBLE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q6:
                if(a6.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a6.setVisibility(v.GONE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a6.setVisibility(v.VISIBLE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q7:
                if(a7.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a7.setVisibility(v.GONE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a7.setVisibility(v.VISIBLE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q8:
                if(a8.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a8.setVisibility(v.GONE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a8.setVisibility(v.VISIBLE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q9:
                if(a9.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a9.setVisibility(v.GONE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a9.setVisibility(v.VISIBLE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q10:
                if(a10.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a10.setVisibility(v.GONE);
                    q10.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a10.setVisibility(v.VISIBLE);
                    q10.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
        }
    }

}
