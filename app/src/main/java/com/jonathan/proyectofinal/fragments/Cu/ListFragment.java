package com.jonathan.proyectofinal.fragments.Cu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;

public class ListFragment extends Fragment {
    public ListFragment() {
    }

    public ListFragment(int contentLayoutId) { super(contentLayoutId); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_list,container,false);
        return view;
    }
}
