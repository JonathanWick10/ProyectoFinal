package com.jonathan.proyectofinal.fragments.carer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;

import butterknife.ButterKnife;

public class NewCardMemorizame extends Fragment {
    public NewCardMemorizame(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.new_card_memorizame,container,false);
        ButterKnife.bind(this, view);

        return view;
    }
}
