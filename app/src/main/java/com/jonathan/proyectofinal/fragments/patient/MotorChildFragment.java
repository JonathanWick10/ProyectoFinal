package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MotorExcercisesAdapter;
import com.jonathan.proyectofinal.data.MotorExcercisesAssignment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotorChildFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @BindView(R.id.rvMotorExercises)
    RecyclerView recyclerMotorExcercises;

    MotorExcercisesAdapter motorExcercisesAdapter;
    private MotorChildFragmentI motorChildFragmentI;
    private View view;

    public MotorChildFragment() {
        // Required empty public constructor
    }

    public MotorChildFragment(MotorChildFragmentI motorChildFragmentI) {
        this.motorChildFragmentI = motorChildFragmentI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_motor_child, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        ButterKnife.bind(this, view);
        fillRecycler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        motorExcercisesAdapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        motorExcercisesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        motorExcercisesAdapter.stopListening();
    }

    private void fillRecycler(){
        recyclerMotorExcercises.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("MotorExcercisesAssignments")
                .whereEqualTo("uidPatient", firebaseUser.getUid());

        FirestoreRecyclerOptions<MotorExcercisesAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MotorExcercisesAssignment>()
                .setQuery(query, MotorExcercisesAssignment.class).build();

        motorExcercisesAdapter = new MotorExcercisesAdapter(firestoreRecyclerOptions, getActivity(), motorChildFragmentI);
        motorExcercisesAdapter.notifyDataSetChanged();
        recyclerMotorExcercises.setAdapter(motorExcercisesAdapter);
    }

    public interface MotorChildFragmentI{
        void alert(String option, MotorExcercisesAssignment listExcercises);
    }
}
