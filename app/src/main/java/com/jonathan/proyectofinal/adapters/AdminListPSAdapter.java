package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.HealthcareProfessional;

public class AdminListPSAdapter extends FirestoreRecyclerAdapter<HealthcareProfessional, AdminListPSAdapter.ViewHolder> {

    ISelectionHealth iSelectionHealth;
    IDeleteHealth iDeleteHealth;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminListPSAdapter(@NonNull FirestoreRecyclerOptions<HealthcareProfessional> options, ISelectionHealth iSelectionHealth, IDeleteHealth iDeleteHealth) {
        super(options);
        this.iSelectionHealth = iSelectionHealth;
        this.iDeleteHealth = iDeleteHealth;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View layout;
        public ImageView photo, delete;
        public TextView name, id, place, profession;
        HealthcareProfessional item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.admin_imagev_photops);
            name = itemView.findViewById(R.id.admin_txtv_nameps);
            id = itemView.findViewById(R.id.admin_txtv_idps);
            place = itemView.findViewById(R.id.admin_txtv_placeps);
            profession = itemView.findViewById(R.id.admin_txtv_profesionps);
            delete = itemView.findViewById(R.id.admin_imgv_delete);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminListPSAdapter.ViewHolder holder, final int position, @NonNull final HealthcareProfessional model) {
        //Get the position of the professional inside the adapter
        final DocumentSnapshot healthDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        //put the data in the views
        holder.name.setText(model.getFirstName());
        holder.id.setText(model.getIdentification());
        holder.place.setText(model.getEmployment_place());
        holder.profession.setText(model.getProfession());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iSelectionHealth.clickItem(healthDocument.toObject(HealthcareProfessional.class));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iDeleteHealth.clickdelete(healthDocument.toObject(HealthcareProfessional.class));
            }
        });

    }

    @NonNull
    @Override
    public AdminListPSAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ps_admin_plantilla, parent, false);
        return new ViewHolder(view);
    }

    //region Interfaces
    public interface ISelectionHealth {
        void clickItem(HealthcareProfessional health);
    }

    public interface IDeleteHealth {
        void clickdelete(HealthcareProfessional pojo);
    }
    //endregion
}
