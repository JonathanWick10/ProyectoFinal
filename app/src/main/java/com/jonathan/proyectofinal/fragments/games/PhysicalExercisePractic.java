package com.jonathan.proyectofinal.fragments.games;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhysicalExercisePractic extends Fragment{

    @BindView(R.id.text_view_countdown)
            public EditText text_view_countdown;

    View view ;
    public PhysicalExercisePractic() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_physical_exercise_practic, container, false);
        ButterKnife.bind(this,view);

        final MiContador timer = new MiContador(30000,1000);
        timer.setView(text_view_countdown);
        timer.start();

        return view;
    }

    public class MiContador extends CountDownTimer{

        public TextView countDown;

        public MiContador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //Lo que quieras hacer al finalizar

        }

        @Override
        public void onTick(long millisUntilFinished) {
            //texto a mostrar en cuenta regresiva en un textview
            countDown.setText((millisUntilFinished/1000+""));
        }

        public void setView (TextView countDown){
            this.countDown = countDown;
        }
    }
}
