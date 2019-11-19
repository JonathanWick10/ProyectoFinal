package com.jonathan.proyectofinal.fragments.games;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.jonathan.proyectofinal.R;
import java.time.LocalTime;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysicalExercisePractic extends Fragment {

    @BindView(R.id.text_view_countdown)
    public TextView countDown;
    @BindView(R.id.fragment_pep_gif)
    GifImageView gifImageView;

    private Memorama.Memoramai memoramai;
    //private Integer img;
    private String img;
    private Integer time;

    public PhysicalExercisePractic(Memorama.Memoramai memoramai, /*Integer img*/String img, Integer time) {

        this.memoramai = memoramai;
        this.time = time;
        this.img = img;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_physical_exercise_practic, container, false);
        ButterKnife.bind(this, view);

        /*
        GifImageView asd = view.findViewById(R.id.fragment_pep_gif);
        asd.setImageResource(img);
        */
        Glide.with(this).load(img).fitCenter().into(gifImageView);

        new CountDownTimer(time, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTick(long millisUntilFinished) {
                //texto a mostrar en cuenta regresiva en un textview
                //segundo
                int seg = Integer.parseInt(String.valueOf(millisUntilFinished / 1000));
                countDown.setText(ConvertSecondToHHMMSSString(seg));
            }

            @Override
            public void onFinish() {
                //iniciar alerta de salida
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                //información
                View viewInflater = getLayoutInflater().inflate(R.layout.physicalexersice_plantilla_exerxise_end, null);
                builder.setView(viewInflater);

                Button btnReload = viewInflater.findViewById(R.id.physicla_exercise_winp_reload);
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String ConvertSecondToHHMMSSString(int nSecondTime) {
        return LocalTime.MIN.plusSeconds(nSecondTime).toString();
    }
}
