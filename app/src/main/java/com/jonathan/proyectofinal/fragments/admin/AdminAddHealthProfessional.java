package com.jonathan.proyectofinal.fragments.admin;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
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
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Admin;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.database.HPManager;
import com.jonathan.proyectofinal.database.LoginManager;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragment;
import com.jonathan.proyectofinal.tools.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAddHealthProfessional extends Fragment{

    //region Variables
    private View view;
    @BindView(R.id.admin_createps_til_name)
    TextInputEditText firstNameHp;
    @BindView(R.id.admin_createps_til_lastname)
    TextInputEditText lastNameHp;
    @BindView(R.id.admin_identification_type)
    AutoCompleteTextView typeIdHp;
    @BindView(R.id.admin_createps_til_documet)
    TextInputEditText idHp;
    @BindView(R.id.admin_createps_rg_gender)
    RadioGroup genderHp;
    @BindView(R.id.admin_iv_date_birth)
    ImageView ibCalendar;
    @BindView(R.id.admin_createps_til_date_birth)
    TextInputEditText dateOfBirthET;
    @BindView(R.id.admin_createps_til_native_city)
    TextInputEditText nativeHP;
    @BindView(R.id.admin_createps_til_telephone)
    TextInputEditText phoneHp;
    @BindView(R.id.admin_createps_address)
    TextInputEditText addressHp;
    @BindView(R.id.admin_createps_til_city)
    TextInputEditText actualCityHp;
    @BindView(R.id.admin_create_add_til_email)
    TextInputEditText emailHp;
    @BindView(R.id.admin_create_add_til_user)
    TextInputEditText userHp;
    @BindView(R.id.admin_create_add_til_pass)
    TextInputEditText passHp;
    @BindView(R.id.admin_createps_til_profession)
    TextInputEditText professionHp;
    @BindView(R.id.admin_create_add_workplace)
    TextInputEditText workPlaceHp;
    @BindView(R.id.admin_createps_btn_save)
    MaterialButton btnSave;
    String selectedDate;
    public static final int REQUEST_CODE = 11;
    private OnFragmentInteractionListener mListener;
    HealthcareProfessional hp = new HealthcareProfessional();
    String firstName, lastName, typeId, ident, gender, birthdate, nativeCity, phone, address,
            actualCity, email, userHealth, pass, profession, workP;
    boolean flag = false;
    LoginManager loginManager = new LoginManager();
    Admin admin = new Admin();
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    String role;
    String uIDAdmin;
    String uIDHP;
    //endregion

    public AdminAddHealthProfessional() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_add_health_professional, container, false);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uIDAdmin = firebaseUser.getUid();
        db = FirebaseFirestore.getInstance();
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
        boolean flag2 = setPojoHp();
        if (flag2) {
            //_____________________________________________________________________________________________________

            firebaseAuth.createUserWithEmailAndPassword(hp.getEmail(),hp.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                firebaseAuth.signOut();
                            }
                        }
                    });

            firebaseAuth.signInWithEmailAndPassword(hp.getEmail(), hp.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                AuthResult itask = task.getResult();
                                FirebaseUser ures=itask.getUser();
                                uIDHP = ures.getUid();
                                hp.setHpUID(uIDHP);
                                db.collection(Constants.HealthcareProfesional).document(hp.getHpUID()).set(hp)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getActivity(), getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("message", e.toString());
                                            }
                                        });
                            }
                        }
                    });

            firebaseAuth.signOut();

            db.collection(Constants.Adminds).document(uIDAdmin).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                admin= documentSnapshot.toObject(Admin.class);
                                firebaseAuth.signInWithEmailAndPassword(admin.getEmail(), admin.getPassword())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                Toast.makeText(getActivity(), "accedio de nuevo", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });

            //_________________________________________________________________________________________________________

        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean setPojoHp() {
        firstName = firstNameHp.getText().toString();
        lastName = lastNameHp.getText().toString();
        typeId = typeIdHp.getText().toString();
        ident = idHp.getText().toString();
        //region Get the selection of RadioGroup
        if (genderHp.getCheckedRadioButtonId() != -1) {
            int radioButtonId = genderHp.getCheckedRadioButtonId();
            View radioButton = genderHp.findViewById(radioButtonId);
            int indice = genderHp.indexOfChild(radioButton);
            RadioButton rb = (RadioButton)  genderHp.getChildAt(indice);
            gender = rb.getText().toString();
        }
        //endregion
        birthdate = dateOfBirthET.getText().toString();
        nativeCity = nativeHP.getText().toString();
        phone = phoneHp.getText().toString();
        address = addressHp.getText().toString();
        actualCity = actualCityHp.getText().toString();
        email = emailHp.getText().toString();
        userHealth = userHp.getText().toString();
        pass = passHp.getText().toString();
        profession = professionHp.getText().toString();
        workP = workPlaceHp.getText().toString();

        if (!firstName.isEmpty() && !lastName.isEmpty() && !typeId.isEmpty() && !ident.isEmpty() &&
                !gender.isEmpty() && !birthdate.isEmpty() && !nativeCity.isEmpty() && !phone.isEmpty() &&
                !address.isEmpty() && !actualCity.isEmpty() && !email.isEmpty() && !userHealth.isEmpty() &&
                !pass.isEmpty() && !profession.isEmpty() && !workP.isEmpty()){
            hp.setFirstName(firstName);
            hp.setLastName(lastName);
            hp.setIdentificationType(typeId);
            hp.setIdentification(ident);
            hp.setGender(gender);
            hp.setBirthday(birthdate);
            hp.setNativeCity(nativeCity);
            hp.setPhoneNumber(Long.parseLong(phone));
            hp.setAddress(address);
            hp.setActualCity(actualCity);
            hp.setEmail(email);
            hp.setUserName(userHealth);
            hp.setPassword(pass);
            hp.setProfession(profession);
            hp.setEmployment_place(workP);
            hp.setRole(Constants.HealthcareProfesional);

            return  flag = true;
        }else {
            return flag = false;
        }
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