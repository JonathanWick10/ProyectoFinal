package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.mikephil.charting.charts.LineChart;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;

import java.util.List;

public class GraphAdapter extends FirestoreRecyclerAdapter<CognitiveExcercisesAssignment, GraphAdapter.GraphViewHolder> {

    //region Variables
    Context context;
    GraphAdapter.ISelectItem iSelectItem;
    //endregion

    //region Build
    public GraphAdapter(@NonNull FirestoreRecyclerOptions options, Context context, ISelectItem iSelectItem) {
        super(options);
        this.context = context;
        this.iSelectItem = iSelectItem;
    }
    //endregion

    //region Override
    @NonNull
    @Override
    public GraphViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_graphs,parent,false);

        return new GraphViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final GraphViewHolder holder, int position, @NonNull CognitiveExcercisesAssignment model) {

        //Get the position of the professional inside the adapter
        final DocumentSnapshot exerciseCognitiveDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());



        holder.setData(exerciseCognitiveDocument.toObject(CognitiveExcercisesAssignment.class));
        holder.nameExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.graphRecycler.getVisibility() == View.GONE){
                    holder.graphRecycler.setVisibility(View.VISIBLE);
                    holder.separator.setVisibility(View.VISIBLE);

                    holder.seeMore.setImageResource(R.drawable.ic_keyboard_arrow_down_black);
                    //btn_show_hide.setText(R.string.btn_hide_info);
                    //btn_show_hide.setWidth(240);
                } else {
                    holder.graphRecycler.setVisibility(View.GONE);
                    holder.separator.setVisibility(View.GONE);

                    holder.seeMore.setImageResource(R.drawable.ic_keyboard_arrow_up_black);
                    //btn_show_hide.setText(R.string.btn_show_info);
                }
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectItem.clickSelectItem(exerciseCognitiveDocument.toObject(CognitiveExcercisesAssignment.class));

            }
        });
    }
    //endregion

    //region View Holder
    public class GraphViewHolder extends RecyclerView.ViewHolder{
        ImageView seeMore;
        TextView nameExercise;
        TextView separator;
        CognitiveExcercisesAssignment item;
        View layout;
        LineChart graphRecycler;

        public GraphViewHolder(@NonNull View itemView) {
            super(itemView);
            seeMore=itemView.findViewById(R.id.btnExpandCard);
            nameExercise=itemView.findViewById(R.id.text_name_exercise);
            separator=itemView.findViewById(R.id.separator);
            graphRecycler=itemView.findViewById(R.id.graph1);
            layout=itemView;
        }

        //Set data to views
        public void setData(CognitiveExcercisesAssignment item) {
            this.item = item;
            nameExercise.setText(item.getNameExcercise());
            graphRecycler.setVisibility(View.GONE);
        }
    }
    //endregion

    //region Interface
    public interface ISelectItem{
        void clickSelectItem(CognitiveExcercisesAssignment cognitiveExcercisesAssignment);
    }
    //endregion

}
