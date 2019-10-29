package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.jonathan.proyectofinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentsChildFragment extends Fragment {

    @BindView(R.id.btn_show_hide_medicament)
    MaterialButton btn_show_hide;
    @BindView(R.id.expandableMedicamentView)
    LinearLayout expandableView;
    @BindView(R.id.cardActivityMedicaments)
    MaterialCardView cardMedicament;


    public MedicamentsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicaments_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_show_hide_medicament)
    public void btnShowCollapseNotification(){
        if (expandableView.getVisibility() == View.GONE){
            TransitionManager.beginDelayedTransition(cardMedicament, new AutoTransition());
            expandableView.setVisibility(View.VISIBLE);
            btn_show_hide.setText(R.string.btn_hide_info);
            //btn_show_hide.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            //btn_show_hide.setLayoutParams(params);
            btn_show_hide.setWidth(240);
        } else {
            TransitionManager.beginDelayedTransition(cardMedicament, new AutoTransition());
            expandableView.setVisibility(View.GONE);
            btn_show_hide.setText(R.string.btn_show_info);
            //LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //btn_show_hide.setLayoutParams(params);
            //btn_show_hide.setWidth(350);
        }
    }
}
