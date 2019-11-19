package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.StretchingExercise;

import java.util.List;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.GraphViewHolder> {

    //region Variables
    List<StretchingExercise> stretchingList;
    Context context;
    GraphAdapter.ISelectionGraph iSelectionGraph;
    //endregion


    public GraphAdapter(List<StretchingExercise> stretchingList, Context context, ISelectionGraph iSelectionGraph) {
        this.stretchingList = stretchingList;
        this.context = context;
        this.iSelectionGraph = iSelectionGraph;
    }

    //region Overwritten methods of RecyclerView
    @NonNull
    @Override
    public GraphViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_graphs, parent, false);
        return new GraphViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GraphViewHolder holder, final int position) {
        //set data
        holder.setData(stretchingList.get(position));
        //events onclick
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectionGraph.clickItem(stretchingList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return stretchingList.size();
    }
    //endregion



    //region ViewHolder of Recycler
    public class GraphViewHolder  extends RecyclerView.ViewHolder {

        Button arrow;
        TextView number;
        StretchingExercise item;
        View layout;

        //Reference to views
        public GraphViewHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            arrow = itemView.findViewById(R.id.btnExpandMotor);
            number = itemView.findViewById(R.id.text_name_exercise);
        }

        //Set data to views
        public void setData(StretchingExercise item) {
            this.item = item;
            number.setText(item.getNameExercise());
        }
    }
    //endregion


    //region Interfaces
    public interface ISelectionGraph {
        void clickItem(StretchingExercise stretchingExercise);
    }
    //endregion


}
