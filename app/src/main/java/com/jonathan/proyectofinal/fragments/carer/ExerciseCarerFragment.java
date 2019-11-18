package com.jonathan.proyectofinal.fragments.carer;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.StretchingAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.data.StretchingExercise;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.adapters.PhysicalExerciseAdapter;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.PhysicalExerciseEntity;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseCarerFragment extends Fragment {

    //region Variables
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View view;
    private StretchingAdapter adapter;
    private StretchingAdapter.ISelectionStretching iSelectionStretching;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<StretchingExercise> stretchingList = new ArrayList<>();
    String userHPoCarer = "";
    StretchingExercise stretchingM = new StretchingExercise();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    private PhysicalExecise.PhysicalExeciseI physicalExeciseI;
    //endregion

    public ExerciseCarerFragment(PhysicalExecise.PhysicalExeciseI physicalExeciseI ) {
        this.physicalExeciseI = physicalExeciseI;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_exercise_carer, container, false);
        ButterKnife.bind(this, view);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();

        eventLogicSelectItem();
        initRecyclerView();
        return view;
    }

    private void eventLogicSelectItem() {
        iSelectionStretching = new StretchingAdapter.ISelectionStretching() {
            @Override
            public void clickItem(StretchingExercise stretchingExercise) {
                String nameExercise=stretchingExercise.getNameExercise();
                String descriptionExercise=stretchingExercise.getDescriptionExercise();
                dialogStretching(nameExercise,descriptionExercise);
              //  Toast.makeText(getActivity(), "mira carolina "+stretchingExercise.getNameExercise(), Toast.LENGTH_LONG).show();
            }
        };
    }

    public void dialogStretching(String nameExercise, String descriptionExercise){

        final AlertDialog alertDialog;

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_one_textview_one_button_one_image, null);
                builder.setView(dialogView);
                alertDialog = builder.create();

                Button btn1 = (Button) dialogView.findViewById(R.id.btn1);
                btn1.setText("Cancelar");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        alertDialog.dismiss();
                    }
                });

                TextView tvNameExercise = dialogView.findViewById(R.id.textViewBold);
                tvNameExercise.setText(nameExercise);
                TextView tvInformation = dialogView.findViewById(R.id.textView);
                tvInformation.setText(descriptionExercise);
                alertDialog.show();


            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } else {

            builder.setTitle(getString(R.string.alert));
            builder.setMessage(nameExercise+" "+descriptionExercise);
            builder.show();
        }
    }



    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        db.collection(Constants.Stretching)
                .orderBy("nameExercise")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        stretchingList = new ArrayList<StretchingExercise>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            stretchingM = documentSnapshopt.toObject(StretchingExercise.class);
                            stretchingList.add(stretchingM);
                        }
                        adapter = new StretchingAdapter(stretchingList,getActivity(),iSelectionStretching);
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
}
