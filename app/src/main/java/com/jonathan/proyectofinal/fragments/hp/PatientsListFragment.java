package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientsListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PatientsAdapter adapter;


    public PatientsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
