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

import java.util.HashMap;
import java.util.List;

public class MemoramaAdapter extends RecyclerView.Adapter<MemoramaAdapter.Holder> {

    private List<MemoramaEntity> memoramaEntities;
    private MemoramaEntity itemSelectedSave;
    private MemoramaAdapterI adapterI;
    private HashMap<String, Integer> data;

    public MemoramaAdapter(List<MemoramaEntity> memoramaEntities, MemoramaAdapterI adapterI, HashMap<String, Integer> data) {
        this.memoramaEntities = memoramaEntities;
        this.adapterI = adapterI;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorama_plantilla, parent, false);

        view.getLayoutParams().height= data.get("height");
        view.getLayoutParams().width= data.get("width");

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        // setear datos
        final MemoramaEntity entity = memoramaEntities.get(position);

        if(entity.isShow()) {
            Picasso.get().load(entity.getImage()).into(holder.imgv);
        } else {
            holder.imgv.setImageResource(R.drawable.ic_imagotipo);
        }

        //Crear verificación de si quedan imagenes por mostrar
        if (entity.isClickeable()) {

            //revisar si es la primera vez
            holder.imgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //valida si ya escogio otro elemento
                    if (itemSelectedSave == null) {

                        memoramaEntities.get(position).setShow(true);
                        memoramaEntities.get(position).setClickeable(false);
                        memoramaEntities.get(position).setFound(false);

                        itemSelectedSave = entity;
                        notifyItemChanged(position);

                    } else {
                        //si hay uno guardado diferente del actual
                        if (itemSelectedSave.getImageGrup() == entity.getImageGrup() && itemSelectedSave != entity) {
                            memoramaEntities.get(position).setShow(true);
                            memoramaEntities.get(position).setClickeable(false);
                            memoramaEntities.get(position).setFound(true);

                            notifyItemChanged(position);

                            int posisave=  itemSelectedSave.getPosition();
                            memoramaEntities.get(posisave).setShow(true);
                            memoramaEntities.get(posisave).setClickeable(false);
                            memoramaEntities.get(posisave).setFound(true);

                            notifyItemChanged(posisave);

                            adapterI.updateAllItems();
                            adapterI.showMsm("Correcto");


                        }else{
                            //no son iguales
                            memoramaEntities.get(position).setShow(true);
                            memoramaEntities.get(position).setClickeable(false);
                            memoramaEntities.get(position).setFound(false);

                            notifyItemChanged(position);

                            int posisave=  itemSelectedSave.getPosition();
                            memoramaEntities.get(posisave).setShow(true);
                            memoramaEntities.get(posisave).setClickeable(false);
                            memoramaEntities.get(posisave).setFound(false);

                            notifyItemChanged(posisave);

                            adapterI.updateAllItems();
                            adapterI.showMsm("Incorrecto");
                        }

                        itemSelectedSave = null;
                    }
                }
            });

        } else {
            //todos los datos estan encontrados Reiniciar o menu
            holder.imgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return memoramaEntities.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imgv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgv = itemView.findViewById(R.id.memorama_img_plantilla);

        }
    }

    public interface MemoramaAdapterI {
        void updateAllItems();
        void showMsm(String mensaje);
    }
}
