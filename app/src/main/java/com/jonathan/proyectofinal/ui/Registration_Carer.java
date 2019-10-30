package com.jonathan.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.AddPatients;
import com.jonathan.proyectofinal.fragments.general.DatePickerFragment;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Registration_Carer extends AppCompatActivity {

    @BindView(R.id.toolbar_registration_carer)
    MaterialToolbar toolbar;


    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.admin_createps_til_name)
    TextInputEditText editName;
    @BindView(R.id.admin_createps_til_lastname)
    TextInputEditText editLastName;
    @BindView(R.id.admin_identification_type)
    AutoCompleteTextView autoCompletIdType;
    @BindView(R.id.admin_createps_til_documet)
    TextInputEditText editIdentification;
    @BindView(R.id.admin_createps_rg_gender)
    RadioGroup rgGender;
    @BindView(R.id.admin_iv_date_birth)
    ImageView ibCalendar;
    @BindView(R.id.admin_createps_til_date_birth)
    TextInputEditText dateOfBirthET;
    @BindView(R.id.admin_createps_til_native_city)
    TextInputEditText editNativeCity;
    @BindView(R.id.admin_createps_til_telephone)
    TextInputEditText editPhone;
    @BindView(R.id.admin_createps_address)
    TextInputEditText editaddress;
    @BindView(R.id.admin_createps_til_city)
    TextInputEditText editActualCity;
    @BindView(R.id.admin_create_add_til_email)
    TextInputEditText editEmail;
    @BindView(R.id.admin_create_add_til_user)
    TextInputEditText editUser;
    @BindView(R.id.admin_create_add_til_pass)
    TextInputEditText editPassword;
    @BindView(R.id.admin_createps_til_profession)
    TextInputEditText professionHp;
    @BindView(R.id.admin_create_add_workplace)
    TextInputEditText workPlaceHp;
    @BindView(R.id.admin_createps_btn_save)
    MaterialButton btnSave;

    String nameSring,lastNameString,typeIDString, idString, birthDayString, nativeCityString,
            actualCityString, addressString, emailString, userString, passwordString, seleccionRG
            , phoneString, profession, workC;
    //Uri of the Image
    Uri uriImage;
    public static final int REQUEST_CODE2 = 10;
    //Variable for datepicker date of birth
    String selectedDate;
    public static final int REQUEST_CODE = 11;
    //Variable for all datepicker date of diagnosis
    public  static final int REQUEST_CODE1 = 12;
    //Variables for all datepicker
    boolean flag = true;
    FirebaseAuth auth;
    FirebaseUser users;
    FirebaseFirestore db;
    StorageReference storageReference;
    String uIDCarer;
    Carer carer = new Carer();
    ProgressDialog progressDialog;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__carer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(Registration_Carer.this);
        dropdownMenu();
        logicButtonSave();
        logicImageProfile();
        logicButtonCalendar();
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
                    progressDialog.setMessage("Realizando registro en línea");
                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(carer.getEmail(),carer.getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    auth.signOut();
                                }
                            });

                    auth.signInWithEmailAndPassword(carer.getEmail(), carer.getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        AuthResult itask = task.getResult();
                                        FirebaseUser ures=itask.getUser();
                                        uIDCarer = ures.getUid();
                                        carer.setCarerUId(uIDCarer);
                                        if (uriImage!=null){
                                            uploadImageToStorage(uriImage, carer);
                                        }
                                        db.collection(Constants.Carers).document(carer.getCarerUId()).set(carer)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(Registration_Carer.this, getResources().getString(R.string.was_saved_succesfully), Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Registration_Carer.this,MainCarer.class);
                                                        startActivity(intent);
                                                        progressDialog.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("message: ", e.toString());
                                                    }
                                                });
                                    }
                                }
                            });
                }else{
                    Toast.makeText(Registration_Carer.this, getResources().getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadImageToStorage(Uri uriImage, final Carer carer) {
        StorageReference imgRef = storageReference.child("Users/Carers/"+carer.getCarerUId()+".jpg");
        imgRef.putFile(uriImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();
                        carer.setUriImg(url);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

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
        nativeCityString = editNativeCity.getText().toString();
        actualCityString = editActualCity.getText().toString();
        addressString = editaddress.getText().toString();
        emailString = editEmail.getText().toString();
        userString = editUser.getText().toString();
        passwordString = editPassword.getText().toString();
        profession = professionHp.getText().toString();
        workC = workPlaceHp.getText().toString();
        //endregion

        //region conditional for fields is empty
        if (!nameSring.isEmpty()&&!lastNameString.isEmpty()&&!typeIDString.isEmpty()
            &&!idString.isEmpty()&&!seleccionRG.isEmpty()&&!birthDayString.isEmpty()
            &&!phoneString.isEmpty()&&!nativeCityString.isEmpty()&&!actualCityString.isEmpty()
            &&!addressString.isEmpty()&&!emailString.isEmpty()&&!userString.isEmpty()
            &&!passwordString.isEmpty()&&!profession.isEmpty()&&!workC.isEmpty()) {
            //region Set data to Pojo Patients
            carer.setFirstName(nameSring);
            carer.setLastName(lastNameString);
            carer.setIdentificationType(typeIDString);
            carer.setIdentification(idString);
            carer.setGender(seleccionRG);
            carer.setBirthday(birthDayString);
            carer.setPhoneNumber(Long.parseLong(phoneString));
            carer.setNativeCity(nativeCityString);
            carer.setActualCity(actualCityString);
            carer.setAddress(addressString);
            carer.setEmail(emailString);
            carer.setUserName(userString);
            carer.setPassword(passwordString);
            carer.setProfession(profession);
            carer.setEmploymentPlace(workC);
            carer.setRole(Constants.Carers);
            /*String[] assignsArray = {users.getUid()};
            List<String> assigns = Arrays.asList(assignsArray);
            carer.setAs(assigns);*/
            //endregion
        }else{
            flag = false;
        }
        //endregion

        return flag;
    }

    private void logicButtonCalendar() {
        // Using an onclick listener in TextInputEditText to display datePicker
        ibCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registration_Carer.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateOfBirthET.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void dropdownMenu() {

        String[] documentos = {"Cédula de ciudadanía", "Cédula de extranjería", "Pasaporte"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Registration_Carer.this, R.layout.dropdown_menu_popup_item, documentos);
        autoCompletIdType.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK){
            selectedDate = data.getStringExtra("selectedDate");
            // Establece el valor de editText
            dateOfBirthET.setText(selectedDate);
        } else*/ if (requestCode == REQUEST_CODE2 && resultCode == Activity.RESULT_OK){
            uriImage = data.getData();
            if (uriImage != null ){
                Glide.with(Registration_Carer.this).load(uriImage).fitCenter().into(profileImage);
            }
            //profileImage.setImageURI(uriImage);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
