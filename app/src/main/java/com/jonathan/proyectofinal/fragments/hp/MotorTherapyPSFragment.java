package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.CognitivesAdapter;
import com.jonathan.proyectofinal.adapters.MotorAdapter;
import com.jonathan.proyectofinal.data.CognitivesExcercises;
import com.jonathan.proyectofinal.data.MotorExcercises;
import com.jonathan.proyectofinal.data.Patient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MotorTherapyPSFragment extends Fragment {

    MotorAdapter motorAdapter;
    @BindView(R.id.list_motor)
    RecyclerView list_motor;
    String uID;
    Patient patient = new Patient();
    Bundle bundle;

    public MotorTherapyPSFragment(Bundle bundle) {
        this.bundle = bundle;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_therapy_motor, container, false);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        if (bundle != null) {
            patient = (Patient) bundle.getSerializable("patient");
            uID = patient.getPatientUID();
        }
        Toast.makeText(getContext(), "Velo: "+uID, Toast.LENGTH_SHORT).show();
        fillRecycler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        motorAdapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        motorAdapter.startListening();
        //Toast.makeText(getContext(), "Velo: "+uID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        motorAdapter.stopListening();
    }

    private void fillRecycler() {
        list_motor.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("MotorExcercises").orderBy("idExcercise");

        FirestoreRecyclerOptions<MotorExcercises> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MotorExcercises>()
                .setQuery(query, MotorExcercises.class).build();

        motorAdapter = new MotorAdapter(firestoreRecyclerOptions, getActivity(), uID);
        motorAdapter.notifyDataSetChanged();
        list_motor.setAdapter(motorAdapter);
    }
}
