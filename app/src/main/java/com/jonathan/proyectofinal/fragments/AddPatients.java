package com.jonathan.proyectofinal.fragments;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.ImageManager;
import com.jonathan.proyectofinal.database.PatientsManager;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragment;

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
    @BindView(R.id.hp_iv_date_birth)
    ImageView ibCalendar;
    @BindView(R.id.hp_createps_til_date_birth)
    TextInputEditText dateOfBirthET;
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
    @BindView(R.id.edit_iv_date_diagnosis)
    ImageView ivDateDiagnosis;
    @BindView(R.id.edit_observation_patient)
    TextInputEditText editObservation;
    @BindView(R.id.button_create_patient)
    MaterialButton btnSave;
    String nameSring,lastNameString,typeIDString, idString, birthDayString, deparmentString, nativeCityString,
    actualCityString, addressString, emailString, userString, passwordString, confirmPasswordString,
    diagnosticString, dateDiagnosticString,observationString, seleccionRG, phoneString;
    //Instance Patient
    Patient patient = new Patient();
    //Uri of the Image
    Uri uriImage;
    public static final int REQUEST_CODE2 = 10;
    //Variable for datepicker date of birth
    String selectedDate;
    public static final int REQUEST_CODE = 11;
    //Variable for all datepicker date of diagnosis
    public  static final int REQUEST_CODE1 = 12;
    //Variables for all datepicker
    private OnFragmentInteractionListener mListener;
    boolean flag = true;
    //endregion

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_add_patients,container,false);
        ButterKnife.bind(this, view);
        dropdownMenu(view);
        logicButtonSave();
        logicImageProfile();
        logicButtonCalendar(view);
        logicButtonDateDiagnosis(view);
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
                startActivityForResult(intent.createChooser(intent,getResources().getString(R.string.select_photo)),REQUEST_CODE2);
            }
        });
    }

    private void logicButtonSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag2 = setPojoPatients();
                if (flag2) {
                    PatientsManager patientsManager = new PatientsManager();//Instance PatientsManager
                    ImageManager imageManager = new ImageManager();//Instance ImageManager
                    imageManager.uploadImageToStorage(uriImage, patient);
                    boolean rta = patientsManager.createPatient(patient);
                    if (rta) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.not_saved), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean setPojoPatients() {
        //region get text of form
        nameSring = editName.getText().toString();
        lastNameString = editLastName.getText().toString();
        typeIDString = autoCompletIdType.getText().toString();
        idString = editIdentification.getText().toString();
        //region Get the selection of RadioGroup
        if (rgGender.getCheckedRadioButtonId() != -1) {
            int radioButtonId = rgGender.getCheckedRadioButtonId();
            View radioButton = rgGender.findViewById(radioButtonId);
            int indice = rgGender.indexOfChild(radioButton);
            RadioButton rb = (RadioButton)  rgGender.getChildAt(indice);
            seleccionRG = rb.getText().toString();
        }
        //endregion
        birthDayString = dateOfBirthET.getText().toString();
        phoneString = editPhone.getText().toString();
        deparmentString = autoCompletDepartment.getText().toString();
        nativeCityString = editNativeCity.getText().toString();
        actualCityString = editActualCity.getText().toString();
        addressString = editaddress.getText().toString();
        emailString = editEmail.getText().toString();
        userString = editUser.getText().toString();
        passwordString = editPassword.getText().toString();
        confirmPasswordString = editConfirmPassword.getText().toString();
        diagnosticString = editDiagnostic.getText().toString();
        dateDiagnosticString = editDateDiagnostic.getText().toString();
        observationString = editObservation.getText().toString();
        //endregion

        //region conditional for fields is empty
        if (!nameSring.isEmpty()&&!lastNameString.isEmpty()&&!typeIDString.isEmpty()&&!idString.isEmpty()&&
        !seleccionRG.isEmpty()&&!birthDayString.isEmpty()&&!phoneString.isEmpty()&&!deparmentString.isEmpty()
        &&!nativeCityString.isEmpty()&&!actualCityString.isEmpty()&&!addressString.isEmpty()&&!emailString.isEmpty()
        &&!userString.isEmpty()&&!passwordString.isEmpty()&&!confirmPasswordString.isEmpty()&&!diagnosticString.isEmpty()
        &&!dateDiagnosticString.isEmpty()&&!observationString.isEmpty()) {
            //region Set data to Pojo Patients
            patient.setFirstName(nameSring);
            patient.setLastName(lastNameString);
            patient.setIdentificationType(typeIDString);
            patient.setIdentification(idString);
            patient.setGender(seleccionRG);
            patient.setBirthday(birthDayString);
            patient.setPhoneNumber(Long.parseLong(phoneString));
            patient.setDeparment(deparmentString);
            patient.setNativeCity(nativeCityString);
            patient.setActualCity(actualCityString);
            patient.setAddress(addressString);
            patient.setEmail(emailString);
            patient.setUserName(userString);
            patient.setPassword(passwordString);
            patient.setConfirmPassword(confirmPasswordString);
            patient.setDiagnostic(diagnosticString);
            patient.setDateDiagnostic(dateDiagnosticString);
            patient.setObservations(observationString);
            Map<String, Object> assigns = new HashMap<>();
            assigns.put("id", "1061755715");
            patient.setAssigns(assigns);
            //endregion
        }else{
            flag = false;
        }
        //endregion

        return flag;
    }

    private void logicButtonCalendar(View view) {
        // Get the fragment manager so they can start from the fragment
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();

        // Using an onclick listener in TextInputEditText to display datePicker
        ibCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // Set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddPatients.this, REQUEST_CODE);
                // Show the widget
                newFragment.show(fm, "datePicker");
            }
        });
    }

    private void logicButtonDateDiagnosis(View view) {
        // Get the snippet manager so they can start from the snippet
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();

        // Using an onclick listener in TextInputEditText to display datePicker
        ivDateDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // Set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddPatients.this, REQUEST_CODE1);
                // Show the widget
                newFragment.show(fm, "datePicker");
            }
        });
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

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            // Establece el valor de editText
            dateOfBirthET.setText(selectedDate);
        } else if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            editDateDiagnostic.setText(selectedDate);
        } else if (requestCode == REQUEST_CODE2 && resultCode == Activity.RESULT_OK){
            uriImage = data.getData();
            profileImage.setImageURI(uriImage);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + e + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(Uri uri);
    }
}
