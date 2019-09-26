package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientViewHolder> {

    List<Patient> listPatients;



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
        holder.name.setText(patientholder.getFirstName());
        holder.id.setText(patientholder.getAge());   //Review (long)
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
    }


    }




