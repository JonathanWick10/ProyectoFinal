package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Memorizame;

import java.util.List;

public class PatientMemorizameAdapter extends RecyclerView.Adapter<PatientMemorizameAdapter.PatientMemorizameAdapterHolder> {

    //region Variables
    List<Memorizame> memorizameList;
    Context context;
    PatientMemorizameAdapter.ISelectionMemorizame iSelectionMemorizame;
    //endregion

    //region Builds

    public PatientMemorizameAdapter(List<Memorizame> memorizameList, Context context, ISelectionMemorizame iSelectionMemorizame) {
        this.memorizameList = memorizameList;
        this.context = context;
        this.iSelectionMemorizame = iSelectionMemorizame;
    }

    //endregion

    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public PatientMemorizameAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memorizame_recycler, parent, false);
        return new PatientMemorizameAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientMemorizameAdapterHolder holder, final int position) {
        //set data
        holder.setData(memorizameList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionMemorizame.clickItem(memorizameList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return memorizameList.size();
    }
    //endregion


    //region Class Holder
    public class PatientMemorizameAdapterHolder  extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView number;
        Memorizame item;
        View layout;

        //Reference to views
        public PatientMemorizameAdapterHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.img_memorizame_patient);
            number = itemView.findViewById(R.id.text_number_patient);
        }

        //Set data to views
        public void setData(Memorizame item) {
            this.item = item;
            number.setText("Empieza");
            Glide.with(context).load(item.getUriImg()).fitCenter().into(photo);
        }
    }
    //endregion

    //region Interfaces
    public interface ISelectionMemorizame {
        void clickItem(Memorizame memorizame);
    }
    //endregion
}
