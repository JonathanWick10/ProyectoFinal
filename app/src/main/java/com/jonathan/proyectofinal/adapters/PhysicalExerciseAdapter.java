package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;

import java.util.List;

public class PhysicalExerciseAdapter extends RecyclerView.Adapter<PhysicalExerciseAdapter.holder> {

    List<PhysicalExerciseEntity> listExercise;

    public PhysicalExerciseAdapter(List<PhysicalExerciseEntity> listExercise) {
        this.listExercise = listExercise;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_execise_plantilla, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        //setear datos
        holder.nameE.setText(listExercise.get(position).getNameExercise());

    }

    @Override
    public int getItemCount() {
        return listExercise.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView nameE, description;
        ImageView image;
        public int time;

        public holder(@NonNull View itemView) {
            super(itemView);

            nameE = itemView.findViewById(R.id.physical_exercise_txtv_name);
            description = itemView.findViewById(R.id.physical_exercise_txtv_description);
            image = itemView.findViewById(R.id.physical_exercise_imagev_gif);
        }
    }
}
