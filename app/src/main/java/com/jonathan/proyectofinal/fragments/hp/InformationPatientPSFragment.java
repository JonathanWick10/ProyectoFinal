package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavType;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationPatientPSFragment extends Fragment {

    @BindView(R.id.tv_ps_patientName)
    TextView tvPatientName;
    @BindView(R.id.tv_ps_agePatient)
    TextView tvAgePatient;
    @BindView(R.id.tv_ps_genderPatient)
    TextView tvGenderPatient;
    @BindView(R.id.tv_ps_nativeCityPatient)
    TextView tvNativeCityPatient;
    @BindView(R.id.tv_ps_currentcityPatient)
    TextView tvCurrentCityPatient;
    @BindView(R.id.tv_ps_guestPhonePatient)
    TextView tvGuestPhonePatient;
    @BindView(R.id.tv_ps_addressPatient)
    TextView tvAddressPatient;
    @BindView(R.id.tv_ps_diagnosis)
    TextView tvDiagnosis;
    @BindView(R.id.tv_ps_diagnosisdate)
    TextView tvDiagnosisDate;

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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();

    }

    public void initDatas() {
        tvAgePatient.setText("" +patient.getAge()+" años");
        tvGenderPatient.setText("" +patient.getGender());
        tvNativeCityPatient.setText("" +patient.getNativeCity());
        tvCurrentCityPatient.setText("" +patient.getActualCity());
        tvGuestPhonePatient.setText("" +patient.getPhoneNumber());
        tvAddressPatient.setText("" + patient.getAddress());
        tvDiagnosis.setText("" +patient.getDiagnostic());
        tvDiagnosisDate.setText("" +patient.getDateDiagnostic());
        tvPatientName.setText(patient.getFirstName()+" "+patient.getLastName());
    }


}
