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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;
import com.jonathan.proyectofinal.fragments.patient.MotorChildFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class MotorExcercisesAdapter extends FirestoreRecyclerAdapter<MotorExcercisesAssignment, MotorExcercisesAdapter.ViewHolder> {

    FirebaseFirestore db;
    private MotorChildFragment.MotorChildFragmentI motorChildFragmentI;
    Context context;
    Integer newRating;

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
        db = FirebaseFirestore.getInstance();

        final DocumentSnapshot excerciseDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        //final String idDoc = excerciseDocument.getId();
        //final String idDoc = getSnapshots().getSnapshot(position).getReference().getId();
        final String idDoc = getSnapshots().getSnapshot(holder.getAdapterPosition()).getId();

        Glide.with(context).load(model.getUriGifExcercise()).fitCenter().into(holder.image);
        holder.nameE.setText(model.getNameExcercise());
        holder.descE.setText(model.getDescriptionExcercise());
        holder.finishE.setText(model.getFinished());
        holder.ratingTxt.setText(String.valueOf(model.getRating()));
        holder.ratingBar.setRating(model.getRating());holder.ratingBar.setRating(model.getRating());
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
                        newRating = 1;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 2:
                        newRating = 2;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 3:
                        newRating = 3;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 4:
                        newRating = 4;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 5:
                        newRating = 5;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    default:
                        newRating = 0;
                        holder.ratingTxt.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
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

    private void updateRating(String idDoc, Integer rating) {
        DocumentReference documentReference = db.collection("MotorExcercisesAssignments").document(idDoc);
        documentReference.update("rating", rating).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, R.string.rate_saved, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, R.string.rate_no_saved, Toast.LENGTH_SHORT).show();
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
