package com.jonathan.proyectofinal.fragments.admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminListPSFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView ;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;
    private AdminListPSFragmentI listPSFragmentI;

    public AdminListPSFragment (AdminListPSAdapter.AdminListPSAdapterI adapterI, AdminListPSFragmentI listPSFragmentI){
        this.adapterI = adapterI;
        this.listPSFragmentI = listPSFragmentI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        ButterKnife.bind(this, view);

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

    @OnClick(R.id.admin_fab_add)
    public void addPs(View view){

        listPSFragmentI.onclickAddPs();
    }

    public interface AdminListPSFragmentI{
        void onclickAddPs();
    }

}
