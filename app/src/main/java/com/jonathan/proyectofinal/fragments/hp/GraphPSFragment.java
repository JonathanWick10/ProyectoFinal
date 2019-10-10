package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.jonathan.proyectofinal.R;

public class GraphPSFragment extends Fragment {

    private LineChart lineChart;

    private View view;

    public GraphPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ps_graph, container, false);
        lineChart = (LineChart)view.findViewById(R.id.graph1);
        reference();
        return view;
    }

    private void reference() {
    }

}
