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

import com.jonathan.proyectofinal.R;
public class InformationCarerFragment extends Fragment implements View.OnClickListener{

    TextView a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26;
    Button q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26;
    public InformationCarerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_carer, container, false);

        q1 = view.findViewById(R.id.q1);   q1.setOnClickListener(this);
        a1 = view.findViewById(R.id.a1);   a1.setOnClickListener(this);
        q2 = view.findViewById(R.id.q2);   q2.setOnClickListener(this);
        a2 = view.findViewById(R.id.a2);   a2.setOnClickListener(this);
        q3 = view.findViewById(R.id.q3);   q3.setOnClickListener(this);
        a3 = view.findViewById(R.id.a3);   a3.setOnClickListener(this);
        q4 = view.findViewById(R.id.q4);   q4.setOnClickListener(this);
        a4 = view.findViewById(R.id.a4);   a4.setOnClickListener(this);
        q5 = view.findViewById(R.id.q5);   q5.setOnClickListener(this);
        a5 = view.findViewById(R.id.a5);   a5.setOnClickListener(this);
        q6 = view.findViewById(R.id.q6);   q6.setOnClickListener(this);
        a6 = view.findViewById(R.id.a6);   a6.setOnClickListener(this);
        q7 = view.findViewById(R.id.q7);   q7.setOnClickListener(this);
        a7 = view.findViewById(R.id.a7);   a7.setOnClickListener(this);
        q8 = view.findViewById(R.id.q8);   q8.setOnClickListener(this);
        a8 = view.findViewById(R.id.a8);   a8.setOnClickListener(this);
        q9 = view.findViewById(R.id.q9);   q9.setOnClickListener(this);
        a9 = view.findViewById(R.id.a9);   a9.setOnClickListener(this);
        q10 = view.findViewById(R.id.q10); q10.setOnClickListener(this);
        a10 = view.findViewById(R.id.a10); a10.setOnClickListener(this);
        q11 = view.findViewById(R.id.q11); q11.setOnClickListener(this);
        a11 = view.findViewById(R.id.a11); a11.setOnClickListener(this);
        q12 = view.findViewById(R.id.q12); q12.setOnClickListener(this);
        a12 = view.findViewById(R.id.a12); a12.setOnClickListener(this);
        q13 = view.findViewById(R.id.q13); q13.setOnClickListener(this);
        a13 = view.findViewById(R.id.a13); a13.setOnClickListener(this);
        q14 = view.findViewById(R.id.q14); q14.setOnClickListener(this);
        a14 = view.findViewById(R.id.a14); a14.setOnClickListener(this);
        q15 = view.findViewById(R.id.q15); q15.setOnClickListener(this);
        a15 = view.findViewById(R.id.a15); a15.setOnClickListener(this);
        q16 = view.findViewById(R.id.q16); q16.setOnClickListener(this);
        a16 = view.findViewById(R.id.a16); a16.setOnClickListener(this);
        q17 = view.findViewById(R.id.q17); q17.setOnClickListener(this);
        a17 = view.findViewById(R.id.a17); a17.setOnClickListener(this);
        q18 = view.findViewById(R.id.q18); q18.setOnClickListener(this);
        a18 = view.findViewById(R.id.a18); a18.setOnClickListener(this);
        q19 = view.findViewById(R.id.q19); q19.setOnClickListener(this);
        a19 = view.findViewById(R.id.a19); a19.setOnClickListener(this);
        q20 = view.findViewById(R.id.q20); q20.setOnClickListener(this);
        a20 = view.findViewById(R.id.a20); a20.setOnClickListener(this);
        q21 = view.findViewById(R.id.q21); q21.setOnClickListener(this);
        a21 = view.findViewById(R.id.a21); a21.setOnClickListener(this);
        q22 = view.findViewById(R.id.q22); q22.setOnClickListener(this);
        a22 = view.findViewById(R.id.a22); a22.setOnClickListener(this);
        q23 = view.findViewById(R.id.q23); q23.setOnClickListener(this);
        a23 = view.findViewById(R.id.a23); a23.setOnClickListener(this);
        q24 = view.findViewById(R.id.q24); q24.setOnClickListener(this);
        a24 = view.findViewById(R.id.a24); a24.setOnClickListener(this);
        q25 = view.findViewById(R.id.q25); q25.setOnClickListener(this);
        a25 = view.findViewById(R.id.a25); a25.setOnClickListener(this);
        q26 = view.findViewById(R.id.q26); q26.setOnClickListener(this);
        a26 = view.findViewById(R.id.a26); a26.setOnClickListener(this);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.q1:
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
            case R.id.q2:
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
            case R.id.q3:
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
            case R.id.q4:
                if(a4.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a4.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a4.setVisibility(v.GONE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a4.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a4.setVisibility(v.VISIBLE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q5:
                if(a5.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a5.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a5.setVisibility(v.GONE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a5.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a5.setVisibility(v.VISIBLE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q6:
                if(a6.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a6.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a6.setVisibility(v.GONE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a6.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a6.setVisibility(v.VISIBLE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q7:
                if(a7.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a7.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a7.setVisibility(v.GONE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a7.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a7.setVisibility(v.VISIBLE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q8:
                if(a8.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a8.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a8.setVisibility(v.GONE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a8.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a8.setVisibility(v.VISIBLE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q9:
                if(a9.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a9.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a9.setVisibility(v.GONE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a9.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a9.setVisibility(v.VISIBLE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q10:
                if(a10.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a10.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a10.setVisibility(v.GONE);
                    q10.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a10.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a10.setVisibility(v.VISIBLE);
                    q10.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q11:
                if(a11.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a11.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a11.setVisibility(v.GONE);
                    q11.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a11.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a11.setVisibility(v.VISIBLE);
                    q11.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q12:
                if(a12.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a12.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a12.setVisibility(v.GONE);
                    q12.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a12.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a12.setVisibility(v.VISIBLE);
                    q12.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q13:
                if(a13.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a13.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a13.setVisibility(v.GONE);
                    q13.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a13.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a13.setVisibility(v.VISIBLE);
                    q13.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q14:
                if(a14.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a14.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a14.setVisibility(v.GONE);
                    q14.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a14.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a14.setVisibility(v.VISIBLE);
                    q14.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q15:
                if(a15.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a15.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a15.setVisibility(v.GONE);
                    q15.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a15.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a15.setVisibility(v.VISIBLE);
                    q15.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q16:
                if(a16.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a16.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a16.setVisibility(v.GONE);
                    q16.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a16.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a16.setVisibility(v.VISIBLE);
                    q16.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q17:
                if(a17.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a17.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a17.setVisibility(v.GONE);
                    q17.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a17.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a17.setVisibility(v.VISIBLE);
                    q17.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q18:
                if(a18.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a18.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a18.setVisibility(v.GONE);
                    q18.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a18.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a18.setVisibility(v.VISIBLE);
                    q18.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q19:
                if(a19.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a19.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a19.setVisibility(v.GONE);
                    q19.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a19.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a19.setVisibility(v.VISIBLE);
                    q19.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q20:
                if(a20.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a20.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a20.setVisibility(v.GONE);
                    q20.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a20.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a20.setVisibility(v.VISIBLE);
                    q20.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q21:
                if(a21.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a21.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a21.setVisibility(v.GONE);
                    q21.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a21.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a21.setVisibility(v.VISIBLE);
                    q21.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q22:
                if(a22.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a22.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a22.setVisibility(v.GONE);
                    q22.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a22.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a22.setVisibility(v.VISIBLE);
                    q22.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q23:
                if(a23.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a23.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a23.setVisibility(v.GONE);
                    q23.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a23.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a23.setVisibility(v.VISIBLE);
                    q23.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q24:
                if(a24.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a24.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a24.setVisibility(v.GONE);
                    q24.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a24.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a24.setVisibility(v.VISIBLE);
                    q24.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q25:
                if(a15.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a25.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a25.setVisibility(v.GONE);
                    q25.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a25.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a25.setVisibility(v.VISIBLE);
                    q25.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
            case R.id.q26:
                if(a26.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    a26.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a26.setVisibility(v.GONE);
                    q26.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.down_black),null);
                }else{ // si no es Visible, lo pones
                    a26.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                    a26.setVisibility(v.VISIBLE);
                    q26.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.up),null);
                }
                break;
        }
    }
}