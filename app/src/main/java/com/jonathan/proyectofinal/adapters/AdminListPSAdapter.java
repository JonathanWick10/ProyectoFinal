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
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.HealthcareProfessional;

import java.util.List;

public class AdminListPSAdapter extends RecyclerView.Adapter<AdminListPSAdapter.Holder> {
    Context context;
    private List<HealthcareProfessional> list;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;


    public AdminListPSAdapter(Context context,List<HealthcareProfessional> list, AdminListPSAdapter.AdminListPSAdapterI adapterI) {
        this.context = context;
        this.list = list;
        this.adapterI = adapterI;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView photo, delete;
        public TextView name, id, place, profession;
        HealthcareProfessional item;


        public Holder(@NonNull View itemView) {
            super(itemView);
            // encontrar los elementos que estan dentro de la plantilla
            photo = itemView.findViewById(R.id.admin_imagev_photops);
            name = itemView.findViewById(R.id.admin_txtv_nameps);
            id = itemView.findViewById(R.id.admin_txtv_idps);
            place = itemView.findViewById(R.id.admin_txtv_placeps);
            profession = itemView.findViewById(R.id.admin_txtv_profesionps);
            delete = itemView.findViewById(R.id.admin_imgv_delete);
        }

        public void setData(HealthcareProfessional item){
            this.item = item;
            name.setText(item.getFirstName()+" "+item.getLastName());
            id.setText(item.getIdentification());
            place.setText(item.getEmployment_place());
            profession.setText(item.getProfession());

        }
    }

    @NonNull
    @Override
    public AdminListPSAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ps_admin_plantilla, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminListPSAdapter.Holder holder, final int position) {
        //setear
        holder.setData(list.get(position));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterI.btnEliminar(list.get(position));
            }
        });
        // delete alert

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface AdminListPSAdapterI {
        void btnEliminar(HealthcareProfessional pojo);
    }
}
