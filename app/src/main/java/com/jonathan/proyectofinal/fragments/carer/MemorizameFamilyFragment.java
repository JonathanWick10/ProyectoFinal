package com.jonathan.proyectofinal.fragments.carer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemorizameFamilyFragment extends Fragment {

    CardView addquestion;
    private int flag;
    AutoCompleteTextView rtaAut;

    private MemorizameFamilyGridAdapter adapter;
    private MemorizameFamilyGridAdapter.ISelectionMemorizame iSelectionMemorizame;

    //region for fragment grid

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_add_image)
    TextView titleAddImage;
    @BindView(R.id.iv_question)
    ImageView ivQuestion;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private String userHPoCarer = "";
    private Carer carer = new Carer();
    private ProgressDialog progressDialog;
    private Patient patient;
    private String categoria = "";
    public static final int REQUEST_CODE2 = 10;
    private CircleImageView imageUpdate;
    private Uri uriImage;
    private StorageReference storageReference;
    //endregion

    public MemorizameFamilyFragment(int flag) { this.flag = flag;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame_family, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = getArguments();
        if (bundle != null) {
            patient = (Patient) bundle.getSerializable("patient");
        }

        switch (flag) {
            case 1:
                categoria = "Family";
                ivQuestion.setImageResource(R.drawable.img_family_question);
                titleAddImage.setText("Agregar pregunta de familia");
                break;
            case 2:
                categoria = "Pets";
                ivQuestion.setImageResource(R.drawable.img_pets_question);
                titleAddImage.setText("Agregar pregunta de mascotas");
                break;
            case 3:
                categoria = "Home";
                ivQuestion.setImageResource(R.drawable.img_home_question);
                titleAddImage.setText("Agregar pregunta de hogar");
                break;
            case 4:
                categoria = "Places";
                ivQuestion.setImageResource(R.drawable.img_places_question);
                titleAddImage.setText("Agregar pregunta de lugar");
                break;

        }
        //endregion


        addquestion = view.findViewById(R.id.cv_add_image);
        addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                Fragment change;
                change = new NewCardMemorizame(flag);
                change.setArguments(bundle);
                transaction.replace(R.id.container_memorizame_parent,change).addToBackStack(null).commit();
            }
        });

//region for fragment grid
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(getActivity());

        logicEventSelecItem();

        initRecyclerView();

        //endregion
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //reference();
        adapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    //endregion


    private void logicEventSelecItem() {
        iSelectionMemorizame = new MemorizameFamilyGridAdapter.ISelectionMemorizame() {
            @Override
            public void clickItem(final Memorizame memorizame) {
                //region AlertDialog
                final AlertDialog alertDialog;
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // only for Lollipop and newer versions
                    try {
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.new_card_memorizame, null);
                        builder.setView(dialogView);
                        alertDialog = builder.create();

                        TextView txtTitle = dialogView.findViewById(R.id.tv_title_question);
                        imageUpdate = dialogView.findViewById(R.id.profile_image);
                        final TextInputEditText editQuestion = dialogView.findViewById(R.id.edit_question);
                        final TextInputEditText editAnswer1 = dialogView.findViewById(R.id.edit_answer1);
                        final TextInputEditText editAnswer2 = dialogView.findViewById(R.id.edit_answer2);
                        final TextInputEditText editAnswer3 = dialogView.findViewById(R.id.edit_answer3);
                        final TextInputEditText editAnswer4 = dialogView.findViewById(R.id.edit_answer4);
                        rtaAut = dialogView.findViewById(R.id.edit_correct_answer);
                        MaterialButton btnactualizar = dialogView.findViewById(R.id.button_create_memorizame);

                        txtTitle.setText("Actualizar Pregunta");
                        Glide.with(getActivity()).load(memorizame.getUriImg()).fitCenter().into(imageUpdate);
                        editQuestion.setText(memorizame.getQuestion());
                        editAnswer1.setText(memorizame.getAnswer1());
                        editAnswer2.setText(memorizame.getAnswer2());
                        editAnswer3.setText(memorizame.getAnswer3());
                        editAnswer4.setText(memorizame.getAnswer4());

                        String correctRTA = memorizame.getCorrectAnswer();
                        if (correctRTA.equals(memorizame.getAnswer1())) {
                            rtaAut.setText("1");
                        } else if (correctRTA.equals(memorizame.getAnswer2())) {
                            rtaAut.setText("2");
                        } else if (correctRTA.equals(memorizame.getAnswer3())) {
                            rtaAut.setText("3");
                        } else if (correctRTA.equals(memorizame.getAnswer4())) {
                            rtaAut.setText("4");
                        }


                        dropdownMenu();

                        imageUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent to tour the gallery
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                // Accept all kinds of images
                                intent.setType("image/*");
                                //If you have several types of viewers, it will ask which one to start with
                                startActivityForResult(intent.createChooser(intent, getResources().getString(R.string.select_photo)), REQUEST_CODE2);
                            }
                        });

                        btnactualizar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                        "Brainmher", "Actualizando Pregunta");
                                String rtaCorrect, question, answer1, answer2, answer3, answer4;
                                question = editQuestion.getText().toString();
                                answer1 = editAnswer1.getText().toString();
                                answer2 = editAnswer2.getText().toString();
                                answer3 = editAnswer3.getText().toString();
                                answer4 = editAnswer4.getText().toString();
                                rtaCorrect = rtaAut.getText().toString();
                                if (!question.isEmpty() && !answer1.isEmpty() && !answer2.isEmpty()
                                        && !answer3.isEmpty() && !answer4.isEmpty() && !rtaCorrect.isEmpty()) {
                                    memorizame.setQuestion(question);
                                    memorizame.setAnswer1(answer1);
                                    memorizame.setAnswer2(answer2);
                                    memorizame.setAnswer3(answer3);
                                    memorizame.setAnswer4(answer4);
                                    switch (rtaCorrect) {
                                        case "1":
                                            memorizame.setCorrectAnswer(answer1);
                                            break;
                                        case "2":
                                            memorizame.setCorrectAnswer(answer2);
                                            break;
                                        case "3":
                                            memorizame.setCorrectAnswer(answer3);
                                            break;
                                        case "4":
                                            memorizame.setCorrectAnswer(answer4);
                                            break;
                                    }
                                    if (uriImage != null) {
                                    /*
                                    if (uriImage == null) {
                                        uriImage = Uri.parse(memorizame.getUriImg());
                                    }*/
                                        storageReference = FirebaseStorage.getInstance().getReference();
                                        StorageReference deleteImg = storageReference.child(categoria + "/" + memorizame.getUuidGenerated() + ".jpg");
                                        deleteImg.delete();
                                        final StorageReference imgRef = storageReference.child(categoria + "/" + memorizame.getUuidGenerated() + ".jpg");
                                        imgRef.putFile(uriImage)
                                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                                        while (!uri.isComplete()) ;
                                                        Uri url = uri.getResult();
                                                        memorizame.setUriImg(url.toString());
                                                        db.collection(Constants.Memorizame).document(patient.getPatientUID())
                                                                .collection(categoria).document(memorizame.getUuidGenerated()).set(memorizame)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
                                                                        progressDialog.dismiss();
                                                                    }
                                                                });
                                                    }
                                                });
                                    }else {
                                        db.collection(Constants.Memorizame).document(patient.getPatientUID())
                                                .collection(categoria).document(memorizame.getUuidGenerated()).set(memorizame)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();

                                                        progressDialog.dismiss();
                                                    }
                                                });
                                    }
                                }
                            }
                        });


                        alertDialog.show();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {

                }

                //endregion
            }

            @Override
            public void clickdelete(final Memorizame memorizame) {
                final AlertDialog alertDialog;

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // only for Lollipop and newer versions
                    try {
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                        builder.setView(dialogView);
                        alertDialog = builder.create();

                        Button btn1 = (Button) dialogView.findViewById(R.id.btn1);
                        btn1.setText(R.string.no);
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                alertDialog.dismiss();
                            }
                        });

//region for flags
                        Toast.makeText(getActivity(), "Memorizame" + flag, Toast.LENGTH_SHORT).show();
                        String categoria = "";
                        switch (flag) {
                            case 1:
                                categoria = "Family";
                                break;
                            case 2:
                                categoria = "Pets";
                                break;
                            case 3:
                                categoria = "Home";
                                break;
                            case 4:
                                categoria = "Places";
                                break;

                        }
                        final String categoria2 = categoria;
                        //endregion

                        Button btn2 = (Button) dialogView.findViewById(R.id.btn2);
                        btn2.setText(R.string.yes);
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                        "Brainmher", "Eliminando registro en línea");
                                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                StorageReference deleteImage = storageReference.child(categoria2 + "/" + memorizame.getUuidGenerated() + ".jpg");
                                deleteImage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        db.collection(Constants.Memorizame).document(patient.getPatientUID()).collection(categoria2)
                                                .document(memorizame.getUuidGenerated()).delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        progressDialog.dismiss();
                                                        db.collection(Constants.Memorizame).document(patient.getPatientUID()).delete();
                                                        Toast.makeText(getActivity(), "memorizame eliminado", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });

                                    }
                                });

                                alertDialog.dismiss();
                            }
                        });
                        TextView tvInformation = dialogView.findViewById(R.id.textView);
                        tvInformation.setText(getString(R.string.message_delete_memorizame));
                        alertDialog.show();


                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {

                    builder.setTitle(getString(R.string.alert));
                    builder.setMessage(getString(R.string.message_delete));
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                    "Brainmher", "Eliminando registro en línea");


                        }
                    });
                    builder.show();
                }
            }
        };
    }

    private void dropdownMenu() {
        String[] correctAnswerArray = {"1", "2", "3", "4"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_menu_popup_item, correctAnswerArray);
        rtaAut.setAdapter(arrayAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE2 && resultCode == getActivity().RESULT_OK) {
            uriImage = data.getData();
            if (uriImage != null) {
                Glide.with(getActivity()).load(uriImage).fitCenter().into(imageUpdate);
            }
        }
    }

//region region for fragment grid

    private void initRecyclerView() {

        String uid = user.getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Memorizame).document(patient.getPatientUID()).collection(categoria);
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<Memorizame> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Memorizame>()
                .setQuery(query, Memorizame.class).build();

        //Passing parameters to the adapter
        adapter = new MemorizameFamilyGridAdapter(firestoreRecyclerOptions, getActivity(), iSelectionMemorizame);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
//endregion
}
