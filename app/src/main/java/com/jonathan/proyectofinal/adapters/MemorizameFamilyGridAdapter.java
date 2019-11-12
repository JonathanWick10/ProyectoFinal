package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Memorizame;

import java.util.List;

public class MemorizameFamilyGridAdapter extends RecyclerView.Adapter<MemorizameFamilyGridAdapter.MemorizameFamilyGridViewHolder> {

    //region Variables
    List<Memorizame> memorizameList;
    Context context;
    MemorizameFamilyGridAdapter.ISelectionMemorizame iSelectionMemorizame;
    MemorizameFamilyGridAdapter.IDeleteMemorizame iDeleteMemorizame;
    //endregion


    public MemorizameFamilyGridAdapter(List<Memorizame> memorizameList, Context context, ISelectionMemorizame iSelectionMemorizame, IDeleteMemorizame iDeleteMemorizame) {
        this.memorizameList = memorizameList;
        this.context = context;
        this.iSelectionMemorizame = iSelectionMemorizame;
        this.iDeleteMemorizame = iDeleteMemorizame;
    }

    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public MemorizameFamilyGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_memorizame, parent, false);
        return new MemorizameFamilyGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemorizameFamilyGridViewHolder holder, final int position) {
        //set data
        holder.setData(memorizameList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionMemorizame.clickItem(memorizameList.get(position));
            }
        });
        /*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDeleteMemorizame.clickdelete(memorizameList.get(position));
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return memorizameList.size();
    }
    //endregion



    //region ViewHolder of Recycler
    public class MemorizameFamilyGridViewHolder  extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView number;
        Memorizame item;
        View layout;

        //Reference to views
        public MemorizameFamilyGridViewHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.img_memorizame);
            number = itemView.findViewById(R.id.text_number);
        }

        //Set data to views
        public void setData(Memorizame item) {
            this.item = item;
            number.setText(item.getQuestion());
            Glide.with(context).load(item.getUriImg()).fitCenter().into(photo);
        }
    }
    //endregion


    //region Interfaces
    public interface ISelectionMemorizame {
        void clickItem(Memorizame memorizame);
    }

    public interface IDeleteMemorizame {
        void clickdelete(Memorizame memorizame);
    }
    //endregion


}
