package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.LoginManager;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCardMemorizame extends Fragment {
    public NewCardMemorizame(){}


    String patientUID, patientUID_2, question,answer1,answer2,answer3,answer4;
    int correctAnswer;
    Memorizame memorizame= new Memorizame();
    boolean flag = false;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    StorageReference storageReference;
    Uri uriImage;
    Patient patient = new Patient();



    Context context;


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



    @BindView(R.id.button_create_memorizame)
    MaterialButton btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.new_card_memorizame,container,false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            patient = (Patient) bundle.getSerializable("patient");
        }


        context=container.getContext();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        patientUID = firebaseUser.getUid();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        uriImage = Uri.parse("android.resource://" + getActivity().getPackageName() +"/"+R.drawable.avatar_patient);
        //dropdownMenu(view);
        //logicButtonCalendar(view);
        //verifiFieds();
        ButterKnife.bind(this, view);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag2 = setPojoMemorizame();

                //Log.d("Save Memorizame","flag:"+flag2);
                if (flag2){

                    String option= "option";
                    Alert(option);
                }
                else {

                    String option= "option";
                    Alert(option);

                   // Toast.makeText(context, getResources().getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }



    public void Alert(String option) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.BackgroundRounded);

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
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn2);
                btn2.setText(R.string.yes);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation=dialogView.findViewById(R.id.textView);
                tvInformation.setText(R.string.ask_dialog_memorizame);
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

        switch (option){
            case "eliminar": break;
        }



    }

    private boolean setPojoMemorizame(){
        //   patientUID_2=patientUID.get;
        question = questionPatient.getText().toString();
        answer1 = answer1Patient.getText().toString();
        answer2 = answer2Patient.getText().toString();
        answer3 = answer3Patient.getText().toString();
        answer4=answer4Patient.getText().toString();
        String correct = correctAnswerPatient.getText().toString();

        if (!question.isEmpty() && !answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty() &&
                !answer4.isEmpty() && !correct.isEmpty()) {
            memorizame.setQuestion(question);
            memorizame.setAnswer1(answer1);
            memorizame.setAnswer2(answer2);
            memorizame.setAnswer3(answer3);
            memorizame.setAnswer4(answer4);
            memorizame.setCorrectAnswer(correctAnswer=Integer.parseInt(correct));
            memorizame.setPatientUID(patientUID);



             return flag = true;
        } else {
             return flag = false;


        }

    }



}
