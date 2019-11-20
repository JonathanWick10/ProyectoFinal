package com.jonathan.proyectofinal.fragments.patient;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MedicamentsAdapter;
import com.jonathan.proyectofinal.data.AlertReceiver;
import com.jonathan.proyectofinal.data.MedicationAssignment;
import com.jonathan.proyectofinal.data.NotificationData;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicamentsChildFragment extends Fragment {

    //regoin Variables
    //Views
    @BindView(R.id.list_notifications_medicaments)
    RecyclerView rcMedicaments;
    //Adapter
    MedicamentsAdapter medicamentsAdapter;
    MedicamentsAdapter.ISelectItemMedicaments iSelectItemMedicaments;
    //Firebase
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    //Alarm
    private NotificationData notificationData;
    private AlarmManager alarmManager;
    public final Calendar calendarInstance = Calendar.getInstance();
    //Instance Medicaments
    MedicationAssignment medicationAssignment = new MedicationAssignment();
    //endregion

    public MedicamentsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicaments_child, container, false);
        ButterKnife.bind(this, view);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        eventLogicSelectItem();
        initRecycler();
        //region Alarm
        notificationData = new NotificationData();
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        //endregion
        alarmsPatient();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        medicamentsAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        medicamentsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        medicamentsAdapter.startListening();
    }

    private void eventLogicSelectItem() {
        iSelectItemMedicaments = new MedicamentsAdapter.ISelectItemMedicaments() {
            @Override
            public void clickSelect(MedicationAssignment medicationAssignment) {
                Toast.makeText(getActivity(), "selecciono " + medicationAssignment.getMedicamentName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void check(MedicationAssignment medicationAssignment) {
                Toast.makeText(getActivity(), "selecciono " + medicationAssignment.getMedicamentName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void initRecycler() {
        rcMedicaments.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcMedicine.setLayoutManager(new GridLayoutManager(getActivity(), 3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Medicines).document(user.getUid()).collection(Constants.Medicine);
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<MedicationAssignment> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MedicationAssignment>()
                .setQuery(query, MedicationAssignment.class).build();

        //Passing parameters to the adapter
        medicamentsAdapter = new MedicamentsAdapter(firestoreRecyclerOptions, getActivity(), iSelectItemMedicaments);
        medicamentsAdapter.notifyDataSetChanged();
        rcMedicaments.setAdapter(medicamentsAdapter);

    }

    public void alarmsPatient() {
        //"2 Minutos", "5 Minutos", "30 Minutos", "6 Horas", "8 Horas", "12 Horas", "48 Horas"

        db.collection(Constants.Medicines).document(user.getUid()).collection(Constants.Medicine)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot documentsnapshop :
                            queryDocumentSnapshots) {
                        medicationAssignment = documentsnapshop.toObject(MedicationAssignment.class);
                        switch (medicationAssignment.getFrequency()) {
                            case "2 Minutos":
                                long intervalMillis = 1000 * 60 * 2;
                                int requestCode = 12;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode,intervalMillis);
                                }else {
                                    //cancelAlarm(requestCode);
                                }
                                break;
                            case "5 Minutos":
                                long intervalMillis1 = 1000 * 60 * 5;
                                int requestCode1 = 123;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode1,intervalMillis1);
                                }else {
                                    //cancelAlarm(requestCode1);
                                }
                                break;
                            case "30 Minutos":
                                long intervalMillis2 = 1000 * 60 * 30;
                                int requestCode2 = 124;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode2,intervalMillis2);
                                }else {
                                    //cancelAlarm(requestCode2);
                                }
                                break;
                            case "6 Horas":
                                long intervalMillis3 = 1000 * 60 * 60 * 6;
                                int requestCode3 = 125;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode3,intervalMillis3);
                                }else {
                                    //cancelAlarm(requestCode3);
                                }
                                break;
                            case "8 Horas":
                                long intervalMillis4 = 1000 * 60 * 60 * 8;
                                int requestCode4 = 126;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode4,intervalMillis4);
                                }else {
                                    //cancelAlarm(requestCode4);
                                }
                                break;
                            case "12 Horas":
                                long intervalMillis5 = 1000 * 60 * 60 * 12;
                                int requestCode5 = 127;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode5,intervalMillis5);
                                }else {
                                    //cancelAlarm(requestCode5);
                                }
                                break;
                            case "48 Horas":
                                long intervalMillis6 = 1000 * 60 * 60 * 48;
                                int requestCode6 = 128;
                                if (medicationAssignment.getStatement().equals("Activada")){
                                    //initNotify(medicationAssignment.getHours());
                                    //startAlarm(requestCode6,intervalMillis6);
                                }else {
                                    //cancelAlarm(requestCode6);
                                }
                                break;
                            //region Ejemplo
                            /*case "Horas":
                                //horas
                                //milisegundos, segundos, minutos, horas, días
                                notificationData.setUnitOfTime(1000 * 60 * 60 * 1);
                                break;
                            case "Dias":
                                //días
                                notificationData.setUnitOfTime(1000 * 60 * 60 * 24);
                                break;
                            case "Semanas":
                                //semanas
                                notificationData.setUnitOfTime(1000 * 60 * 60 * 24 * 7);
                                break;
                            case "Meses":
                                //meses
                                notificationData.setUnitOfTime(1000 * 60 * 60 * 24 * 7 * 4);
                                break;
                            case "Minutos":
                                //minutos
                                notificationData.setUnitOfTime(1000 * 60 * 1);
                                break;*/
                            //endregion
                        }
                    }
                }
            }
        });

    }

    private void initNotify(String hour ) {

        String[] hoursplit = hour.split(":");
        String hourDay = hoursplit[0].trim();
        String[] minuteSplit = hoursplit[1].split(" ");
        String minuteDay = minuteSplit[0].trim();
        int hourNotify = Integer.parseInt(hourDay);
        int minuteNotify = Integer.parseInt(minuteDay);

        calendarInstance.set(Calendar.HOUR_OF_DAY, hourNotify);
        calendarInstance.set(Calendar.MINUTE, minuteNotify);
        calendarInstance.set(Calendar.SECOND, 0);
        notificationData.setStartHour(hourNotify);
        notificationData.setStartMinute(minuteNotify);
        notificationData.setStartTime(calendarInstance.getTimeInMillis());
        // startAlarm();

        if (notificationData.getStatus().equals(true)) {
            notificationData.setStatus(false);
            //simpleSwitchBtn.setChecked(false);
        } else if (notificationData.getStatus().equals(false)) {
            notificationData.setStatus(true);
            //simpleSwitchBtn.setChecked(true);
        }
    }

    private void startAlarm(int requestCode, long intervalMillis) {

        //   Toast.makeText(this, "init startAlarm()" + notificationData.getStatus(), Toast.LENGTH_SHORT).show();

        Intent dialogIntent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (calendarInstance.before(Calendar.getInstance())) {
            calendarInstance.add(Calendar.DATE, 1);
        }

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, notificationData.getStartTime(), intervalMillis, pendingIntent);
        //   cal.setText(notificationData.getStartTime()+""+notificationData.getStatus());
    }

    private void cancelAlarm(int requestCode) {

        Intent dialogIntent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getContext(), "cancel:" + notificationData.getStatus(), Toast.LENGTH_SHORT).show();

    }

}
