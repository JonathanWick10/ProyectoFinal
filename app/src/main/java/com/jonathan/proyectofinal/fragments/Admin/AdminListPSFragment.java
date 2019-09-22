package com.jonathan.proyectofinal.fragments.Admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        recyclerView.setAdapter(new AdminListPSAdapter(list,adapterI));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

}
