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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MedicationAssignment;

public class MedicinesAdapter extends FirestoreRecyclerAdapter<MedicationAssignment, MedicinesAdapter.MedicinesViewHolder> {

    //region Variables
    Context context;
    MedicinesAdapter.IMedicinesClick iMedicinesClick;
    //endregion

    //region Build
    public MedicinesAdapter(@NonNull FirestoreRecyclerOptions<MedicationAssignment> options,Context context, IMedicinesClick iMedicinesClick) {
        super(options);
        this.iMedicinesClick = iMedicinesClick;
        this.context = context;
    }
    //endregion

    @Override
    protected void onBindViewHolder(@NonNull MedicinesViewHolder holder, int position, @NonNull MedicationAssignment model) {
        //Get the position of the professional inside the adapter
        final DocumentSnapshot medicineDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());


        holder.setData(medicineDocument.toObject(MedicationAssignment.class));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMedicinesClick.clickSelectedItem(medicineDocument.toObject(MedicationAssignment.class));
            }
        });

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMedicinesClick.clickDeleteItem(medicineDocument.toObject(MedicationAssignment.class));
            }
        });
    }

    @NonNull
    @Override
    public MedicinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
        return new MedicinesViewHolder(view);
    }

    //region View Holder
    public class MedicinesViewHolder extends RecyclerView.ViewHolder{

        //Views
        View layout;
        MedicationAssignment item;
        TextView txtMediName, txtMediDescription;
        ImageView imgMedicine,imageDelete;

        public MedicinesViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtMediName = itemView.findViewById(R.id.txt_medicine_name);
            txtMediDescription = itemView.findViewById(R.id.txt_medicine_description);
            imageDelete = itemView.findViewById(R.id.img_delete_medicine);
            imgMedicine = itemView.findViewById(R.id.iv_medicine_miniature);
        }
        //Set data to views
        public void setData(MedicationAssignment item) {
            this.item = item;
            txtMediName.setText(item.getMedicamentName());
            txtMediDescription.setText(item.getMedicamentDescription());
            Glide.with(context).load(item.getUriImg()).centerCrop().into(imgMedicine);
        }
    }
    //endregion

    //region Interface
    public interface IMedicinesClick{
        void clickSelectedItem(MedicationAssignment medicationAssignment);
        void clickDeleteItem(MedicationAssignment medicationAssignment);
    }
    //endregion
}
