package com.jonathan.proyectofinal.fragments.carer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemorizameFamilyFragmentGrid extends Fragment {

    public MemorizameFamilyFragmentGrid(){}

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

  //  private MemorizameFamilyGridAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memorizame_grid,container,false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();

        return view;
    }
    private void initAdapter() {
        /*
        if (adapter == null){
            adapter = new TopArticlesAdapter(getActivity().getApplicationContext(), this);
        }

         */
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas
      //  recyclerView.setAdapter(adapter);
    }


}
