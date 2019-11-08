package com.jonathan.proyectofinal.fragments.games;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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
    private Memorama.Memoramai memoramai ;

    public PhysicalExercisePractic(Memorama.Memoramai memoramai) {

        this.memoramai = memoramai;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_physical_exercise_practic, container, false);
        ButterKnife.bind(this, view);

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //texto a mostrar en cuenta regresiva en un textview
                countDown.setText((millisUntilFinished / 1000 + ""));
            }

            @Override
            public void onFinish() {
                //iniciar alerta de salida
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                //información
                View viewInflater = getLayoutInflater().inflate(R.layout.physicalexersice_plantilla_exerxise_end , null);
                builder.setView(viewInflater);
                Button btnReload =  viewInflater.findViewById(R.id.physicla_exercise_winp_reload);
                Button btnBackToMenu = viewInflater.findViewById(R.id.physical_exercise_winp_btnonback);

                AlertDialog dialog = builder.create();
                dialog.show();

                final AlertDialog finalDialog = dialog;

                btnReload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalDialog.dismiss();
                        memoramai.reloadGame("Physical");
                    }
                });

                btnBackToMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalDialog.dismiss();
                        memoramai.callOnbackPressed();
                    }
                });
            }
        }.start();

        return view;
    }
}
