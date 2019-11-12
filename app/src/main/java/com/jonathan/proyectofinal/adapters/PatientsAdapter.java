package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientViewHolder> {

    //region Variables
    List<Patient> patientList;
    Context context;
    ISelectionPatient iSelectionPatient;
    IDeletePatient iDeletePatient;
    //endregion

    //region Builder
    public PatientsAdapter(List<Patient> patientList, Context context, ISelectionPatient iSelectionPatient, IDeletePatient iDeletePatient) {
        this.patientList = patientList;
        this.context = context;
        this.iSelectionPatient = iSelectionPatient;
        this.iDeletePatient = iDeletePatient;
    }
    //endregion

    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_list_patients, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, final int position) {
        //set data
        holder.setData(patientList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionPatient.clickItem(patientList.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDeletePatient.clickdelete(patientList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
    //endregion

    //region ViewHolder of Recycler
    public class PatientViewHolder extends RecyclerView.ViewHolder {

        CircleImageView photo;
        ImageView delete;
        TextView name, identification;
        Patient item;
        View layout;

        //Reference to views
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.imagePatient);
            delete = itemView.findViewById(R.id.btn_delete);
            name = itemView.findViewById(R.id.tvPatientName);
            identification = itemView.findViewById(R.id.tvPatientId);
        }

        //Set data to views
        public void setData(Patient item) {
            this.item = item;
            Glide.with(context).load(item.getUriImg()).fitCenter().into(photo);
            name.setText(item.getFirstName());
            identification.setText(item.getIdentification());
        }
    }
    //endregion

    //region Interfaces
    public interface ISelectionPatient {
        void clickItem(Patient patient);
    }

    public interface IDeletePatient {
        void clickdelete(Patient patient);
    }
    //endregion

}




