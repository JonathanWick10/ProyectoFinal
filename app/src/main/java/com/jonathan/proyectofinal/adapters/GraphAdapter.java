package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.primitives.Ints;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.CognitiveExcercisesAssignment;
import com.jonathan.proyectofinal.data.ScoreGame;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphAdapter extends FirestoreRecyclerAdapter<CognitiveExcercisesAssignment, GraphAdapter.GraphViewHolder> {

    //region Variables
    Context context;
    GraphAdapter.ISelectItem iSelectItem;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //endregion

    List<Integer> scoresGraph;
    ScoreGame scoreGame = new ScoreGame();


    ArrayList<String> intentos = new ArrayList<>();
    private LineChart lineChart;

    private View view;

    private String[] days = new String[]{};
    private int[] scores = new int[]{};






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

        lineChart = (LineChart) view.findViewById(R.id.graph1);



        return new GraphViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final GraphViewHolder holder, int position, @NonNull CognitiveExcercisesAssignment model) {

        //Get the position of the professional inside the adapter
        final DocumentSnapshot exerciseCognitiveDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        db.collection(Constants.scoreGames).document(model.getUidPatient())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    scoreGame = documentSnapshot.toObject(ScoreGame.class);
                    scoresGraph = scoreGame.getGameMemoramaScore();
                    createCharts();
                }
            }
        });

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



    private Chart getSameChart(Chart chart, String description, int textColor, int background, int anmimateTime) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateX(anmimateTime);
        return chart;
    }

    private ArrayList<Entry> getLineEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < scores.length; i++)
            entries.add(new Entry(i, scores[i]));
        return entries;

    }

    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(intentos));
    }

    private void axisLeft(YAxis axis) {
        axis.setAxisMaximum(100);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) {
        axis.setEnabled(false);
    }

    public void createCharts() {
        int[] score = Ints.toArray(scoresGraph);
        for(int i = 1; i <= score.length; i++){
            intentos.add(String.valueOf(i));
        }
        scores=score;

        lineChart = (LineChart) getSameChart(lineChart, "", Color.RED, Color.WHITE, 2500);
        lineChart.setDrawGridBackground(true);
        lineChart.setData(getLineData());
        lineChart.invalidate();
        axisX(lineChart.getXAxis());
        axisLeft(lineChart.getAxisLeft());
        axisRight(lineChart.getAxisRight());
    }

    private DataSet getData(DataSet dataSet) {
        dataSet.setValueTextSize(15);
        dataSet.setValueTextSize(Color.WHITE);
        return dataSet;
    }

    private LineData getLineData() {

        LineDataSet lineDataSet = (LineDataSet) getData(new LineDataSet(getLineEntries(), ""));
        LineData lineData = new LineData(lineDataSet);
        return lineData;
    }

}
