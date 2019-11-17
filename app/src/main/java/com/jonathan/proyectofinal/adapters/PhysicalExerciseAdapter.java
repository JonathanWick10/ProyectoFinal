package com.jonathan.proyectofinal.adapters;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class PhysicalExerciseAdapter extends RecyclerView.Adapter<PhysicalExerciseAdapter.holder> {

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
        ButterKnife.bind(this, view);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int position) {

        final PhysicalExerciseEntity listExerciseget = listExercise.get(position);
        //setear datos
        holder.nameE.setText(listExerciseget.getNameExercise());
        holder.image.setImageResource(listExerciseget.getImage());
        //holder.description.setText(listExerciseget.getDescripcion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                physicalExeciseI.alert("eliminar", listExerciseget);
            }
        });
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        //ratingTxt.setText("Very bad");
                        holder.ratingTxt.setText("1");
                        break;
                    case 2:
                        //ratingTxt.setText("Need some improvement");
                        holder.ratingTxt.setText("2");
                        break;
                    case 3:
                        //ratingTxt.setText("Good");
                        holder.ratingTxt.setText("3");
                        break;
                    case 4:
                        //ratingTxt.setText("Great");
                        holder.ratingTxt.setText("4");
                        break;
                    case 5:
                        //ratingTxt.setText("Awesome. I love it");
                        holder.ratingTxt.setText("5");
                        break;
                    default:
                        holder.ratingTxt.setText("");
                }
            }
        });

        holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(holder.cardActivity, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black);
                } else {
                    TransitionManager.beginDelayedTransition(holder.cardActivity, new AutoTransition());
                    holder.expandableView.setVisibility(View.GONE);
                    holder.btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listExercise.size();
    }


    public class holder extends RecyclerView.ViewHolder {
        //region reference
        @BindView(R.id.physical_exercise_txtv_name)
        TextView nameE;
        @BindView(R.id.physical_exercise_imagev_gif)
        GifImageView image;
        @BindView(R.id.expandableViewMotor)
        LinearLayout expandableView;
        @BindView(R.id.btnExpandMotor)
        Button btnExpand;
        @BindView(R.id.cardActivityMotor)
        CardView cardActivity;
        @BindView(R.id.ratingBarMotor)
        RatingBar ratingBar;
        @BindView(R.id.data_rating_motor)
        TextView ratingTxt;
        // endregion


        public holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

