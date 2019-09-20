package com.jonathan.proyectofinal.fragments.Cu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;

public class ManageFragment extends Fragment {

    private static final String TAG = "ManageFragment";

    //vars

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ManageFragment() {
    }

    public ManageFragment(int contentLayoutId) {
        super(contentLayoutId);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_manage, container, false);
        return view;
    }

}
