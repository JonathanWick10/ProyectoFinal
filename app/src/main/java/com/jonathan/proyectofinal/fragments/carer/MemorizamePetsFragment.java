package com.jonathan.proyectofinal.fragments.carer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;

public class MemorizamePetsFragment extends Fragment {

    public MemorizamePetsFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame_pets,container,false);
        return view;
    }
}
