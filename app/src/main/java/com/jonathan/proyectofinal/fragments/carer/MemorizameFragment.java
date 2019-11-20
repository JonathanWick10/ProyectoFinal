package com.jonathan.proyectofinal.fragments.carer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;

public class MemorizameFragment extends Fragment {
    CardView family, pets, home, places;
    Patient patientSendFragment = new Patient();
    Bundle args;

    public MemorizameFragment(Bundle args) {
        this.args = args;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame,container,false);


        args = getArguments();
        if (args != null) {
            patientSendFragment = (Patient) args.getSerializable("patient");
        }

        family = view.findViewById(R.id.cv_family);
        pets = view.findViewById(R.id.cv_pets);
        home = view.findViewById(R.id.cv_home);
        places = view.findViewById(R.id.cv_places);

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                Fragment change;
                change = new MemorizameFamilyFragment(1);
                change.setArguments(args);
                transaction.replace(R.id.container_memorizame_parent,change).addToBackStack(null).commit();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                Fragment change;
                change = new MemorizameFamilyFragment(2);
                change.setArguments(args);
                transaction.replace(R.id.container_memorizame_parent,change).addToBackStack(null).commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                Fragment change;
                change = new MemorizameFamilyFragment(3);
                change.setArguments(args);
                transaction.replace(R.id.container_memorizame_parent,change).addToBackStack(null).commit();
            }
        });
        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                Fragment change;
                change = new MemorizameFamilyFragment(4);
                change.setArguments(args);
                transaction.replace(R.id.container_memorizame_parent,change).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
