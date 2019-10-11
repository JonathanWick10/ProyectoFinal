package com.jonathan.proyectofinal.fragments.hp;

import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.jonathan.proyectofinal.R;

import java.util.ArrayList;
import java.util.Map;

public class GraphPSFragment extends Fragment {

    private LineChart lineChart;

    private View view;

    private String[] days= new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"};
    private int[] scores= new int[]{10,20,25,35,50,20,40,45,60,45,50,80,85,90,90,50,60,80,95};

    public GraphPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ps_graph, container, false);
        lineChart = (LineChart)view.findViewById(R.id.graph1);
        reference();
        createCharts();
        return view;
    }

    private void reference() {
    }
    private Chart getSameChart (Chart chart, String description,int textColor, int background,int anmimateTime){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateX(anmimateTime);
                return chart;
    }

    private ArrayList<Entry>getLineEntry(){
        ArrayList<Entry>entries=new ArrayList<>();
        for (int i=0;i<scores.length;i++)
            entries.add(new Entry(i,scores[i]));
        return entries;

    }
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(days));
    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(100);
        axis.setAxisMinimum(0);
    }
    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }
    public void createCharts(){
        lineChart=(LineChart)getSameChart(lineChart,"Gráfica1", Color.RED,Color.BLUE,3000);
        lineChart.setDrawGridBackground(true);
        //lineChart.setData(getLineData());
        lineChart.invalidate();
        axisX(lineChart.getXAxis());
        axisLeft(lineChart.getAxisLeft());
        axisRight(lineChart.getAxisRight());
    }

private DataSet getData(DataSet dataSet){
        dataSet.setValueTextSize(15);
        dataSet.setValueTextSize(Color.WHITE);

        return dataSet;
}

    private LineData getLineData(){

        LineDataSet lineDataSet=(LineDataSet)lineChart.getData().getDataSetByIndex(0);
        LineData lineData= new LineData(lineDataSet);
        lineDataSet.setValues(getLineEntry());
        return lineData;
    }

}
