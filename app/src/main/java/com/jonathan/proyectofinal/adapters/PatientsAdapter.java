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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.jonathan.proyectofinal.tools.Constants.Patients;

public class PatientsAdapter extends FirestoreRecyclerAdapter<Patient,PatientsAdapter.PatientViewHolder> {

    //region Variables
    List<Patient> patientList;
    Context context;
    ISelectionPatient iSelectionPatient;
    IDeletePatient iDeletePatient;
    //endregion

    //region Builder

    public PatientsAdapter(@NonNull FirestoreRecyclerOptions<Patient> options, Context context, ISelectionPatient iSelectionPatient, IDeletePatient iDeletePatient) {
        super(options);
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
    protected void onBindViewHolder(@NonNull PatientViewHolder holder, int position, @NonNull Patient model) {
        //Get the position of the professional inside the adapter
        final DocumentSnapshot patientDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        //put the data in the views
        holder.setData(patientDocument.toObject(Patient.class));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iSelectionPatient.clickItem(patientDocument.toObject(Patient.class));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iDeletePatient.clickdelete(patientDocument.toObject(Patient.class));
            }
        });
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




