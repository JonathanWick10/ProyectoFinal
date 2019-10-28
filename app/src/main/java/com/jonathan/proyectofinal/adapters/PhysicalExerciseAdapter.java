package com.jonathan.proyectofinal.adapters;

import android.app.AlertDialog;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class PhysicalExerciseAdapter extends RecyclerView.Adapter<PhysicalExerciseAdapter.holder> {

    LinearLayout expandableView;
    Button btnExpand;
    CardView cardActivity;
    RatingBar ratingBar;
    TextView ratingTxt;

    private List<PhysicalExerciseEntity> listExercise;
    private PhysicalExecise.PhysicalExeciseI physicalExeciseI;

    public PhysicalExerciseAdapter(List<PhysicalExerciseEntity> listExercise, PhysicalExecise.PhysicalExeciseI physicalExeciseI) {
        this.listExercise = listExercise;
        this.physicalExeciseI = physicalExeciseI;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_execise_plantilla, parent, false);

        expandableView = view.findViewById(R.id.expandableViewMotor);
        btnExpand = view.findViewById(R.id.btnExpandMotor);
        ratingBar = view.findViewById(R.id.ratingBarMotor);
        ratingTxt = view.findViewById(R.id.data_rating_motor);
        cardActivity = view.findViewById(R.id.cardActivityMotor);
        ratingBar.setOnRatingBarChangeListener(mOnRatingChangeListener);


        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardActivity, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black);
                } else {
                    TransitionManager.beginDelayedTransition(cardActivity, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black);
                }
            }
        });

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        //setear datos
        holder.nameE.setText(listExercise.get(position).getNameExercise());
        //Log.v("Alerta", "asdasd");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                physicalExeciseI.alert("eliminar");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listExercise.size();
    }


    public class holder extends RecyclerView.ViewHolder {
        TextView nameE, description;
        GifImageView image;
        public int time;

        public holder(@NonNull View itemView) {
            super(itemView);

            nameE = itemView.findViewById(R.id.physical_exercise_txtv_name);
            description = itemView.findViewById(R.id.physical_exercise_txtv_description);
            //image = itemView.findViewById(R.id.physical_exercise_imagev_gif);
        }
    }

    private RatingBar.OnRatingBarChangeListener mOnRatingChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            switch ((int) ratingBar.getRating()) {
                case 1:
                    //ratingTxt.setText("Very bad");
                    ratingTxt.setText("1");
                    break;
                case 2:
                    //ratingTxt.setText("Need some improvement");
                    ratingTxt.setText("2");
                    break;
                case 3:
                    //ratingTxt.setText("Good");
                    ratingTxt.setText("3");
                    break;
                case 4:
                    //ratingTxt.setText("Great");
                    ratingTxt.setText("4");
                    break;
                case 5:
                    //ratingTxt.setText("Awesome. I love it");
                    ratingTxt.setText("5");
                    break;
                default:
                    ratingTxt.setText("");
            }
        }
    };
}
