package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MensagesContent;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationManagerAdapter extends RecyclerView.Adapter<NotificationManagerAdapter.NotificationManagerHolder> {

    private List<MensagesContent> entity;

    public NotificationManagerAdapter(List<MensagesContent> entity){
        //seteo de datos
        this.entity = entity;
    }

    @NonNull
    @Override
    public NotificationManagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar nueva vista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_manager_plantilla, parent, false);
        return new NotificationManagerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationManagerHolder holder, int position) {
        //setear datos de la lista a la vista recorrida
        MensagesContent data = entity.get(position);
        holder.name.setText(data.getEs());
    }

    @Override
    public int getItemCount() { return entity.size(); }

    public class NotificationManagerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.notification_name)
        TextView name;

        public NotificationManagerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
