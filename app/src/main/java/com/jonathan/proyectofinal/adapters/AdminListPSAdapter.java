package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;

import java.util.List;

public class AdminListPSAdapter extends RecyclerView.Adapter<AdminListPSAdapter.Holder> {
    private List<String> list;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;

    public AdminListPSAdapter(List<String> list, AdminListPSAdapter.AdminListPSAdapterI adapterI) {
        this.list = list;
        this.adapterI = adapterI;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView photo, delete;
        public TextView name, id, place, profession;

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
        holder.photo.setImageResource(R.drawable.ic_launcher_background);
        holder.name.setText(list.get(position));
        holder.id.setText(list.get(position));
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
        void btnEliminar(String pojo);
    }
}
