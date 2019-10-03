package com.jonathan.proyectofinal.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.ImageManager;
import com.jonathan.proyectofinal.database.PatientsManager;
import com.jonathan.proyectofinal.ui.PatientsList;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddPatients extends Fragment {
    public AddPatients() {
    }
    //region Variables
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.edit_name_patient)
    TextInputEditText editName;
    @BindView(R.id.edit_last_name_patient)
    TextInputEditText editLastName;
    @BindView(R.id.edit_identification_type_patient)
    AutoCompleteTextView autoCompletIdType;
    @BindView(R.id.edit_identification_patient)
    TextInputEditText editIdentification;
    @BindView(R.id.rg_gender_patient)
    RadioGroup rgGender;
    @BindView(R.id.rb_female_patient)
    RadioButton rbFemale;
    @BindView(R.id.rb_male_patient)
    RadioButton rbMale;
    @BindView(R.id.edit_birthday_patients)
    TextInputEditText editBirthday;
    @BindView(R.id.edit_phone_patient)
    TextInputEditText editPhone;
    @BindView(R.id.edit_department_patient)
    AutoCompleteTextView autoCompletDepartment;
    @BindView(R.id.edit_native_city_patient)
    TextInputEditText editNativeCity;
    @BindView(R.id.edit_actual_city_patient)
    TextInputEditText editActualCity;
    @BindView(R.id.edit_address_patient)
    TextInputEditText editaddress;
    @BindView(R.id.edit_email_patient)
    TextInputEditText editEmail;
    @BindView(R.id.edit_user_patient)
    TextInputEditText editUser;
    @BindView(R.id.edit_password_patient)
    TextInputEditText editPassword;
    @BindView(R.id.edit_confirm_password_patient)
    TextInputEditText editConfirmPassword;
    @BindView(R.id.edit_diagnostic_patient)
    TextInputEditText editDiagnostic;
    @BindView(R.id.edit_date_diagnostic_patient)
    TextInputEditText editDateDiagnostic;
    @BindView(R.id.edit_observation_patient)
    TextInputEditText editObservation;
    @BindView(R.id.button_create_patient)
    MaterialButton btnSave;
    //Instance Patient
    Patient patient = new Patient();
    //Uri of the Image
    Uri uriImage;
    //endregion

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_add_patients,container,false);
        ButterKnife.bind(this, view);
        dropdownMenu(view);
        logicButtonSave();
        logicImageProfile();
        return view;
    }

    private void logicImageProfile() {
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to tour the gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Accept all kinds of images
                intent.setType("image/*");
                //If you have several types of viewers, it will ask which one to start with
                startActivityForResult(intent.createChooser(intent,getResources().getString(R.string.select_photo)),10);
            }
        });
    }

    private void logicButtonSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPojoPatients();
                PatientsManager patientsManager = new PatientsManager();//Instance PatientsManager
                ImageManager imageManager = new ImageManager();//Instance ImageManager
                imageManager.uploadImageToStorage(uriImage,patient);
                boolean rta = patientsManager.createPatient(patient);
                if (rta){
                    Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.not_saved), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setPojoPatients() {
        //region Set data to Pojo Patients
        patient.setFirstName(editName.getText().toString());
        patient.setLastName(editLastName.getText().toString());
        patient.setIdentificationType(autoCompletIdType.getText().toString());
        patient.setIdentification(editIdentification.getText().toString());
        //region Obtiene la selección del RadioGroup
        int radioButtonId = rgGender.getCheckedRadioButtonId();
        View radioButton = rgGender.findViewById(radioButtonId);
        int indice = rgGender.indexOfChild(radioButton);
        RadioButton rb = (RadioButton)  rgGender.getChildAt(indice);
        String seleccionRG = rb.getText().toString();
        //endregion
        patient.setGender(seleccionRG);
        patient.setBirthday(editBirthday.getText().toString());
        patient.setPhoneNumber(Long.parseLong(editPhone.getText().toString()));
        patient.setDeparment(autoCompletDepartment.getText().toString());
        patient.setNativeCity(editNativeCity.getText().toString());
        patient.setActualCity(editActualCity.getText().toString());
        patient.setAddress(editaddress.getText().toString());
        patient.setEmail(editEmail.getText().toString());
        patient.setUserName(editUser.getText().toString());
        patient.setPassword(editPassword.getText().toString());
        patient.setConfirmPassword(editConfirmPassword.getText().toString());
        patient.setDiagnostic(editDiagnostic.getText().toString());
        patient.setDateDiagnostic(editDateDiagnostic.getText().toString());
        patient.setObservations(editObservation.getText().toString());
        Map<String, Object> assigns = new HashMap<>();
        assigns.put("id", "1061755715");
        patient.setAssigns(assigns);
        //endregion
    }

    private void dropdownMenu(View view) {
        String typeId1 = getResources().getString(R.string.citizenship_card);
        String typeId2 = getResources().getString(R.string.foreign_identity_card);
        String typeId3 = getResources().getString(R.string.passport);
        String[] documentos = new String[] {typeId1, typeId2,typeId3};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        autoCompletIdType.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        autoCompletDepartment.setAdapter(adapter2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       uriImage = data.getData();
       profileImage.setImageURI(uriImage);
    }
}
