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
import com.jonathan.proyectofinal.data.StretchingExercise;

import java.util.List;

public class StretchingAdapter extends RecyclerView.Adapter<StretchingAdapter.StretchingViewHolder> {

    //region Variables
    List<StretchingExercise> stretchingList;
    Context context;
    StretchingAdapter.ISelectionStretching iSelectionStretching;
    //endregion


    public StretchingAdapter(List<StretchingExercise> stretchingList, Context context, ISelectionStretching iSelectionStretching) {
        this.stretchingList = stretchingList;
        this.context = context;
        this.iSelectionStretching = iSelectionStretching;
    }

    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public StretchingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_exercise_carer, parent, false);
        return new StretchingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StretchingViewHolder holder, final int position) {
        //set data
        holder.setData(stretchingList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionStretching.clickItem(stretchingList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return stretchingList.size();
    }
    //endregion



    //region ViewHolder of Recycler
    public class StretchingViewHolder  extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView number;
        StretchingExercise item;
        View layout;

        //Reference to views
        public StretchingViewHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            photo = itemView.findViewById(R.id.img_stretching);
            number = itemView.findViewById(R.id.text_name_exercise);
        }

        //Set data to views
        public void setData(StretchingExercise item) {
            this.item = item;
            number.setText(item.getNameExercise());
            Glide.with(context).load(item.getUriGif()).fitCenter().into(photo);
        }
    }
    //endregion


    //region Interfaces
    public interface ISelectionStretching {
        void clickItem(StretchingExercise stretchingExercise);
    }
    //endregion


}
