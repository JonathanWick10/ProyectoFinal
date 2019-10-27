package com.jonathan.proyectofinal.fragments.hp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.PatientsAdapter;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.PatientsManager;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;
import com.jonathan.proyectofinal.interfaces.IOnPatientClickListener;
import com.jonathan.proyectofinal.interfaces.IPatientsListFragmentListener;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;
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
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Patient> patientList = new ArrayList<>();
    Patient patientM = new Patient();
    //endregion

    public PatientsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patients, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
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
                Intent goPatient =new Intent(getActivity(), HealthProfessionalActivity.class);
                String patientUID= patient.getPatientUID();
                String patientIdentification= patient.getIdentification();
                goPatient.putExtra("patientUID", patientUID);
                goPatient.putExtra("patientIdentification", patientIdentification);

                startActivity(goPatient);
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
        //------------------------------------------
        recyclerView.setLayoutManager(linearLayoutManager);
        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReferencePatients = db.collection(Constants.Patients);
        collectionReferencePatients
                .whereArrayContains("assigns", uid)
                .orderBy("firstName")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        patientList = new ArrayList<Patient>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            patientM = documentSnapshopt.toObject(Patient.class);
                            patientList.add(patientM);
                        }
                        adapter = new PatientsAdapter(patientList,getActivity(),iSelectionPatient,iDeletePatient);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });


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
