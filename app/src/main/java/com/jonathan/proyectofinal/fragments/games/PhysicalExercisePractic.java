package com.jonathan.proyectofinal.fragments.games;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jonathan.proyectofinal.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysicalExercisePractic extends Fragment {

    @BindView(R.id.text_view_countdown)
    public TextView countDown;

    private View view;

    public PhysicalExercisePractic() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_physical_exercise_practic, container, false);
        ButterKnife.bind(this, view);

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //texto a mostrar en cuenta regresiva en un textview
                countDown.setText((millisUntilFinished / 1000 + ""));
            }

            @Override
            public void onFinish() {
                Toast.makeText(view.getContext(), "Termino", Toast.LENGTH_SHORT).show();
            }
        }.start();

        return view;
    }
}
