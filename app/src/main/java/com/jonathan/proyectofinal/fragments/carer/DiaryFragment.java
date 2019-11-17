package com.jonathan.proyectofinal.fragments.carer;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.CarerEvent;
import com.jonathan.proyectofinal.database.CarerEventsManager;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.MainCarer;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;

public class DiaryFragment extends Fragment implements CalendarView.OnDateChangeListener {

    @BindView(R.id.edit_carer_event_name)
    TextInputEditText editCarerEventName;
    @BindView(R.id.til_carer_event_name)
    TextInputLayout tilCarerEventName;
    @BindView(R.id.edit_carer_event_description)
    TextInputEditText editCarerEventDescription;
    @BindView(R.id.til_carer_event_description)
    TextInputLayout tilCarerEventDescription;
    @BindView(R.id.edit_carer_event_location)
    TextInputEditText editCarerEventLocation;
    @BindView(R.id.til_carer_event_location)
    TextInputLayout tilCarerEventLocation;
    @BindView(R.id.edit_carer_event_start)
    TextInputEditText editCarerEventStart;
    @BindView(R.id.til_carer_event_start)
    TextInputLayout tilCarerEventStart;
    @BindView(R.id.edit_carer_event_end)
    TextInputEditText editCarerEventEnd;
    @BindView(R.id.til_carer_event_end)
    TextInputLayout tilCarerEventEnd;
    private CalendarView calendarView;
    public final Calendar calendarInstance = Calendar.getInstance();
    int hourCalendar, minuteCalendar;
    CarerEvent carerEvent;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    String uidCarer;


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cu_diary, container, false);
        calendarView = view.findViewById(R.id.calendarViewCarer);
        calendarView.setOnDateChangeListener(this);
        carerEvent= new CarerEvent();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();
        uidCarer = firebaseUser.getUid();

        Toast.makeText(getContext(), "uid:" + uidCarer, Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, final int day) {

        final int year = i, month = i1 + 1;
        final AlertDialog.Builder builder;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions

            builder = new AlertDialog.Builder(getContext(), R.style.BackgroundRounded);
        }
        else {
            builder = new AlertDialog.Builder(getContext());
        }

        CharSequence[] items = new CharSequence[3];
        items[0] = getString(R.string.add_event_carer);
        items[1] = getString(R.string.view_event_carer);
        items[2] = getString(R.string.cancel_event_carer);


        builder.setTitle("Seleccione una opción")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            showAddEventDialog(day, month, year);
                        } else if (i == 1) {
                            viewEventDialog(day, month, year);
                            Toast.makeText(getContext(), "VER", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "CANCELAR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAddEventDialog(final int day, final int month, final int year) {

       // final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BackgroundRounded);

        final AlertDialog.Builder dialogBuilder;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions

             dialogBuilder = new AlertDialog.Builder(getContext(), R.style.BackgroundRounded);
        }
        else {
            dialogBuilder = new AlertDialog.Builder(getContext());
        }

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_cu_add_event, null);
        dialogBuilder.setView(dialogView);

        final TextInputEditText etEventName, etEventDescription, etEventLocation, etStartHour, etEndHour;
        final TextInputLayout textEventName, textEventStartHour;
        final Button btnAddEvent, btnCancel;
        final ImageButton btnStartHour, btnEndHour;
        etEventName= dialogView.findViewById(R.id.edit_carer_event_name);
        textEventName = dialogView.findViewById(R.id.til_carer_event_name);
        etEventDescription = dialogView.findViewById(R.id.edit_carer_event_description);
        etEventLocation=dialogView.findViewById(R.id.edit_carer_event_location);
        etStartHour=dialogView.findViewById(R.id.edit_carer_event_start);
        textEventStartHour=dialogView.findViewById(R.id.til_carer_event_start);
        etEndHour=dialogView.findViewById(R.id.edit_carer_event_end);
        btnAddEvent=dialogView.findViewById(R.id.btn_add_event);
        btnCancel=dialogView.findViewById(R.id.btn_cancel_event);
        btnStartHour=dialogView.findViewById(R.id.event_start_hour);
        btnEndHour=dialogView.findViewById(R.id.event_end_hour);

        etEventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textEventName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etStartHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textEventStartHour.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //   final EditText edt = (EditText) dialogView.findViewById(R.id.carer);


        dialogBuilder.setTitle( getResources().getString(R.string.add_event_carer)+" ("+day+"/"+month+"/"+year+")");

        final AlertDialog alertDialog = dialogBuilder.create();

        btnStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getStartHour(etStartHour, day, month, year).show();

            }
        });

        btnEndHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getEndHour(etEndHour).show();
            }
        });



        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etEventName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "VACIO", Toast.LENGTH_SHORT).show();
                    textEventName.setError(getString(R.string.email_required));
                }
                else if (etStartHour.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "VACIO", Toast.LENGTH_SHORT).show();
                    textEventStartHour.setError(getString(R.string.email_required));
                } else{

                CarerEventsManager bd = new CarerEventsManager(getContext(), "CarerEvents", null, 1);
                SQLiteDatabase db = bd.getWritableDatabase();

                String sql = "insert into eventsCarer" +
                        "(eventName, eventLocation, eventDate, eventStartHour," +
                        "eventEndHour, eventDescription) values('" +
                        etEventName.getText().toString() +
                        "','" + etEventLocation.getText().toString() +
                        "','" + year + "-" + month + "-" + day +
                        "','" + etStartHour.getText().toString() +
                        "','" + etEndHour.getText().toString() +
                        "','" + etEventDescription.getText().toString() +
                        "')";

                try {
                    db.execSQL(sql);
                } catch (Exception e) {

                    Toast.makeText(getContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();


            }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void viewEventDialog(final int day, final int month, final int year) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_cu_view_events, null);
        dialogBuilder.setView(dialogView);

        CarerEventsManager bd = new CarerEventsManager(getContext(), "CarerEvents", null, 1);
        SQLiteDatabase db = bd.getReadableDatabase();

        String date = year+"-"+month+"-"+day;

        String sql = "select * from eventsCarer where eventDate='"+date+"'";
        Cursor c;

        String nombre, fecha, descripcion, ubicacion;

        final ListView carerEventsList;
        carerEventsList = dialogView.findViewById(R.id.lv_carer_events);

        final ArrayAdapter<String> arrayAdapter ;


        try{
            c=db.rawQuery(sql, null);
            arrayAdapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
            if(c.moveToFirst()){
                do{

                    nombre=c.getString(1);
                    ubicacion=c.getString(2);
                    fecha=c.getString(3);
                    descripcion=c.getString(6);
                    arrayAdapter.add(nombre+", "+ubicacion+ ", "+fecha+", "+descripcion);

                }while (c.moveToNext());

                carerEventsList.setAdapter(arrayAdapter);
            }else {
                Toast.makeText(getContext(), "NO HAY EVENTOS", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

            Toast.makeText(getContext(), "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        carerEventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        //   final EditText edt = (EditText) dialogView.findViewById(R.id.carer);

        dialogBuilder.setTitle("Eventos " + day + "-" + month + "-" + year);
        dialogBuilder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private TimePickerDialog getStartHour(final TextInputEditText etStartHour, final int day, final int month, final int year) {

        hourCalendar = calendarInstance.get(Calendar.HOUR_OF_DAY);
        minuteCalendar = calendarInstance.get(Calendar.MINUTE);

        TimePickerDialog startHour = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendarInstance.set(Calendar.YEAR, year);
                calendarInstance.set(Calendar.MONTH, month);
                calendarInstance.set(Calendar.DAY_OF_MONTH, day);
                calendarInstance.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarInstance.set(Calendar.MINUTE, minute);
                calendarInstance.set(Calendar.SECOND, 0);


                carerEvent.setEventStartTime(calendarInstance.getTimeInMillis());
           //     updateHourEvent(calendarInstance);

                // set start hour in text
                String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendarInstance.getTime());
                etStartHour.setText(timeText);

                carerEvent.setEventStartHour(timeText);

            }

        }, hourCalendar, minuteCalendar, false);

        return startHour;
    }

    private TimePickerDialog getEndHour(final TextInputEditText etEndHour) {

        hourCalendar = calendarInstance.get(Calendar.HOUR_OF_DAY);
        minuteCalendar = calendarInstance.get(Calendar.MINUTE);

        TimePickerDialog endHour = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendarInstance.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarInstance.set(Calendar.MINUTE, minute);
                calendarInstance.set(Calendar.SECOND, 0);

                // set end hour in text
                String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendarInstance.getTime());
                etEndHour.setText(timeText);

                carerEvent.setEventEndHour(timeText);

            }

        }, hourCalendar, minuteCalendar, false);

        return endHour;
    }

    private void addEventCarerFirebase(){

    }



}
