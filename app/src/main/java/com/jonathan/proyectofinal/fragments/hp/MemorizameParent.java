package com.jonathan.proyectofinal.fragments.hp;

import android.content.Context;
import android.net.Uri;
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

    private View view;
    private Bundle args;
    private Fragment change;
    private FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

            args = bundle;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memorizame_parent, container, false);

        //incie el de cartas
        inflateFragment("memorizame");

        return view;
    }


    @Override
    public void inflateFragment(String fragmentTag) {
        transaction = getChildFragmentManager().beginTransaction();

        if (fragmentTag.equals("memorizame")) {
            change = new MemorizameFragment(this);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_family_questions))) {
            change = new MemorizameFamilyFragment(1);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_pets_questions))) {
            change = new MemorizameFamilyFragment(2);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_home_questions))) {
            change = new MemorizameFamilyFragment(3);
            change.setArguments(args);
            transaction.replace(R.id.container_memorizame_parent, change).addToBackStack(null).commit();

        } else if (fragmentTag.equals(getString(R.string.tab_places_questions))) {
            change = new MemorizameFamilyFragment(4);
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
