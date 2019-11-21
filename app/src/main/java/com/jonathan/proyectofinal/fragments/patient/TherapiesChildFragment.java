package com.jonathan.proyectofinal.fragments.patient;


import android.app.ActionBar;
import android.icu.text.MeasureFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.jonathan.proyectofinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TherapiesChildFragment extends Fragment {

    @BindView(R.id.btn_show_hide_therapy)
    MaterialButton btn_show_hide;
    @BindView(R.id.expandableTherapyView)
    LinearLayout expandableView;
    @BindView(R.id.cardActivityTherapy)
    MaterialCardView cardTherapy;
    @BindView(R.id.iv_therapy_miniature)
    ImageView imageView;

    public TherapiesChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_therapies_child, container, false);
        ButterKnife.bind(this, view);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/brainmher-51968.appspot.com/o/Excercises%2FCognitive%2Fmemorama.png?alt=media&token=33b03bda-1c0c-4430-8934-5c1515fe03bc").fitCenter().into(imageView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.btn_show_hide_therapy)
    public void btnShowCollapseNotification(){
        if (expandableView.getVisibility() == View.GONE){
            TransitionManager.beginDelayedTransition(cardTherapy, new AutoTransition());
            expandableView.setVisibility(View.VISIBLE);
            btn_show_hide.setText(R.string.btn_hide_info);
            //btn_show_hide.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            //btn_show_hide.setLayoutParams(params);
            btn_show_hide.setWidth(240);
        } else {
            TransitionManager.beginDelayedTransition(cardTherapy, new AutoTransition());
            expandableView.setVisibility(View.GONE);
            btn_show_hide.setText(R.string.btn_show_info);
            //LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //btn_show_hide.setLayoutParams(params);
            //btn_show_hide.setWidth(350);
        }
    }
}
