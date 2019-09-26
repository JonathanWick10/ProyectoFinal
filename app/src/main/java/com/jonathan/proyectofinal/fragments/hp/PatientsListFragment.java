package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;
import com.jonathan.proyectofinal.ui.PatientsList;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientsListFragment extends Fragment implements IPatientsListFragmentListener, IOnPatientClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;

    private PatientsAdapter adapter;
    private View view;


    public PatientsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patients, container, false);
        reference();

        return view;
    }

    private void reference() {
        linearLayoutManager=new LinearLayoutManager(getActivity());
        ButterKnife.bind(this, view);
        initRecyclerView();
        initAdapter();
    }

    private void initAdapter() {
        if (adapter == null){
            adapter = new PatientsAdapter(getActivity().getApplicationContext(), this);
        }
    }
    private void initRecyclerView() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void onItemClick(Patient patient) {
        //Toast.makeText(getActivity(),customer.getCodigoCliente(),Toast.LENGTH_SHORT).show();
        ((PatientsList) getActivity()).fragmentClick(patient);
    }

    public void addAllToList(List<Patient> patients) {
        adapter.addAll(patients);
    }


}
