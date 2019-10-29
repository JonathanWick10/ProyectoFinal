package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;

import java.util.List;

public class MemorizameFamilyGridAdapter extends RecyclerView.Adapter<MemorizameFamilyGridAdapter.MemorizameFamilyGridViewHolder> {
    //extends RecyclerView.Adapter<MemorizameFamilyGridAdapter.Holder>

    //region Variables
    List<Memorizame> memorizameList;
    Context context;
    MemorizameFamilyGridAdapter.ISelectionPatient iSelectionPatient;
    MemorizameFamilyGridAdapter.IDeletePatient iDeletePatient;
    //endregion



    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public MemorizameFamilyGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_memorizame, parent, false);
        return new MemorizameFamilyGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemorizameFamilyGridViewHolder holder, final int position) {
        //set data
        holder.setData(memorizameList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionPatient.clickItem(memorizameList.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDeletePatient.clickdelete(memorizameList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return memorizameList.size();
    }
    //endregion



    //region ViewHolder of Recycler
    public class MemorizameFamilyGridViewHolder  extends RecyclerView.ViewHolder {

        ImageView photo, delete;
        TextView name, identification;
        Memorizame item;
        View layout;

        //Reference to views
        public MemorizameFamilyGridViewHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.imagePatient);
            delete = itemView.findViewById(R.id.btn_delete);
            name = itemView.findViewById(R.id.tvPatientName);
            identification = itemView.findViewById(R.id.tvPatientId);
        }

        //Set data to views
        public void setData(Memorizame item) {
            this.item = item;
            name.setText(item.getQuestion());
            identification.setText(item.getCorrectAnswer());
        }
    }
    //endregion


    //region Interfaces
    public interface ISelectionPatient {
        void clickItem(Memorizame patient);
    }

    public interface IDeletePatient {
        void clickdelete(Memorizame patient);
    }
    //endregion


}
