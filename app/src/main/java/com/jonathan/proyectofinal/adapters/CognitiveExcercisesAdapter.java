package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CognitiveExcercisesAdapter extends FirestoreRecyclerAdapter<CognitiveExcercisesAssignment, CognitiveExcercisesAdapter.ViewHolder> {

    FirebaseFirestore db;
    CognitiveExcercisesAdapter.ISelectionItem iSelectionItem;
    Context context;
    Integer newRating;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CognitiveExcercisesAdapter(@NonNull FirestoreRecyclerOptions<CognitiveExcercisesAssignment> options, Context context,
                                      CognitiveExcercisesAdapter.ISelectionItem iSelectionItem) {
        super(options);
        this.context = context;
        this.iSelectionItem = iSelectionItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View layout;

        @BindView(R.id.iv_cognitive_miniature)
        ImageView ivImage;
        @BindView(R.id.data_cognitive_name)
        TextView tvName;
        @BindView(R.id.data_cognitive_description)
        TextView tvDescription;
        @BindView(R.id.data_cognitive_level)
        TextView tvLevel;
        @BindView(R.id.data_cognitive_best_score)
        TextView tvScore;
        @BindView(R.id.data_cognitive_statement)
        TextView tvStatement;
        @BindView(R.id.data_cognitive_rating)
        TextView tvRating;
        @BindView(R.id.cognitiveRatingBar)
        RatingBar ratingBar;
        @BindView(R.id.cardCognitiveExcercise)
        MaterialCardView cardExcercise;
        @BindView(R.id.btnCognitiveExpand)
        Button btnExpand;
        @BindView(R.id.expandableCognitiveView)
        LinearLayout expandableView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final CognitiveExcercisesAdapter.ViewHolder holder, final int position, @NonNull CognitiveExcercisesAssignment model) {
        db = FirebaseFirestore.getInstance();
        final DocumentSnapshot cognitiveDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String idDoc = cognitiveDocument.getId();

        //put the data in the views
        Glide.with(context).load(model.getUriImageExcercise()).fitCenter().into(holder.ivImage);
        holder.tvName.setText(model.getNameExcercise());
        holder.tvDescription.setText(model.getDescriptionExcercise());
        holder.tvLevel.setText(String.valueOf(model.getLevel()));
        holder.tvScore.setText(String.valueOf(model.getBestScore()));
        holder.tvStatement.setText(model.getStatement());
        holder.tvRating.setText(String.valueOf(model.getRating()));
        holder.ratingBar.setRating(model.getRating());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        newRating = 1;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 2:
                        newRating = 2;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 3:
                        newRating = 3;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 4:
                        newRating = 4;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    case 5:
                        newRating = 5;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                        break;
                    default:
                        newRating = 0;
                        holder.tvRating.setText(String.valueOf(newRating));
                        updateRating(idDoc, newRating);
                }
            }
        });

        holder.cardExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionItem.clickSelect();
            }
        });

        holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(holder.cardExcercise, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black);
                } else {
                    TransitionManager.beginDelayedTransition(holder.cardExcercise, new AutoTransition());
                    holder.expandableView.setVisibility(View.GONE);
                    holder.btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black);
                }
            }
        });
    }

    private void updateRating(String idDoc, Integer newRating) {
        DocumentReference documentReference = db.collection("CognitiveExcercisesAssignments").document(idDoc);
        documentReference.update("rating", newRating).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Calificación guardada.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error al guardar calificación.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public CognitiveExcercisesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitive_exercise_card, parent, false);
        return new ViewHolder(view);
    }

    public interface ISelectionItem {
        void clickSelect();
    }
}
