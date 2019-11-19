package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memorizame_parent, container, false);

        //incie el de cartas
        inflateFragment("memorizame");

        return view;
    }


    @Override
    public void inflateFragment(String fragmentTag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        Fragment change;
        if (fragmentTag.equals("memorizame")) {
            change = new MemorizameFragment(this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_family_questions))) {
            change = new MemorizameFamilyFragment(1,this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_pets_questions))) {
            change = new MemorizameFamilyFragment(2,this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_home_questions))) {
            change = new MemorizameFamilyFragment(3,this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_places_questions))) {
            change = new MemorizameFamilyFragment(4,this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.family_questions_img))) {
            change = new NewCardMemorizame(1);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals("memorizamee")) {
            change = new NewCardMemorizame(2);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();
        }
    }
}
