package com.jonathan.proyectofinal.fragments.hp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.PatientsManager;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;
import com.jonathan.proyectofinal.ui.PatientsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientsListFragment extends Fragment {

    //region Variables
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private PatientsAdapter adapter;
    private PatientsAdapter.ISelectionPatient iSelectionPatient;
    private PatientsAdapter.IDeletePatient iDeletePatient;
    private View view;
    private PatientsManager patientsManager = new PatientsManager();
    List<Patient> list = new ArrayList<>();
    //endregion

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
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ButterKnife.bind(this, view);
        eventSelectedItem();
        eventDeleteItem();
        initRecyclerView();
    }

    //region Events Onclick
    private void eventSelectedItem() {
        iSelectionPatient = new PatientsAdapter.ISelectionPatient() {
            @Override
            public void clickItem(Patient patient) {
                Toast.makeText(getActivity(), patient.getIdentification()+" / "+patient.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void eventDeleteItem() {
        iDeletePatient = new PatientsAdapter.IDeletePatient() {
            @Override
            public void clickdelete(Patient patient) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                alerta.setTitle(getString(R.string.alert));
                alerta.setMessage(getString(R.string.message_delete) + " - " + patient.getFirstName());
                alerta.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Caiste prro", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.show();
            }
        };
    }
    //endregion

    private void initRecyclerView() {
        //list = patientsManager.listForHP("");
        //Código quemado--------------------------
        List<Patient> list2 = new ArrayList<>();
        Patient patientu = new Patient();
        patientu.setFirstName("jonathan");
        patientu.setIdentification("1061755715");
        for (int i = 0; i<=6; i++){
            list2.add(patientu);
        }
        //------------------------------------------
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PatientsAdapter(list2,getActivity(),iSelectionPatient,iDeletePatient);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    //region Código Carolina
   /* private void initAdapter() {
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
        PatientsManager patientsManager = new PatientsManager();
        HealthcareProfessional patient2 = new HealthcareProfessional();
        patient2.setIdentification("1061755715");
        adapter.addAll(patientsManager.listForHP(patient2)patients);
    }*/
    //endregion

}
