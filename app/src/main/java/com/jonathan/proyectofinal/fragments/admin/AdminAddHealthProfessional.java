package com.jonathan.proyectofinal.fragments.admin;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAddHealthProfessional extends Fragment{

    private View view;
    @BindView(R.id.admin_iv_date_birth)
    ImageView ibCalendar;
    @BindView(R.id.admin_createps_til_date_birth)
    TextInputEditText dateOfBirthET;
    @BindView(R.id.admin_createps_btn_save)
    MaterialButton btnSave;
    String selectedDate;
    public static final int REQUEST_CODE = 11;
    private OnFragmentInteractionListener mListener;

    public AdminAddHealthProfessional() {
        // Required empty public constructor
    }

    /*
    public static AdminAddHealthProfessional newInstance(){
        AdminAddHealthProfessional adminAddHealthProfessional = new AdminAddHealthProfessional();
        return adminAddHealthProfessional;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_add_health_professional, container, false);
        ButterKnife.bind(this, view);
        dropdownMenu(view);
        logicButtonCalendar(view);
        return view;
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
                newFragment.setTargetFragment(AdminAddHealthProfessional.this, REQUEST_CODE);
                // Show the widget
                newFragment.show(fm, "datePicker");
            }
        });
    }

    @OnClick(R.id.admin_createps_btn_save)
    public void logicButtonSave(View view){

    }

    private void dropdownMenu(View view) {
        // Filling drop-down list for document type
        String[] documentos = new String[] {getResources().getString(R.string.citizenship_card), getResources().getString(R.string.foreign_identity_card), getResources().getString(R.string.passport)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, documentos);
        AutoCompleteTextView tipoIdentificacion = view.findViewById(R.id.admin_identification_type);
        tipoIdentificacion.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            dateOfBirthET.setText(selectedDate);
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
