package com.jonathan.proyectofinal.fragments.hp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavType;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.AddPatients;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragment;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragmentDateOfBirth;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InformationPatientPSFragment extends Fragment {

    private IMainCarer mIMainCarer;
    private AddPatients.OnFragmentInteractionListener mListener;

    @BindView(R.id.tv_ps_patientName)
    TextView tvPatientName;
    @BindView(R.id.tv_ps_namePatient)
    TextView tvNamePatient;
    @BindView(R.id.tv_ps_lastnamePatient)
    TextView tvLastNamePatient;
    @BindView(R.id.tv_ps_idtypePatient)
    TextView tvIdTypePatient;
    @BindView(R.id.tv_ps_identificationPatient)
    TextView tvIdentificationPatient;
    @BindView(R.id.tv_ps_genderPatient)
    TextView tvGenderPatient;
    @BindView(R.id.tv_ps_datebirthPatient)
    TextView tvDateBitthPatient;
    /*
    @BindView(R.id.tv_ps_agePatient)
    TextView tvAgePatient;
    */
    @BindView(R.id.tv_ps_nativeCityPatient)
    TextView tvNativeCityPatient;
    @BindView(R.id.tv_ps_departmentPatient)
    TextView tvDepartmentPatient;
    @BindView(R.id.tv_ps_currentcityPatient)
    TextView tvCurrentCityPatient;
    @BindView(R.id.tv_ps_guestPhonePatient)
    TextView tvGuestPhonePatient;
    @BindView(R.id.tv_ps_addressPatient)
    TextView tvAddressPatient;
    @BindView(R.id.tv_ps_emaiPatient)
    TextView tvEmailPatient;
    @BindView(R.id.tv_ps_userPatient)
    TextView tvUserPatient;
    @BindView(R.id.tv_ps_passPatient)
    TextView tvPassPatient;
    @BindView(R.id.tv_ps_diagnosisPatient)
    TextView tvDiagnosis;
    @BindView(R.id.tv_ps_diagnosisdatePatient)
    TextView tvDiagnosisDate;
    @BindView(R.id.tv_ps_observationsPatient)
    TextView tvObservationsDate;
    @BindView(R.id.btn_edit_cancel)
    MaterialButton btnEditCancel;
    @BindView(R.id.btn_save)
    MaterialButton btnSave;

    @BindView(R.id.ll_view_data)
    LinearLayout linear_view;
    @BindView(R.id.ll_edit_data)
    LinearLayout linear_edit;

    @BindView(R.id.edit_til_name_patient)
    TextInputLayout tilNamePatient;
    @BindView(R.id.edit_til_password_patient)
    TextInputLayout tilPasswordPatient;

    @BindView(R.id.edit_txt_name_patient)
    TextInputEditText txtNamePatient;
    @BindView(R.id.edit_txt_lastname_patient)
    TextInputEditText txtLastNamePatient;
    @BindView(R.id.edit_txt_idtype_patient)
    AutoCompleteTextView txtIdTypePatient;
    @BindView(R.id.edit_txt_identification_patient)
    TextInputEditText txtIdentificationPatient;
    @BindView(R.id.edit_rg_gender_patient)
    RadioGroup rgGenderPatient;
    @BindView(R.id.edit_rb_female_patient)
    RadioButton rbGenderFemalePatient;
    @BindView(R.id.edit_rb_male_patient)
    RadioButton rbGenderMalePatient;
    @BindView(R.id.edit_txt_datebirth_patient)
    TextInputEditText txtDateBirthPatient;
    @BindView(R.id.edit_iv_datebirth_patient)
    CircleImageView ivDateBirthCalendar;
    @BindView(R.id.edit_txt_nativecity_patient)
    TextInputEditText txtNativeCityPatient;
    @BindView(R.id.edit_txt_phone_patient)
    TextInputEditText txtPhonePatient;
    @BindView(R.id.edit_txt_department_patient)
    AutoCompleteTextView txtDepartmentPatient;
    @BindView(R.id.edit_txt_actualcity_patient)
    TextInputEditText txtActualCityPatient;
    @BindView(R.id.edit_txt_address_patient)
    TextInputEditText txtAddressPatient;
    @BindView(R.id.edit_txt_email_patient)
    TextInputEditText txtEmailPatient;
    @BindView(R.id.edit_txt_user_patient)
    TextInputEditText txtUserPatient;
    @BindView(R.id.edit_txt_password_patient)
    TextInputEditText txtPasswordPatient;
    @BindView(R.id.edit_txt_diagnostic_patient)
    TextInputEditText txtDiagnosisPatient;
    @BindView(R.id.edit_txt_datediagnosis_patient)
    TextInputEditText txtDateDiagnosisPatient;
    @BindView(R.id.edit_iv_datediagnosis_patient)
    CircleImageView ivDateDiagnosisCalendar;
    @BindView(R.id.edit_txt_observations_patient)
    TextInputEditText txtObservationsPatient;

    public static final int REQUEST_CODE = 11;
    public  static final int REQUEST_CODE1 = 12;
    String selectedDate;

    Patient patient = new Patient();
    FirebaseFirestore db;

    public InformationPatientPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_ps_information_patient, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle!=null){
            patient = (Patient) bundle.getSerializable("patient");
            initDatas();
        }
        logicButtonCalendar(view);
        logicButtonDateDiagnosis(view);
        dropdownMenu(view);
        verifiFieds();
        return view;
    }

    private void verifiFieds() {
        txtPasswordPatient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilPasswordPatient.setError(null);
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
                String email = txtPasswordPatient.getText().toString().trim();
                if (email.length() < 7) {
                    tilPasswordPatient.setError(getString(R.string.val_min_passwornd));
                    data = false;
                }
                break;
        }
        return data;
    }

    private void logicButtonCalendar(View view) {
        final FragmentManager fm = (getFragmentManager());

        ivDateBirthCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new DatePickerFragmentDateOfBirth();
                newFragment.setTargetFragment(InformationPatientPSFragment.this, REQUEST_CODE);
                newFragment.show(fm, "datePicker");
            }
        });
    }

    private void logicButtonDateDiagnosis(View view) {
        final FragmentManager fm = (getFragmentManager());

        ivDateDiagnosisCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(InformationPatientPSFragment.this, REQUEST_CODE1);
                newFragment.show(fm, "datePicker");
            }
        });
    }

    @OnClick(R.id.btn_edit_cancel)
    void logicBtnEditCancel(){
        if (linear_view.getVisibility() == View.VISIBLE){
            linear_view.setVisibility(View.GONE);
            linear_edit.setVisibility(View.VISIBLE);

            btnEditCancel.setIcon(getResources().getDrawable(R.drawable.ic_cancel_black));
            btnEditCancel.setText(getResources().getString(R.string.cancel));
            btnEditCancel.setIconTintResource(R.color.red);
            btnEditCancel.setTextColor(getResources().getColor(R.color.red));
            btnSave.setVisibility(View.VISIBLE);

        } else {
            linear_view.setVisibility(View.VISIBLE);
            linear_edit.setVisibility(View.GONE);

            btnEditCancel.setIcon(getResources().getDrawable(R.drawable.ic_edit_black));
            btnEditCancel.setText(getResources().getString(R.string.update));
            btnEditCancel.setIconTintResource(R.color.colorPrimaryDark);
            btnEditCancel.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            btnSave.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            // Establece el valor de editText
            txtDateBirthPatient.setText(selectedDate);
        } else if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            txtDateDiagnosisPatient.setText(selectedDate);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();

    }

    public void initDatas() {
        //tvAgePatient.setText("" +patient.getAge()+" años");
        tvGenderPatient.setText("" +patient.getGender());
        tvNativeCityPatient.setText("" +patient.getNativeCity());
        tvCurrentCityPatient.setText("" +patient.getActualCity());
        tvGuestPhonePatient.setText("" +patient.getPhoneNumber());
        tvAddressPatient.setText("" + patient.getAddress());
        tvDiagnosis.setText("" +patient.getDiagnostic());
        tvDiagnosisDate.setText("" +patient.getDateDiagnostic());
        tvPatientName.setText(patient.getFirstName()+" "+patient.getLastName());
    }

    private void dropdownMenu(View view) {
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

        String[] documentos = {getResources().getString(R.string.citizenship_card), getResources().getString(R.string.foreign_identity_card), getResources().getString(R.string.passport)};

        String[] departamentos = new String[] {typeId1, typeId2, typeId3, typeId4, typeId5, typeId6, typeId7, typeId8, typeId9, typeId10,};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        txtIdTypePatient.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, departamentos);
        txtDepartmentPatient.setAdapter(adapter2);
    }

}
