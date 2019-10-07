package com.jonathan.proyectofinal.adapters;


import android.os.Handler;
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

    private List<MemoramaEntity> memoramaEntities;
    private MemoramaEntity itemSelectedSave;
    private MemoramaAdapterI adapterI;

    public MemoramaAdapter(List<MemoramaEntity> memoramaEntities, MemoramaAdapterI adapterI) {
        this.memoramaEntities = memoramaEntities;
        this.adapterI = adapterI;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorama_plantilla, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        // setear datos
        final MemoramaEntity entity = memoramaEntities.get(position);


        if (entity.isShow()) {
            Picasso.get().load(entity.getImage()).into(holder.imgv);
        } else {
            Picasso.get().load("https://hackster.imgix.net/uploads/attachments/237594/UyXxTjj4uiMVLsh02Jmp.uploads/tmp/32abfd31-ac35-430d-8faf-625cd4e8bd64/tmp_image_0?auto=compress%2Cformat&w=900&h=675&fit=min").into(holder.imgv);
        }

        if (entity.isClickeable()) {
            holder.imgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (itemSelectedSave == null) {
                        itemSelectedSave = entity;
                        //todos bien, acerto las imagenes
                        memoramaEntities.get(position).setShow(true);

                        //update two elemetns
                        notifyItemChanged(position);

                    } else {
                        //elemtn after
                        final int posiElem = itemSelectedSave.getPosition();

                        if (itemSelectedSave.getImageGrup() == entity.getImageGrup() && posiElem != position) {

                            //set show two elements
                            memoramaEntities.get(posiElem).setShow(true);
                            memoramaEntities.get(position).setShow(true);

                            //todos bien, acerto las imagenes
                            memoramaEntities.get(position).setClickeable(false);
                            memoramaEntities.get(posiElem).setClickeable(false);

                            //update two elemetns
                            notifyItemChanged(position);
                            notifyItemChanged(posiElem);

                            //call to interface
                            adapterI.clickItem("Muy bien");
                        } else {
                            //set show two elements
                            memoramaEntities.get(position).setShow(true);

                            //update two elemetns
                            notifyItemChanged(position);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //set show two elements
                                    memoramaEntities.get(posiElem).setShow(false);
                                    memoramaEntities.get(position).setShow(false);

                                    //update two elemetns
                                    notifyItemChanged(position);
                                    notifyItemChanged(posiElem);
                                }
                            }, 3000);

                            //call to interface
                            adapterI.clickItem("Muy mal");
                        }

                        itemSelectedSave = null;
                    }
                }
            });
        } else {
            //elimina el click listener
            holder.imgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterI.clickItem("Ya no se puede dar click");
                }
            });
        }
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

    public interface MemoramaAdapterI {
        void clickItem(String mensaje);
    }
}
