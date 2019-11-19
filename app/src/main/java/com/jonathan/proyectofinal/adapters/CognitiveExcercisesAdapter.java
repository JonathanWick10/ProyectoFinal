package com.jonathan.proyectofinal.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.interfaces.IComunicateFragment;
import com.jonathan.proyectofinal.ui.Games;

import org.w3c.dom.Text;

public class CognitiveExcercisesAdapter extends FirestoreRecyclerAdapter<CognitiveExcercisesAssignment, CognitiveExcercisesAdapter.ViewHolder> {

    IComunicateFragment interfaceComunicateFragments;
    Activity activity;
    CognitiveExcercisesAdapter.ISelectionItem iSelectionItem;
    Context context;

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
        public ImageView ivImage;
        public TextView tvName, tvDescription, tvLevel, tvScore, tvStatement, tvRating;
        public RatingBar ratingBar;
        public MaterialCardView cardExcercise;
        public Button btnExpand;
        public LinearLayout expandableView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ivImage = itemView.findViewById(R.id.iv_cognitive_miniature);
            tvName = itemView.findViewById(R.id.data_cognitive_name);
            tvDescription = itemView.findViewById(R.id.data_cognitive_description);
            tvLevel = itemView.findViewById(R.id.data_cognitive_level);
            tvScore = itemView.findViewById(R.id.data_cognitive_best_score);
            tvStatement = itemView.findViewById(R.id.data_cognitive_statement);
            tvRating = itemView.findViewById(R.id.data_cognitive_rating);
            ratingBar = itemView.findViewById(R.id.cognitiveRatingBar);
            cardExcercise = itemView.findViewById(R.id.cardCognitiveExcercise);
            btnExpand = itemView.findViewById(R.id.btnCognitiveExpand);
            expandableView = itemView.findViewById(R.id.expandableCognitiveView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final CognitiveExcercisesAdapter.ViewHolder holder, int position, @NonNull CognitiveExcercisesAssignment model) {
        //final DocumentSnapshot cognitiveDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        //put the data in the views
        Glide.with(context).load(model.getUriImageExcercise()).fitCenter().into(holder.ivImage);
        holder.tvName.setText(model.getNameExcercise());
        holder.tvDescription.setText(model.getDescriptionExcercise());
        holder.tvLevel.setText(String.valueOf(model.getLevel()));
        holder.tvScore.setText(String.valueOf(model.getBestScore()));
        holder.tvStatement.setText(model.getStatement());
        holder.tvRating.setText(String.valueOf(model.getRating()));
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        //ratingTxt.setText("Very bad");
                        holder.tvRating.setText("1");
                        break;
                    case 2:
                        //ratingTxt.setText("Need some improvement");
                        holder.tvRating.setText("2");
                        break;
                    case 3:
                        //ratingTxt.setText("Good");
                        holder.tvRating.setText("3");
                        break;
                    case 4:
                        //ratingTxt.setText("Great");
                        holder.tvRating.setText("4");
                        break;
                    case 5:
                        //ratingTxt.setText("Awesome. I love it");
                        holder.tvRating.setText("5");
                        break;
                    default:
                        holder.tvRating.setText("");
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
                if (holder.expandableView.getVisibility() == View.GONE){
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

    @NonNull
    @Override
    public CognitiveExcercisesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitive_exercise_card, parent, false);
        return new ViewHolder(view);
    }

    public interface ISelectionItem{
        void clickSelect();
    }
}
