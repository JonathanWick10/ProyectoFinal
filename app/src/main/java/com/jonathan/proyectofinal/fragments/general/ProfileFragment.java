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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.admin.AdminAddHealthProfessional;
import com.jonathan.proyectofinal.tools.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View view;

    @BindView(R.id.til_name_profile)
    TextInputLayout til_name;
    @BindView(R.id.til_lastname_profile)
    TextInputLayout til_lastname;
    @BindView(R.id.til_identification_type_profile)
    TextInputLayout til_identification_type;
    @BindView(R.id.til_identification_profile)
    TextInputLayout til_identification;
    @BindView(R.id.til_date_birth_profile)
    TextInputLayout til_date_birth;
    @BindView(R.id.til_native_city_profile)
    TextInputLayout til_native_city;
    @BindView(R.id.til_phone_profile)
    TextInputLayout til_phone;
    @BindView(R.id.til_department_profile)
    TextInputLayout til_department;
    @BindView(R.id.til_actual_city_profile)
    TextInputLayout til_actual_city;
    @BindView(R.id.til_address_profile)
    TextInputLayout til_address;
    @BindView(R.id.til_email_profile)
    TextInputLayout til_email;
    @BindView(R.id.til_user_profile)
    TextInputLayout til_user;
    @BindView(R.id.til_password_profile)
    TextInputLayout til_password;
    @BindView(R.id.til_profession_profile)
    TextInputLayout til_profession;
    @BindView(R.id.til_workplace_profile)
    TextInputLayout til_workplace;



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
    @BindView(R.id.rb_female_profile)
    RadioButton rb_female;
    @BindView(R.id.rb_male_profile)
    RadioButton rb_male;
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
    @BindView(R.id.edit_profession_profile)
    TextInputEditText txtProfession;
    @BindView(R.id.edit_workplace_profile)
    TextInputEditText txtWorkPlace;
    @BindView(R.id.button_update_profile)
    MaterialButton btnUpdate;
    String selectedDate;
    String selectedGender;
    public static final int REQUEST_CODE = 11;

    FirebaseFirestore db;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        getUserData();
        dropdowns(view);
        logicButtonCalendarDB(view);
        verifiFieds();
        return view;
    }

    private void getUserData() {
        db = FirebaseFirestore.getInstance();
        Bundle bundle = getArguments();
        if (bundle!=null){
            String uID = bundle.getString("userUid");
            String role = bundle.getString("userRole");

            switch (role){
                case "Admin":
                    db.collection(Constants.Adminds).document(uID).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()){
                                        Admin admin = new Admin();
                                        admin = documentSnapshot.toObject(Admin.class);
                                        setDataAdmins(admin);
                                    }
                                }
                            });
                    break;
                case "Healthcare_profesionals":
                    db.collection(Constants.HealthcareProfesional).document(uID).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()){
                                        HealthcareProfessional hp = new HealthcareProfessional();
                                        hp = documentSnapshot.toObject(HealthcareProfessional.class);
                                        setDataHp(hp);
                                    }
                                }
                            });
                    break;
                case "Carers":
                    db.collection(Constants.Carers).document(uID).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()){
                                        Carer carer = new Carer();
                                        carer = documentSnapshot.toObject(Carer.class);
                                        setDataCarer(carer);
                                    }
                                }
                            });
                    break;
                case "Patients":
                    db.collection(Constants.Patients).document(uID).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()){
                                        Patient patient = new Patient();
                                        patient = documentSnapshot.toObject(Patient.class);
                                        setDataPatients(patient);
                                    }
                                }
                            });
                    break;
            }


        }
    }

    private void setDataCarer(Carer carer) {
        //Fields properties
        til_department.setVisibility(View.GONE);

        //Set data in fields
        txtName.setText(carer.getFirstName());
        txtLastName.setText(carer.getLastName());
        txtIdType.setText(carer.getIdentificationType(), false);
        txtIdentification.setText(carer.getIdentification());
        txtProfession.setText(carer.getProfession());
        selectedGender = carer.getGender();
        switch (selectedGender){
            case "Femenino":
                rb_female.setChecked(true);
                break;
            case "Masculino":
                rb_male.setChecked(true);
                break;
        }
        txtDateBirth.setText(carer.getBirthday());
        txtPhone.setText(Long.toString(carer.getPhoneNumber()));
        txtUser.setText(carer.getUserName());
        txtPassword.setText(carer.getPassword());
        txtEmail.setText(carer.getEmail());
        txtNativeCity.setText(carer.getNativeCity());
        txtActualCity.setText(carer.getActualCity());
        txtAddress.setText(carer.getAddress());
        txtWorkPlace.setText(carer.getEmploymentPlace());
    }

    private void setDataHp(HealthcareProfessional hp) {
        //Fields properties
        til_department.setVisibility(View.GONE);

        //Set data in fields
        txtName.setText(hp.getFirstName());
        txtLastName.setText(hp.getLastName());
        txtIdType.setText(hp.getIdentificationType(), false);
        txtIdentification.setText(hp.getIdentification());
        txtProfession.setText(hp.getProfession());
        selectedGender = hp.getGender();
        switch (selectedGender){
            case "Femenino":
                rb_female.setChecked(true);
                break;
            case "Masculino":
                rb_male.setChecked(true);
                break;
        }
        txtDateBirth.setText(hp.getBirthday());
        txtPhone.setText(Long.toString(hp.getPhoneNumber()));
        txtUser.setText(hp.getUserName());
        txtPassword.setText(hp.getPassword());
        txtEmail.setText(hp.getEmail());
        txtNativeCity.setText(hp.getNativeCity());
        txtActualCity.setText(hp.getActualCity());
        txtAddress.setText(hp.getAddress());
        txtWorkPlace.setText(hp.getEmployment_place());
    }

    private void setDataAdmins(Admin admin) {
        //Fields properties
        txtName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtIdType.setEnabled(false);
        txtIdentification.setEnabled(false);
        txtProfession.setEnabled(false);
        rb_female.setEnabled(false);
        rb_male.setEnabled(false);
        txtDateBirth.setEnabled(false);
        txtNativeCity.setEnabled(false);
        txtPhone.setEnabled(false);
        txtActualCity.setEnabled(false);
        txtAddress.setEnabled(false);
        txtEmail.setEnabled(false);
        txtUser.setEnabled(false);
        txtPassword.setEnabled(false);
        txtWorkPlace.setEnabled(false);
        til_name.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_lastname.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_identification_type.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_identification.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_profession.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_date_birth.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_native_city.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_phone.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_actual_city.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_address.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_email.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_user.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_workplace.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_department.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        civCalendarDB.setVisibility(View.GONE);
        txtName.setFocusable(false);
        txtLastName.setFocusable(false);
        txtIdType.setFocusable(false);
        txtIdentification.setFocusable(false);
        txtProfession.setFocusable(false);
        txtDateBirth.setFocusable(false);
        txtNativeCity.setFocusable(false);
        txtPhone.setFocusable(false);
        txtActualCity.setFocusable(false);
        txtAddress.setFocusable(false);
        txtEmail.setFocusable(false);
        txtDateBirth.setFocusable(false);
        txtUser.setFocusable(false);
        txtPassword.setFocusable(false);
        txtWorkPlace.setFocusable(false);

        //Set data in fields
        txtName.setText(admin.getFirstName());
        txtLastName.setText(admin.getLastName());
        txtIdType.setText(admin.getIdentificationType(), false);
        txtIdentification.setText(admin.getIdentification());
        txtProfession.setText(admin.getProfession());
        selectedGender = admin.getGender();
        switch (selectedGender){
            case "Femenino":
                rb_female.setChecked(true);
                break;
            case "Masculino":
                rb_male.setChecked(true);
                break;
        }
        txtDateBirth.setText(admin.getBirthday());
        txtPhone.setText(Long.toString(admin.getPhoneNumber()));
        txtUser.setText(admin.getUserName());
        txtPassword.setText(admin.getPassword());
        txtEmail.setText(admin.getEmail());
        txtNativeCity.setText(admin.getNativeCity());
        txtActualCity.setText(admin.getActualCity());
        txtAddress.setText(admin.getAddress());
        txtWorkPlace.setText(admin.getEmploymentPlace());
    }

    //Método para ocular campos no correspondientes al paciente y colocar datos pertenecientes
    private void setDataPatients(Patient patient) {
        //Fields properties
        txtName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtIdType.setEnabled(false);
        txtIdentification.setEnabled(false);
        rb_female.setEnabled(false);
        rb_male.setEnabled(false);
        txtDateBirth.setEnabled(false);
        txtNativeCity.setEnabled(false);
        txtPhone.setEnabled(false);
        txtDepartment.setEnabled(false);
        txtActualCity.setEnabled(false);
        txtAddress.setEnabled(false);
        txtEmail.setEnabled(false);
        txtUser.setEnabled(false);
        txtPassword.setEnabled(false);
        til_name.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_lastname.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_identification_type.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_identification.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_date_birth.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_native_city.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_phone.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_department.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_actual_city.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_address.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_email.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_user.setEndIconMode(TextInputLayout.END_ICON_NONE);
        til_profession.setVisibility(View.GONE);
        til_workplace.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        civCalendarDB.setVisibility(View.GONE);
        txtName.setFocusable(false);
        txtLastName.setFocusable(false);
        txtIdType.setFocusable(false);
        txtIdentification.setFocusable(false);
        txtDateBirth.setFocusable(false);
        txtNativeCity.setFocusable(false);
        txtPhone.setFocusable(false);
        txtDepartment.setFocusable(false);
        txtActualCity.setFocusable(false);
        txtAddress.setFocusable(false);
        txtEmail.setFocusable(false);
        txtDateBirth.setFocusable(false);
        txtUser.setFocusable(false);
        txtPassword.setFocusable(false);

        //Set data in fields
        txtName.setText(patient.getFirstName());
        txtLastName.setText(patient.getLastName());
        txtIdType.setText(patient.getIdentificationType(), false);
        txtIdentification.setText(patient.getIdentification());
        selectedGender = patient.getGender();
        switch (selectedGender){
            case "Femenino":
                rb_female.setChecked(true);
                break;
            case "Masculino":
                rb_male.setChecked(true);
                break;
        }
        txtDateBirth.setText(patient.getBirthday());
        txtPhone.setText(Long.toString(patient.getPhoneNumber()));
        txtUser.setText(patient.getUserName());
        txtPassword.setText(patient.getPassword());
        txtEmail.setText(patient.getEmail());
        txtNativeCity.setText(patient.getNativeCity());
        txtActualCity.setText(patient.getActualCity());
        txtAddress.setText(patient.getAddress());
        txtDepartment.setText(patient.getDepartment(), false);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            txtDateBirth.setText(selectedDate);
        }
    }
}
