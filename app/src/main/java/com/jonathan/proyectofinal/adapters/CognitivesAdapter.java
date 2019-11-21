package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.CognitivesExcercises;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CognitivesAdapter extends FirestoreRecyclerAdapter<CognitivesExcercises, CognitivesAdapter.ViewHolder> {

    Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *4
     * @param options
     */
    public CognitivesAdapter(@NonNull FirestoreRecyclerOptions<CognitivesExcercises> options, Context context) {
        super(options);
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View layout;
        @BindView(R.id.cardCognitive)
        MaterialCardView cardCognitive;
        @BindView(R.id.iv_cognitive)
        ImageView iv_cognitive;
        @BindView(R.id.tv_cognitive_name)
        TextView tv_cognitive_name;
        @BindView(R.id.tv_cognitive_description)
        TextView tv_cognitive_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final CognitivesAdapter.ViewHolder holder, int position, @NonNull CognitivesExcercises model) {
        Glide.with(context).load(model.getUriImageExcercise()).fitCenter().into(holder.iv_cognitive);
        holder.tv_cognitive_name.setText(model.getNameExcercise());
        holder.tv_cognitive_description.setText(model.getDescriptionExcercise());

        holder.cardCognitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardCognitive.toggle();
            }
        });


        holder.cardCognitive.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(context, "Marcado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sin marcar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @NonNull
    @Override
    public CognitivesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitives_card, parent, false);
        return new ViewHolder(view);
    }

}
