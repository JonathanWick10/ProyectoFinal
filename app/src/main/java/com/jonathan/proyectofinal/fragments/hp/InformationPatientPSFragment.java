package com.jonathan.proyectofinal.fragments.hp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;

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

    String firstName="";
    String lastName="";
    String agePatient="";
    String genderPatient="";
    String nativeCityPatient="";
    String currentCityPatient="";
    String guestPhonePatient="";
    String addressPatient="";
    String diagnosis="";
    String diagnosisDate="";
    String identificationType="";
    String identification="";
    String birthday="";
    long phoneNumber=0;
    String email="";
    String observations="";
    String emergencyNumber="";

    public InformationPatientPSFragment() {
    }


    /*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ps_information_patient, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        Bundle bundle = getArguments();
        String uID = bundle.getString("patientUID");

        return view;

    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_ps_information_patient, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        Bundle bundle = getArguments();
        if (bundle!=null){
            String uID = bundle.getString("patientUID");
        }
        return view;
    }

    public void initDatas() {
       agePatient="25";
       genderPatient="Femenino";
       nativeCityPatient="Cali";
       currentCityPatient="Popayán";
       guestPhonePatient="312 252 1155";
       addressPatient="Calle 7N";
       diagnosis="El paciente tiene Alzheimer";
       diagnosisDate="15 - 10 - 2014";
       firstName="Carolina";
       lastName="Valencia";
       identificationType="Cedula de ciudadania";
       identification="00000000";
       birthday="15 - 10 - 1993";
       phoneNumber=5728369;
       email="abcdes@gmail.com";
       observations="Paciente con";
       emergencyNumber="123";


        tvAgePatient.setText("" +agePatient+" años");
        tvGenderPatient.setText("" +genderPatient);
        tvNativeCityPatient.setText("" +nativeCityPatient);
        tvCurrentCityPatient.setText("" +currentCityPatient);
        tvGuestPhonePatient.setText("" +guestPhonePatient);
        tvAddressPatient.setText("" +addressPatient);
        tvDiagnosis.setText("" +diagnosis);
        tvDiagnosisDate.setText("" +diagnosisDate);
        tvPatientName.setText(firstName+" "+lastName);

    }


}
