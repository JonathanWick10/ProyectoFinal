package com.jonathan.proyectofinal.fragments.carer;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.LoginManager;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.fragments.hp.MemorizameParent;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewCardMemorizame extends Fragment {

    private int flagInt;

    public NewCardMemorizame(int flagInt) {
        this.flagInt = flagInt;
    }


    String patientUID, patientUID_2, question, answer1, answer2, answer3, answer4;
    int correctAnswer;
    Memorizame memorizame = new Memorizame();
    boolean flag = false;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    StorageReference storageReference;
    Uri uriImage;
    Patient patient = new Patient();

    public static final int REQUEST_CODE2 = 10;


    Context context;

    @BindView(R.id.tv_title_question)
    TextView txtTitle;
    @BindView(R.id.edit_question)
    TextInputEditText questionPatient;
    @BindView(R.id.edit_answer1)
    TextInputEditText answer1Patient;
    @BindView(R.id.edit_answer2)
    TextInputEditText answer2Patient;
    @BindView(R.id.edit_answer3)
    TextInputEditText answer3Patient;
    @BindView(R.id.edit_answer4)
    TextInputEditText answer4Patient;
    @BindView(R.id.edit_correct_answer)
    AutoCompleteTextView correctAnswerPatient;
    @BindView(R.id.profile_image)
    CircleImageView addImage;
    Bundle args = new Bundle();

    String categoria = "";


    @BindView(R.id.button_create_memorizame)
    MaterialButton btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_card_memorizame, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            patient = (Patient) bundle.getSerializable("patient");
        }


        context = container.getContext();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        patientUID = firebaseUser.getUid();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        //verifiFieds();
        ButterKnife.bind(this, view);
        dropdownMenu(view);

        //region for flags

        switch (flagInt) {
            case 1:
                categoria = "Family";
                txtTitle.setText("Agregar pregunta de familia");
                break;
            case 2:
                categoria = "Pets";
                txtTitle.setText("Agregar pregunta de mascotas");
                break;
            case 3:
                categoria = "Home";
                txtTitle.setText("Agregar pregunta de hogar");
                break;
            case 4:
                categoria = "Places";
                txtTitle.setText("Agregar pregunta de lugar");
                break;

        }
        //endregion


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag2 = setPojoMemorizame();

                //Log.d("Save Memorizame","flag:"+flag2);
                if (flag2) {

                    String option = "option";
                    saveMemorizame();
                    Alert(option);
                } else {

                }
            }
        });

        logicImageProfile();
        return view;
    }

    private void dropdownMenu(View view) {
        String[] correctAnswerArray = {"1", "2", "3", "4"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_menu_popup_item, correctAnswerArray);
        correctAnswerPatient.setAdapter(arrayAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE2 && resultCode == Activity.RESULT_OK) {
            uriImage = data.getData();
            if (uriImage != null) {
                Glide.with(getActivity()).load(uriImage).fitCenter().into(addImage);
            }
            //profileImage.setImageURI(uriImage);
        }
    }

    private void logicImageProfile() {
        addImage.setOnClickListener(new View.OnClickListener() {
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
    }

    public void saveMemorizame() {

        switch (flagInt) {
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
        //endregion

        //region for logic save


        final String categoria2 = categoria;

        if (uriImage != null) {
            final String uuidGenerated = createTransactionID();
            memorizame.setUuidGenerated(uuidGenerated);

            final StorageReference imgRef = storageReference.child(categoria + "/" + uuidGenerated + ".jpg");
            imgRef.putFile(uriImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uri.isComplete()) ;
                            Uri url = uri.getResult();
                            memorizame.setUriImg(url.toString());
                            db.collection(Constants.Memorizame).document(patient.getPatientUID())
                                    .collection(categoria2).document(uuidGenerated).set(memorizame)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        }
                                    });
                        }
                    });
            Toast.makeText(getActivity(), "Tarjeta Memorizame guardada exitosamente", Toast.LENGTH_SHORT).show();

        }
        //endregion
    }

    public String createTransactionID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public void Alert(String option) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                builder.setView(dialogView);
                alertDialog = builder.create();

                Button btn1 = (Button) dialogView.findViewById(R.id.btn1);
                btn1.setText(R.string.no);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        translation();
                        setClearForm();
                        alertDialog.dismiss();
                    }
                });
                Button btn2 = (Button) dialogView.findViewById(R.id.btn2);
                btn2.setText(R.string.yes);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setClearForm();
                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation = dialogView.findViewById(R.id.textView);
                tvInformation.setText(R.string.ask_dialog_memorizame);
                alertDialog.show();


            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } else {
            builder.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    translation();
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

        switch (option) {
            case "eliminar":
                break;
        }


    }

    private void setClearForm() {
        Glide.with(context).load(getResources().getDrawable(R.drawable.img_add_image)).fitCenter().into(addImage);
        questionPatient.setText("");
        answer1Patient.setText("");
        answer2Patient.setText("");
        answer3Patient.setText("");
        answer4Patient.setText("");
        correctAnswerPatient.setText("");

    }

    private void translation() {
        Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        Fragment change;
        change = new MemorizameParent();
        transaction.replace(R.id.container_memorizame_parent,change).commit();
    }

    private boolean setPojoMemorizame() {
        //   patientUID_2=patientUID.get;
        question = questionPatient.getText().toString();
        answer1 = answer1Patient.getText().toString();
        answer2 = answer2Patient.getText().toString();
        answer3 = answer3Patient.getText().toString();
        answer4 = answer4Patient.getText().toString();
        String correct = correctAnswerPatient.getText().toString();

        if (!question.isEmpty() && !answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty() &&
                !answer4.isEmpty() && !correct.isEmpty() && uriImage != null) {

            memorizame.setQuestion(question);
            memorizame.setAnswer1(answer1);
            memorizame.setAnswer2(answer2);
            memorizame.setAnswer3(answer3);
            memorizame.setAnswer4(answer4);
            memorizame.setPatientUID(patientUID);
            switch (correct) {
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

            return flag = true;
        } else {
            if (uriImage == null) {
                Toast.makeText(getActivity(), "Agregue imagen", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, getResources().getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();

            }
            return flag = false;


        }

    }

}
