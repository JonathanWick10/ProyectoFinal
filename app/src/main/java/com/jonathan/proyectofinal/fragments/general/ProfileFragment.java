package com.jonathan.proyectofinal.fragments.general;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View view;

    @BindView(R.id.til_password_profile)
    TextInputLayout til_password;

    @BindView(R.id.img_image_profile)
    CircleImageView civProfile;
    @BindView(R.id.edit_name_profile)
    TextInputEditText txtName;
    @BindView(R.id.edit_last_name_profile)
    TextInputEditText txtLastName;
    @BindView(R.id.edit_identification_type_profile)
    AutoCompleteTextView txtIdType;
    @BindView(R.id.edit_identification_profile)
    TextInputEditText txtIdentification;
    @BindView(R.id.rg_gender_profile)
    RadioGroup rgGender;
    @BindView(R.id.edit_date_birth_profile)
    TextInputEditText txtDateBirth;
    @BindView(R.id.iv_date_birth_profile)
    CircleImageView civCalendarDB;
    @BindView(R.id.edit_native_city_profile)
    TextInputEditText txtNativeCity;
    @BindView(R.id.edit_phone_profile)
    TextInputEditText txtPhone;
    @BindView(R.id.edit_department_profile)
    AutoCompleteTextView txtDepartment;
    @BindView(R.id.edit_actual_city_profile)
    TextInputEditText txtActualCity;
    @BindView(R.id.edit_address_profile)
    TextInputEditText txtAddress;
    @BindView(R.id.edit_email_profile)
    TextInputEditText txtEmail;
    @BindView(R.id.edit_user_profile)
    TextInputEditText txtUser;
    @BindView(R.id.edit_password_profile)
    TextInputEditText txtPassword;
    @BindView(R.id.edit_diagnostic_patient)
    TextInputEditText txtDiagnosis;
    @BindView(R.id.edit_diagnosis_date_profile)
    TextInputEditText txtDiagnosisDate;
    @BindView(R.id.iv_date_diagnosis_profile)
    CircleImageView civCalendarDD;
    @BindView(R.id.edit_profession_profile)
    TextInputEditText txtProfession;
    @BindView(R.id.edit_workplace_profile)
    TextInputEditText txtWorkPlace;
    @BindView(R.id.button_update_profile)
    MaterialButton btnUpdate;
    String selectedDate;
    public static final int REQUEST_CODE = 11;
    public static final int REQUEST_CODE1 = 12;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        dropdowns(view);
        logicButtonCalendarDB(view);
        logicButtonCalendarDD(view);
        verifiFieds();
        return view;
    }

    private void verifiFieds() {
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                vaildateFields("password");
            }
        });
    }

    private boolean vaildateFields(String field) {
        boolean data = true;
        switch (field){
            case "password":
                String email = txtPassword.getText().toString().trim();
                if (email.length() < 7) {
                    til_password.setError(getString(R.string.val_min_passwornd));
                    data = false;
                }
                break;
        }
        return data;
    }

    private void dropdowns(View view) {
        String typeId1 = "Atlántico";
        String typeId2 = "Antioquia";
        String typeId3 = "Caqueta";
        String typeId4 = "Cauca";
        String typeId5 = "Cundinamarca";
        String typeId6 = "Huila";
        String typeId7 = "Nariño";
        String typeId8 = "Norte de Santander";
        String typeId9 = "Santander";
        String typeId10 = "Valle del cauca";
        String[] departamentos = new String[] {typeId1, typeId2, typeId3, typeId4, typeId5, typeId6, typeId7, typeId8, typeId9, typeId10,};
        String[] documentos = {getResources().getString(R.string.citizenship_card), getResources().getString(R.string.foreign_identity_card), getResources().getString(R.string.passport)};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        txtIdType.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, departamentos);
        txtDepartment.setAdapter(adapter2);
    }

    private void logicButtonCalendarDB(View view) {
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        civCalendarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new DatePickerFragmentDateOfBirth();
                newFragment.setTargetFragment(ProfileFragment.this, REQUEST_CODE);
                newFragment.show(fm, "datePicker");
            }
        });
    }

    private void logicButtonCalendarDD(View view) {
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        civCalendarDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(ProfileFragment.this, REQUEST_CODE1);
                newFragment.show(fm, "datePicker");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            txtDateBirth.setText(selectedDate);
        } else if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            txtDiagnosisDate.setText(selectedDate);
        }
    }
}
