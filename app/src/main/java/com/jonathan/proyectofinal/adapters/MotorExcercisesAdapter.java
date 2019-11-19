package com.jonathan.proyectofinal.adapters;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;
import com.jonathan.proyectofinal.fragments.patient.MotorChildFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class MotorExcercisesAdapter extends FirestoreRecyclerAdapter<MotorExcercisesAssignment, MotorExcercisesAdapter.ViewHolder> {

    private MotorChildFragment.MotorChildFragmentI motorChildFragmentI;
    Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MotorExcercisesAdapter(@NonNull FirestoreRecyclerOptions<MotorExcercisesAssignment> options, Context context, MotorChildFragment.MotorChildFragmentI motorChildFragmentI) {
        super(options);
        this.context = context;
        this.motorChildFragmentI = motorChildFragmentI;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //region reference
        @BindView(R.id.physical_exercise_imagev_gif)
        GifImageView image;
        @BindView(R.id.physical_exercise_txtv_name)
        TextView nameE;
        @BindView(R.id.physical_exercise_txtv_resume)
        TextView descE;
        @BindView(R.id.data_finished)
        TextView finishE;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // endregion
    }

    @Override
    protected void onBindViewHolder(@NonNull final MotorExcercisesAdapter.ViewHolder holder, final int position, @NonNull final MotorExcercisesAssignment model) {
        //final DocumentSnapshot healthDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final DocumentSnapshot excerciseDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        Glide.with(context).load(model.getUriGifExcercise()).fitCenter().into(holder.image);
        holder.nameE.setText(model.getNameExcercise());
        holder.descE.setText(model.getDescriptionExcercise());
        holder.finishE.setText(model.getFinished());
        holder.ratingTxt.setText(String.valueOf(model.getRating()));
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

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        holder.ratingTxt.setText("1");
                        break;
                    case 2:
                        holder.ratingTxt.setText("2");
                        break;
                    case 3:
                        holder.ratingTxt.setText("3");
                        break;
                    case 4:
                        holder.ratingTxt.setText("4");
                        break;
                    case 5:
                        holder.ratingTxt.setText("5");
                        break;
                    default:
                        holder.ratingTxt.setText("");
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motorChildFragmentI.alert("eliminar", excerciseDocument.toObject(MotorExcercisesAssignment.class));
            }
        });
    }

    @NonNull
    @Override
    public MotorExcercisesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_execise_plantilla, parent, false);
        return new ViewHolder(view);
    }
}
