package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MemorizameFragment extends Fragment {
    CardView family, pets, home, places;

    private IMainCarer mIMainCarer;

    public MemorizameFragment() {
    }

    public MemorizameFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame,container,false);

        family = view.findViewById(R.id.cv_family);
        pets = view.findViewById(R.id.cv_pets);
        home = view.findViewById(R.id.cv_home);
        places = view.findViewById(R.id.cv_places);

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.tab_family_questions));
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.tab_pets_questions));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.tab_home_questions));
            }
        });
        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.tab_places_questions));
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
