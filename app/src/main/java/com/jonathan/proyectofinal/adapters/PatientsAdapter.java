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

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientViewHolder> {

    //region Código Carolina
    /*List<Patient> listPatients;
    interDelete clickDelete;

    private Context context;
    private IOnPatientClickListener onPatientClickListener;

    public interface interDelete{
        void clickItem(Patient item);
    }
   public PatientsAdapter(List<Patient> listPatients, interDelete clickDelete) {
        this.listPatients = listPatients;
        this.clickDelete = clickDelete;
    }
    public PatientsAdapter(Context context, IOnPatientClickListener onPatientClickListener) {
        this.context = context;
        this.onPatientClickListener = onPatientClickListener;
    }

    public  static class PatientViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name, id;
        ImageView imagePatient, imageDelete;




        public PatientViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cv_list_patient);
            name=itemView.findViewById(R.id.tvPatientName);
            id=itemView.findViewById(R.id.tvPatientId);
            imagePatient=itemView.findViewById(R.id.imagePatient);
            imageDelete=itemView.findViewById(R.id.btn_delete);

        }}

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_list_patients, parent, false);
            PatientViewHolder patientViewHolder= new PatientViewHolder(view);
            return patientViewHolder;
        }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, final int position) {

        Patient patientholder=listPatients.get(position);
        holder.name.setText(patientholder.getFirstName()); //Review (long)
       // holder.imagePatient.setImageResource(patientholder.id);
        holder.imageDelete.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {
                clickDelete.clickItem(listPatients.get(position));
            }
        });

    }
    public  void addAll(List<Patient> patients){
        listPatients.clear();
        listPatients.addAll(patients);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listPatients.size();
    }*/
    //endregion

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

        ImageView photo, delete;
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




