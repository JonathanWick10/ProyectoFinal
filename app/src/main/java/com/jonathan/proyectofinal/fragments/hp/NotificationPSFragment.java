package com.jonathan.proyectofinal.fragments.hp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.AlertReceiver;
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

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NotificationPSFragment extends Fragment {

    //region codigo de ella
    private ImageButton startHour;
    private TextView textShowHour, calendarInst, cal;
    public final Calendar calendarInstance = Calendar.getInstance();
    int hourCalendar, minuteCalendar;
    private Switch simpleSwitchBtn;
    private TextView switchBtn_txtView;
    //private Spinner spinnerUnitTime;
    //private EditText etIntTime, etNameMedicine;
    private String [] arrayUnitTime;
    private ArrayAdapter adapter;

    private NotificationData notificationData;
    private AlarmManager alarmManager;

    public NotificationPSFragment() { }
    //endregion

    private Patient patient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_notification, container, false);

        /*Bundle bundle = getArguments();

        if (bundle!=null){*/
        SharedPreferences preferences = getActivity().getPreferences(0);
        Gson gson = new Gson();
        String json = preferences.getString("serialipatient","");
        patient = gson.fromJson(json,Patient.class);
        //}
        referenceViews(view);
        notificationData=new NotificationData();
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        arrayUnitTime = getResources().getStringArray(R.array.unit_time);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,arrayUnitTime);
        // spinnerUnitTime.setAdapter(adapter);


        //consultar las personas a enviar notificacion
        mostrarMensaje("Hola","Mundo");
        return view;
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
        mensage.setTemplate_id(Constants.template_id);
        //mensage.setIncluded_segments(segments);
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
    private void referenceViews(View view) {

        //////////////////
        calendarInst = view.findViewById(R.id.tv_calendar);
        cal=view.findViewById(R.id.tv_c);
        /////////

        startHour=view.findViewById(R.id.btn_notification_start);
        textShowHour=view.findViewById(R.id.tv_start_hour);
        simpleSwitchBtn = view.findViewById(R.id.switch_reminder);
        switchBtn_txtView = view.findViewById(R.id.tv_switch);
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

        startHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStartHour().show();
            }
        });

        simpleSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    notificationData.setStatus(true);
                    switchBtn_txtView.setText(R.string.alarm_on);
                    startAlarm();
                }
                else {
                    notificationData.setStatus(false);
                    switchBtn_txtView.setText(R.string.alarm_off);
                    cancelAlarm();
                }
            }
        });
    //    Toast.makeText(this, ""+calendarInstance.getTimeInMillis()+""+notificationData.getStatus() +"", Toast.LENGTH_SHORT).show();

      /*  spinnerUnitTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

    }

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

                if(notificationData.getStatus().equals(true)) {
                    notificationData.setStatus(false);
                    simpleSwitchBtn.setChecked(false);
                }
                else if (notificationData.getStatus().equals(false))
                {
                    notificationData.setStatus(true);
                    simpleSwitchBtn.setChecked(true);
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 12345, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT);

        if (calendarInstance.before(Calendar.getInstance())) {
            calendarInstance.add(Calendar.DATE, 1);
        }

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, notificationData.getStartTime(), 1000*60*10, pendingIntent);
     //   cal.setText(notificationData.getStartTime()+""+notificationData.getStatus());
    }

    private void cancelAlarm() {

        Intent dialogIntent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 12345, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        //    textShowHour.setText(getString(R.string.hour_format));
     //   cal.setText(notificationData.getStartTime() + "" + notificationData.getStatus());
        Toast.makeText(getContext(), "cancel:"+notificationData.getStatus(), Toast.LENGTH_SHORT).show();

    }

    private void updateTimeText(Calendar c) {
        String timeText = "Inicio:";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textShowHour.setText(timeText);

    }
    //endregion
}
