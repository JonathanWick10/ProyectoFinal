package com.jonathan.proyectofinal.fragments.hp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.base.Strings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MedicinesAdapter;
import com.jonathan.proyectofinal.data.AlertReceiver;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import com.jonathan.proyectofinal.data.Mensage;
import com.jonathan.proyectofinal.data.MensagesContent;
import com.jonathan.proyectofinal.data.NotificationData;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NotificationPSFragment extends Fragment {

    //region codigo de ella
    public final Calendar calendarInstance = Calendar.getInstance();
    int hourCalendar, minuteCalendar;
    private Switch simpleSwitchBtn;
    private TextView switchBtn_txtView;
    private String[] arrayUnitTime;
    private ArrayAdapter adapter;

    private NotificationData notificationData;
    private AlarmManager alarmManager;

    //endregion

    private Patient patient;
    //Views
    @BindView(R.id.floating_medicine_add)
    FloatingActionButton fabAddMedicine;
    @BindView(R.id.rc_medicine)
    RecyclerView rcMedicine;
    @BindView(R.id.txt_no_register)
    TextView txtNoRegister;
    //Views Alert
    TextView txtStartHour;
    CircleImageView imgMedicine;
    //Strings
    String nameMedicine, descriptionMedicine, doseMedicine, frequencyMedicine, startHourMedicine = "";
    public static final int REQUEST_CODE2 = 10;
    //Instance MedicationAssignament
    MedicationAssignment medicationAssignment = new MedicationAssignment();
    //Uri Img
    Uri uriImage;
    //FireBase
    FirebaseFirestore db;
    StorageReference storageReference;
    //adapter
    MedicinesAdapter adapterMedicine;
    MedicinesAdapter.IMedicinesClick iMedicinesClick;
    AutoCompleteTextView editFrequencyMedicine;

    //region Build
    public NotificationPSFragment() {
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_notification, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences preferences = getActivity().getPreferences(0);
        Gson gson = new Gson();
        String json = preferences.getString("serialipatient", "");
        patient = gson.fromJson(json, Patient.class);

        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        eventLogicClick();
        initRecycler();
        //region Alarm
        notificationData = new NotificationData();
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        arrayUnitTime = getResources().getStringArray(R.array.unit_time);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayUnitTime);
        // spinnerUnitTime.setAdapter(adapter);

        //endregion

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //reference();
        adapterMedicine.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterMedicine.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterMedicine.stopListening();
    }

    private void dropdownMenu() {
        String[] correctAnswerArray = {"2 Minutos", "30 Minutos", "6 Horas", "8 Horas", "12 Horas", "48 Horas"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.dropdown_menu_popup_item, correctAnswerArray);
        editFrequencyMedicine.setAdapter(arrayAdapter);

    }

    private void eventLogicClick() {
        iMedicinesClick = new MedicinesAdapter.IMedicinesClick() {
            @Override
            public void clickSelectedItem(MedicationAssignment medicationAssignment) {
                alertDialogMedicine("update", medicationAssignment);
            }

            @Override
            public void clickDeleteItem(final MedicationAssignment medicationAssignment) {

                final AlertDialog alertDialog;

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // only for Lollipop and newer versions
                    try {
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                        builder.setView(dialogView);
                        alertDialog = builder.create();

                        Button btn1 = (Button) dialogView.findViewById(R.id.btn1);
                        btn1.setText(R.string.no);
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                alertDialog.dismiss();
                            }
                        });

                        Button btn2 = (Button) dialogView.findViewById(R.id.btn2);
                        btn2.setText(R.string.yes);
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                                        "Brainmher", "Eliminando registro en línea");
                                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                StorageReference deleteImage = storageReference.child(Constants.Medicines + "/" + medicationAssignment.getMedicamentUID() + ".jpg");
                                deleteImage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        db.collection(Constants.Medicines).document(patient.getPatientUID()).collection(Constants.Medicine)
                                                .document(medicationAssignment.getMedicamentUID()).delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        progressDialog.dismiss();
                                                        db.collection(Constants.Medicines).document(patient.getPatientUID()).delete();
                                                        progressDialog.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });

                                    }
                                });

                                alertDialog.dismiss();
                            }
                        });
                        TextView tvInformation = dialogView.findViewById(R.id.textView);
                        tvInformation.setText("Esta seguro de eliminar "+medicationAssignment.getMedicamentName());
                        alertDialog.show();


                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
        };
    }

    private void initRecycler() {


        rcMedicine.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcMedicine.setLayoutManager(new GridLayoutManager(getActivity(), 3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Medicines).document(patient.getPatientUID()).collection(Constants.Medicine);
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<MedicationAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MedicationAssignment>()
                .setQuery(query, MedicationAssignment.class).build();

        //Passing parameters to the adapter
        adapterMedicine = new MedicinesAdapter(firestoreRecyclerOptions,getActivity(), iMedicinesClick);
        adapterMedicine.notifyDataSetChanged();
        rcMedicine.setAdapter(adapterMedicine);
    }

    @OnClick({R.id.floating_medicine_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_medicine_add:
                MedicationAssignment masigEmpty = new MedicationAssignment();
                alertDialogMedicine("create", masigEmpty);
                break;
        }
    }

    private void alertDialogMedicine(final String option, final MedicationAssignment medicationAssignmentUpdate) {

        //region AlertDialog
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.item_add_medicine, null);//inflate view on alertdialog
                builder.setView(dialogView);
                alertDialog = builder.create();

                //reference of views
                final LinearLayout layoutActDesatc = dialogView.findViewById(R.id.layout_act_desact);
                final TextView title = dialogView.findViewById(R.id.txt_title_add_medicine);
                final TextInputEditText editNameMedicine = dialogView.findViewById(R.id.edit_name_medicine);
                final TextInputEditText editDescriptionMedicine = dialogView.findViewById(R.id.edit_desciption_medicine);
                final TextInputEditText editDoseMedicine = dialogView.findViewById(R.id.edit_dose_medicine);
                editFrequencyMedicine = dialogView.findViewById(R.id.edit_frequency);
                imgMedicine = dialogView.findViewById(R.id.img_medicine);
                ImageView imgStartHour = dialogView.findViewById(R.id.img_start_hour);
                txtStartHour = dialogView.findViewById(R.id.txt_start_hour);
                TextView txtStateSwitch = dialogView.findViewById(R.id.txt_switch);
                Switch switchState = dialogView.findViewById(R.id.switch_reminder);
                Button btnCancelar = dialogView.findViewById(R.id.btn_cancelar_medicine);
                Button btnSave = dialogView.findViewById(R.id.btn_save_medicine);


                dropdownMenu();

                //Init TimePicker
                imgStartHour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setStartHour().show();
                    }
                });

                //select img of the phone gallery
                imgMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent to tour the gallery
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        // Accept all kinds of images
                        intent.setType("image/*");
                        //If you have several types of viewers, it will ask which one to start with
                        startActivityForResult(intent.createChooser(intent, getResources().getString(R.string.select_photo)), REQUEST_CODE2);
                    }
                });

                btnCancelar.setText(getString(R.string.cancel));
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                if (option.equals("update")) {
                    title.setText("Actualizar Medicamento");
                    editNameMedicine.setText(medicationAssignmentUpdate.getMedicamentName());
                    editDescriptionMedicine.setText(medicationAssignmentUpdate.getMedicamentDescription());
                    editDoseMedicine.setText(medicationAssignmentUpdate.getDose());
                    editFrequencyMedicine.setText(medicationAssignmentUpdate.getFrequency());
                    txtStartHour.setText(medicationAssignmentUpdate.getHours());
                    txtStateSwitch.setText(medicationAssignmentUpdate.getStatement());
                    Glide.with(getActivity()).load(medicationAssignmentUpdate.getUriImg()).fitCenter().into(imgMedicine);
                    startHourMedicine = medicationAssignmentUpdate.getHours();
                    layoutActDesatc.setVisibility(View.VISIBLE);
                    if (medicationAssignmentUpdate.getStatement().equals("Activada")) {
                        switchState.setChecked(true);
                        medicationAssignment.setStatement("Activada");
                    } else {
                        switchState.setChecked(false);
                        medicationAssignment.setStatement("Desactivada");
                    }

                    switchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                medicationAssignment.setStatement("Activida");
                            } else {
                                medicationAssignment.setStatement("Desactivada");
                            }
                        }
                    });
                }

                btnSave.setText(getString(R.string.save));
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //set data to strings
                        nameMedicine = editNameMedicine.getText().toString();
                        descriptionMedicine = editDescriptionMedicine.getText().toString();
                        doseMedicine = editDoseMedicine.getText().toString();
                        frequencyMedicine = editFrequencyMedicine.getText().toString();


                        if (!nameMedicine.isEmpty() && !descriptionMedicine.isEmpty() && !doseMedicine.isEmpty()
                                && !frequencyMedicine.isEmpty() && !startHourMedicine.isEmpty() ) {
                            //set data pojo
                            medicationAssignment.setMedicamentName(nameMedicine);
                            medicationAssignment.setMedicamentDescription(descriptionMedicine);
                            medicationAssignment.setDose(doseMedicine);
                            medicationAssignment.setFrequency(frequencyMedicine);
                            medicationAssignment.setHours(startHourMedicine);

                            if (option.equals("create")) {

                                if(uriImage != null) {

                                    medicationAssignment.setStatement("Activada");
                    /*
                    switch (i)
                {
                    case 0:
                        //horas
                        //milisegundos, segundos, minutos, horas, días
                        notificationData.setUnitOfTime(1000*60*60*1);
                        break;
                    case 1:
                        //días
                        notificationData.setUnitOfTime(1000*60*60*24);
                        break;
                    case 2:
                        //semanas
                        notificationData.setUnitOfTime(1000*60*60*24*7);
                        break;
                    case 3:
                        //meses
                        notificationData.setUnitOfTime(1000*60*60*24*7*4);
                        break;
                    case 4:
                        //minutos
                        notificationData.setUnitOfTime(1000*60*1);
                        break;
                }*/
                                    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Brainmher", "Registrando en línea");

                                    final String uuidGenerated = createTransactionID();
                                    medicationAssignment.setMedicamentUID(uuidGenerated);

                                    final StorageReference imgRef = storageReference.child(Constants.Medicines + "/" + uuidGenerated + ".jpg");
                                    imgRef.putFile(uriImage)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                                    while (!uri.isComplete()) ;
                                                    Uri url = uri.getResult();
                                                    medicationAssignment.setUriImg(url.toString());
                                                    db.collection(Constants.Medicines).document(patient.getPatientUID())
                                                            .collection(Constants.Medicine).document(uuidGenerated).set(medicationAssignment)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    mostrarMensaje("Brainmher", "Tienes una nueva notificación de medicamento "
                                                                            + medicationAssignment.getMedicamentName());
                                                                    alertDialog.dismiss();
                                                                    progressDialog.dismiss();

                                                                }
                                                            });
                                                }
                                            });
                                }else{
                                    Toast.makeText(getActivity(), "Ingrese Imagen", Toast.LENGTH_SHORT).show();
                                }

                            } else if (option.equals("update")) {

                                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Brainmher", "Actuaizando en línea");


                                if(uriImage!=null) {
                                    storageReference = FirebaseStorage.getInstance().getReference();
                                    StorageReference deleteImg = storageReference.child(Constants.Medicines + "/" + medicationAssignmentUpdate.getMedicamentUID() + ".jpg");
                                    deleteImg.delete();

                                    final StorageReference imgRef = storageReference.child(Constants.Medicines + "/" + medicationAssignmentUpdate.getMedicamentUID() + ".jpg");
                                    imgRef.putFile(uriImage)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                                    while (!uri.isComplete()) ;
                                                    Uri url = uri.getResult();
                                                    medicationAssignment.setMedicamentUID(medicationAssignmentUpdate.getMedicamentUID());
                                                    medicationAssignment.setUriImg(url.toString());
                                                    db.collection(Constants.Medicines).document(patient.getPatientUID())
                                                            .collection(Constants.Medicine).document(medicationAssignmentUpdate.getMedicamentUID()).set(medicationAssignment)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    alertDialog.dismiss();
                                                                    progressDialog.dismiss();

                                                                }
                                                            });
                                                }
                                            });
                                }else{
                                    if(startHourMedicine.equals("")){
                                        medicationAssignment.setHours(medicationAssignmentUpdate.getHours());
                                    }else {
                                        medicationAssignment.setHours(startHourMedicine);
                                    }
                                    medicationAssignment.setMedicamentUID(medicationAssignmentUpdate.getMedicamentUID());
                                    medicationAssignment.setUriImg(medicationAssignmentUpdate.getUriImg());
                                    db.collection(Constants.Medicines).document(patient.getPatientUID())
                                            .collection(Constants.Medicine).document(medicationAssignmentUpdate.getMedicamentUID()).set(medicationAssignment)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    alertDialog.dismiss();
                                                    progressDialog.dismiss();

                                                }
                                            });
                                }
                            }

                        } else {
                            Toast.makeText(getActivity(), "Completa todos los datos", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                alertDialog.show();
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } else {

        }

        //endregion
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE2 && resultCode == Activity.RESULT_OK) {
            uriImage = data.getData();
            if (uriImage != null) {
                Glide.with(getActivity()).load(uriImage).fitCenter().into(imgMedicine);
            }
        }
    }

    private void mostrarMensaje(final String title, final String mensaje) {
        Gson gson = new Gson();

        //LISTA DE USUARIOS POR PLAYERID A LOS CUALES ENVIAR
        List<String> playerIds = new ArrayList<>();
        playerIds.add(patient.getPlayerId());

        //contenido en idiomas
        MensagesContent heading = new MensagesContent();
        heading.setEs(title);
        heading.setEn(title);

        //cabecera en idiomas
        MensagesContent content = new MensagesContent();
        content.setEs(mensaje);
        content.setEn(mensaje);

        //unir toda la infomarcion en la entidad
        Mensage mensage = new Mensage();
        mensage.setApp_id(Constants.app_id);
        mensage.setInclude_player_ids(playerIds);
        mensage.setContents(content);
        mensage.setHeadings(heading);

        OneSignal.postNotification(gson.toJson(mensage), new OneSignal.PostNotificationResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {

            }

            @Override
            public void onFailure(JSONObject response) {

            }
        });
    }

    //region codigo de ella

   /*     spinnerUnitTime = view.findViewById(R.id.sppiner_unit);
        etIntTime = view.findViewById(R.id.et_int_time);
        etNameMedicine=view.findViewById(R.id.et_medicine_name);

        if(!etIntTime.getText().toString().isEmpty()) {
            notificationData.setFrequency(Long.parseLong(etIntTime.getText().toString()));
        }

        if(!etNameMedicine.getText().toString().isEmpty()) {
            notificationData.setMessageNotification(etNameMedicine.getText().toString());
        }
       /* else
        {
            notificationData.setFrequency(1000);
        }*/

    private TimePickerDialog setStartHour() {

        //simpleSwitchBtn.setChecked(false);
        //notificationData.setStartTime(0);

        hourCalendar = calendarInstance.get(Calendar.HOUR_OF_DAY);
        minuteCalendar = calendarInstance.get(Calendar.MINUTE);

        TimePickerDialog startHour = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendarInstance.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarInstance.set(Calendar.MINUTE, minute);
                calendarInstance.set(Calendar.SECOND, 0);
                notificationData.setStartHour(hourOfDay);
                notificationData.setStartMinute(minute);
                updateTimeText(calendarInstance);
                notificationData.setStartTime(calendarInstance.getTimeInMillis());
                // startAlarm();

                if (notificationData.getStatus().equals(true)) {
                    notificationData.setStatus(false);
                    //simpleSwitchBtn.setChecked(false);
                } else if (notificationData.getStatus().equals(false)) {
                    notificationData.setStatus(true);
                    //simpleSwitchBtn.setChecked(true);
                }

                //   calendarInst.setText(notificationData.getStartTime()+"" + notificationData.getStatus());
            }

        }, hourCalendar, minuteCalendar, false);


        Toast.makeText(getContext(), " Finish TimePicket:" + "", Toast.LENGTH_SHORT).show();
        return startHour;
    }

    private void startAlarm() {

        //   Toast.makeText(this, "init startAlarm()" + notificationData.getStatus(), Toast.LENGTH_SHORT).show();

        //    simpleSwitchBtn.setChecked(true);
        Intent dialogIntent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 12345, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (calendarInstance.before(Calendar.getInstance())) {
            calendarInstance.add(Calendar.DATE, 1);
        }

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, notificationData.getStartTime(), 1000 * 60 * 10, pendingIntent);
        //   cal.setText(notificationData.getStartTime()+""+notificationData.getStatus());
    }

    private void cancelAlarm() {

        Intent dialogIntent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 12345, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        //    textShowHour.setText(getString(R.string.hour_format));
        //   cal.setText(notificationData.getStartTime() + "" + notificationData.getStatus());
        Toast.makeText(getContext(), "cancel:" + notificationData.getStatus(), Toast.LENGTH_SHORT).show();

    }

    private void updateTimeText(Calendar c) {
        String timeText = "Inicio:  ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        txtStartHour.setText(timeText);
        startHourMedicine = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

    }

    public String createTransactionID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    //endregion


}
