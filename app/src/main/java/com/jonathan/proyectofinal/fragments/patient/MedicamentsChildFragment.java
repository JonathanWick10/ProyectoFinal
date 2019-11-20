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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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
                Toast.makeText(getActivity(), "selecciono "+medicationAssignment.getMedicamentName(), Toast.LENGTH_SHORT).show();
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
        medicamentsAdapter = new MedicamentsAdapter(firestoreRecyclerOptions,getActivity(), iSelectItemMedicaments);
        medicamentsAdapter.notifyDataSetChanged();
        rcMedicaments.setAdapter(medicamentsAdapter);

    }

    public void alarmsPatient(){
        /* "2 Minutos", "30 Minutos", "6 Horas", "8 Horas", "12 Horas", "48 Horas"
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

}
