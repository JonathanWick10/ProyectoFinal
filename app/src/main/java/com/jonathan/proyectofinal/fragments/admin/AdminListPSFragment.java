package com.jonathan.proyectofinal.fragments.admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminListPSFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView ;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;

    public AdminListPSFragment (AdminListPSAdapter.AdminListPSAdapterI adapterI){
        this.adapterI = adapterI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        reference();

        return view;
    }

    private void reference() {
        recyclerView = view.findViewById(R.id.admin_rv_list);
        List<String> list= new ArrayList<>();
        list.add("Data 1");
        list.add("Data 2");
        list.add("Data 3");
        list.add("Data 4");
        list.add("Data 5");
        list.add("Data 6");
        list.add("Data 7");
        recyclerView.setAdapter(new AdminListPSAdapter(list,adapterI));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

}
