package com.jonathan.proyectofinal.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MemoramaEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MemoramaAdapter extends RecyclerView.Adapter<MemoramaAdapter.Holder> {

    List<MemoramaEntity> memoramaEntities ;

    public MemoramaAdapter(List<MemoramaEntity> memoramaEntities) {
        this.memoramaEntities = memoramaEntities;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorama_plantilla,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        // setear datos
        final MemoramaEntity entity = memoramaEntities.get(position);


        if (entity.isShow()){
            Picasso.get().load(entity.getImage()).into(holder.imgv);
        }else {
            Picasso.get().load("https://hackster.imgix.net/uploads/attachments/237594/UyXxTjj4uiMVLsh02Jmp.uploads/tmp/32abfd31-ac35-430d-8faf-625cd4e8bd64/tmp_image_0?auto=compress%2Cformat&w=900&h=675&fit=min").into(holder.imgv);
        }

        holder.imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setShow(!(entity.isShow()));
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memoramaEntities.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView imgv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imgv = itemView.findViewById(R.id.memorama_img_plantilla);

        }
    }
}
