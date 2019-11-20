package com.jonathan.proyectofinal.fragments.hp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFamilyFragment;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.fragments.carer.NewCardMemorizame;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MemorizameParent extends Fragment implements IMainCarer {

    private Bundle args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        Patient patientSendFragment;
        args = getActivity().getIntent().getExtras();
        if (args!= null){
            patientSendFragment = (Patient) args.getSerializable("patient");
            args.putSerializable("patient",patientSendFragment);

            SharedPreferences preferences = getActivity().getPreferences(0);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(patientSendFragment);
            editor.putString("serialipatient",json);
            editor.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memorizame_parent, container, false);

        //incie el de cartas
        FragmentManager manager = getChildFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        Fragment change;
        change = new MemorizameFragment(args);
        change.setArguments(args);
        transaction.replace(R.id.container_memorizame_parent,change).commit();
        //inflateFragment("memorizame");

        return view;
    }


    @Override
    public void inflateFragment(String fragmentTag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        Fragment change;
        if (fragmentTag.equals("memorizamepru")) {
            change = new MemorizameFragment(args);
            transaction.replace(R.id.container_memorizame_parent, change).commit();
        }
    }
}
