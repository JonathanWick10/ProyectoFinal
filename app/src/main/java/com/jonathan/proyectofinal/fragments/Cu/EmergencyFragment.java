package com.jonathan.proyectofinal.fragments.Cu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;

public class EmergencyFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabEmergency, tabNearby;

    public EmergencyFragment() {
    }

    public EmergencyFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_emergency,container,false);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        tabEmergency = view.findViewById(R.id.Call_Emergency);
        tabNearby = view.findViewById(R.id.Nearby_Hosp);
        return view;
    }
}
