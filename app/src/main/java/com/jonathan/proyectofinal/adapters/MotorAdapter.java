package com.jonathan.proyectofinal.adapters;

import android.app.assist.AssistStructure;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import com.jonathan.proyectofinal.data.MotorExcercises;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MotorAdapter extends FirestoreRecyclerAdapter<MotorExcercises, MotorAdapter.ViewHolder> {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Context context;
    String uid;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MotorAdapter(@NonNull FirestoreRecyclerOptions<MotorExcercises> options, Context context, String uid) {
        super(options);
        this.context = context;
        this.uid = uid;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View layout;
        @BindView(R.id.cardMotor)
        MaterialCardView cardMotor;
        @BindView(R.id.iv_motor)
        ImageView iv_motor;
        @BindView(R.id.tv_motor_name)
        TextView tv_motor_name;
        @BindView(R.id.tv_motor_description)
        TextView tv_motor_description;
        /*
        @BindView(R.id.tv_motor_long_description)
        TextView tv_motor_long_description;
        @BindView(R.id.expandableMotorView)
        LinearLayout expandableMotorView;
        @BindView(R.id.btn_show_hide_ld)
        MaterialButton btn_show_hide;
        */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull final MotorAdapter.ViewHolder holder, int position, @NonNull MotorExcercises model) {
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        final DocumentSnapshot excerciseDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String idDoc = getSnapshots().getSnapshot(holder.getAdapterPosition()).getId();

        Glide.with(context).load(model.getUriGifExcercise()).fitCenter().into(holder.iv_motor);
        holder.tv_motor_name.setText(model.getNameExcercise());
        holder.tv_motor_description.setText(model.getDescriptionExcercise());
        //holder.tv_motor_long_description.setText(model.getLongDescriptionExcercise());
        final MaterialCardView cardMotor = holder.cardMotor;
        //final LinearLayout expandableMotorView = holder.expandableMotorView;
        //final MaterialButton btn_show_hide = holder.btn_show_hide;

        holder.cardMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardMotor.toggle();
            }
        });

        holder.cardMotor.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "Asignado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sin asignar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        btn_show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iSelectItemMedicaments.clickSelect(medicamentDocument.toObject(MedicationAssignment.class));
                if (expandableMotorView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardMotor, new AutoTransition());
                    expandableMotorView.setVisibility(View.VISIBLE);
                    btn_show_hide.setText(R.string.btn_hide_info);
                    btn_show_hide.setWidth(240);
                } else {
                    TransitionManager.beginDelayedTransition(cardMotor, new AutoTransition());
                    expandableMotorView.setVisibility(View.GONE);
                    btn_show_hide.setText(R.string.btn_show_info);
                }
            }
        });
        */
    }

    @NonNull
    @Override
    public MotorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motor_card, parent, false);
        return new ViewHolder(view);
    }
}
