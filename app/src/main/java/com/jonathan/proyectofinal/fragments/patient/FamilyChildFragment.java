package com.jonathan.proyectofinal.fragments.patient;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.adapters.PatientMemorizameAdapter;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyChildFragment extends Fragment {

    //region Variables
    @BindView(R.id.recyclerViewFamily)
    RecyclerView recyclerView;
    @BindView(R.id.layout_description_family)
    LinearLayout layout;
    private PatientMemorizameAdapter adapter;
    private PatientMemorizameAdapter.ISelectionMemorizame iSelectionMemorizame;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Memorizame> memorizameList = new ArrayList<>();
    Memorizame memorizameM = new Memorizame();
    Patient patient = new Patient();
    String seleccionRG = "";
    //endregion


    public FamilyChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_family_child, container, false);
        ButterKnife.bind(this, view);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        logicEventItemSelect();
        initRecycler();
        return view;
    }

    private void logicEventItemSelect() {

        iSelectionMemorizame = new PatientMemorizameAdapter.ISelectionMemorizame() {
            @Override
            public void clickItem(final Memorizame memorizame) {



                AlertQuestion(memorizame);

            }
        };
    }

    private void AlertQuestion(final Memorizame memorizame) {
        //region AlertDialog
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.layout_question_memorizame, null);
                builder.setView(dialogView);
                alertDialog=builder.create();

                TextView txtQuestion=dialogView.findViewById(R.id.text_question);
                txtQuestion.setText(memorizame.getQuestion());
                final RadioGroup rgASK = dialogView.findViewById(R.id.rg_question);
                RadioButton rb1 = dialogView.findViewById(R.id.rb_question_1);
                RadioButton rb2 = dialogView.findViewById(R.id.rb_question_2);
                RadioButton rb3 = dialogView.findViewById(R.id.rb_question_3);
                RadioButton rb4 = dialogView.findViewById(R.id.rb_question_4);
                rb1.setText(memorizame.getAnswer1());
                rb2.setText(memorizame.getAnswer2());
                rb3.setText(memorizame.getAnswer3());
                rb4.setText(memorizame.getAnswer4());
                ImageView imgQuestion = dialogView.findViewById(R.id.img_question);
                Glide.with(getActivity()).load(memorizame.getUriImg()).fitCenter().into(imgQuestion);

                Button btn1=(Button)dialogView.findViewById(R.id.btn_cancelar);
                btn1.setText(R.string.cancel);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //translation();
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn_terminar);
                btn2.setText(R.string.yes);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //region Get the selection of RadioGroup
                        if (rgASK.getCheckedRadioButtonId() != -1) {
                            int radioButtonId = rgASK.getCheckedRadioButtonId();
                            View radioButton = rgASK.findViewById(radioButtonId);
                            int indice = rgASK.indexOfChild(radioButton);
                            RadioButton rb = (RadioButton)  rgASK.getChildAt(indice);
                            seleccionRG = rb.getText().toString();
                        }
                        //endregion

                        String rtaCorrect = memorizame.getCorrectAnswer();

                        if (!seleccionRG.isEmpty()){
                            if (seleccionRG.equals(rtaCorrect)){
                                Alert("Genial Acertaste! ¿Quieres Intentarlo de nuevo?",memorizame);
                                alertDialog.dismiss();
                            }
                            else {
                                Alert("Lo siento fallaste! ¿Quieres Intentarlo de nuevo?", memorizame);
                                alertDialog.dismiss();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Selecciona una respuesta!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.show();
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }else{
            builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!seleccionRG.isEmpty()){
                        if (seleccionRG == memorizame.getCorrectAnswer()){
                            Alert("Genial Acertaste! ¿Quieres Intentarlo de nuevo?", memorizame);
                            dialog.dismiss();
                        }
                        else {
                            Alert("Lo siento fallaste! ¿Quieres Intentarlo de nuevo?", memorizame);
                            dialog.dismiss();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Selecciona una respuesta!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();

        }

        //endregion
    }


    public void Alert(String option, final Memorizame memorizame) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                builder.setView(dialogView);
                alertDialog=builder.create();

                Button btn1=(Button)dialogView.findViewById(R.id.btn1);
                btn1.setText(R.string.no);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        seleccionRG = "";
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn2);
                btn2.setText(R.string.yes);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertQuestion(memorizame);
                        seleccionRG= "";
                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation=dialogView.findViewById(R.id.textView);
                tvInformation.setText(option);
                alertDialog.show();
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        else{
            builder.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setCancelable(false);
            builder.show();

        }
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas

        CollectionReference collectionReferenceMemorizame = db.collection(Constants.Memorizame);

        collectionReferenceMemorizame.document(user.getUid()).collection("Family").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        memorizameList = new ArrayList<Memorizame>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            memorizameM = documentSnapshopt.toObject(Memorizame.class);
                            memorizameList.add(memorizameM);
                        }
                        adapter = new PatientMemorizameAdapter(memorizameList,getActivity(),iSelectionMemorizame);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        if (memorizameList.size()!=0){
                            layout.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }else{
                            layout.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
