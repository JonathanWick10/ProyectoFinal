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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.CarerEvent;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.database.CarerEventsManager;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.MainCarer;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String uidCarer, eventID;
    List<CarerEvent> carerEventList;
    TextView textEventsList;
    private CarerEventsManager carerEventsManager;
    private  AlertDialog alertDialogListEvents;


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
        carerEventsManager = new CarerEventsManager();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        uidCarer = firebaseUser.getUid();

        return view;
    }

    @Override
    public void onSelectedDayChange(@NonNull final CalendarView calendarView, int i, int i1, final int day) {

        final int year = i, month = i1 + 1;
        final AlertDialog.Builder builder;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), R.style.BackgroundRounded);
        }
        else {
            builder = new AlertDialog.Builder(getContext());
        }

        CharSequence[] items = new CharSequence[3];
        items[0] = getString(R.string.add_event_carer);
        items[1] = getString(R.string.view_event_carer);
        items[2] = getString(R.string.exit);


        builder.setTitle("Seleccione una opción")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            showAddEventDialog(day, month, year);
                        } else if (i == 1) {
                            viewEventDialog(day, month, year);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAddEventDialog(final int day, final int month, final int year) {

        final AlertDialog.Builder dialogBuilder;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     dialogBuilder = new AlertDialog.Builder(getContext(), R.style.BackgroundRounded);
        }else {
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



        //region button add event
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

                    carerEvent.setEventName(etEventName.getText().toString());
                    carerEvent.setEventLocation(etEventLocation.getText().toString());
                    carerEvent.setEventDate(year + "-" + month + "-" + day);
                    carerEvent.setEventStartHour(etStartHour.getText().toString());
                    carerEvent.setEventEndHour(etEndHour.getText().toString());
                    carerEvent.setEventDescription(etEventDescription.getText().toString());

                    Map<String, Object> event = new HashMap<>();
                    event.put("eventName", etEventName.getText().toString());
                    event.put("eventLocation", etEventName.getText().toString());

                    addEventCarerFirebase();

                    Toast.makeText(getContext(), "Evento guardado con éxito", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
        //endregion

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void viewEventDialog(final int day, final int month, final int year) {

        final AlertDialog.Builder dialogBuilder;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogBuilder = new AlertDialog.Builder(getContext(), R.style.BackgroundRounded);
        }else {
            dialogBuilder = new AlertDialog.Builder(getContext());
        }

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_cu_view_events, null);
        dialogBuilder.setView(dialogView);

        String date = year+"-"+month+"-"+day;

        final ListView carerEventsList;
        carerEventsList = dialogView.findViewById(R.id.lv_carer_events);
        textEventsList = dialogView.findViewById(R.id.tv_event_carer_list);

        dialogBuilder.setTitle("Eventos " + day + "-" + month + "-" + year);
        dialogBuilder.setPositiveButton(getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });
        AlertDialog b = dialogBuilder.create();
        listEvents(carerEventsList, date);
        alertDialogListEvents=b;
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

        Map<String, Object> event = new HashMap<>();
        event.put("eventName", carerEvent.getEventName());
        event.put("eventLocation", carerEvent.getEventLocation());
        event.put("eventDate", carerEvent.getEventDate());
        event.put("eventStartHour", carerEvent.getEventStartHour());
        event.put("eventEndHour", carerEvent.getEventEndHour());
        event.put("eventDescription", carerEvent.getEventDescription());
        event.put("eventStartTime", carerEvent.getEventStartTime());


        //region Create sub-collection Events
        db.collection(Constants.Carers).document(uidCarer)
                .collection("Events")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        addCarerEventID(documentReference.getId());
                        Log.d("ID GOOD", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SORRY :(", "Error adding document", e);
                    }
                });
        //endregion


    }

    private List<CarerEvent> listEvents(final ListView carerEventsList, final String date){


        CollectionReference collectionReferenceCarerEvents = db.collection(Constants.Carers).document(uidCarer)
                .collection("Events");

        collectionReferenceCarerEvents
                .whereEqualTo("eventDate", date)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        carerEventList = new ArrayList<CarerEvent>();
                        ArrayList eventsId = new ArrayList<String>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            String idEvent = documentSnapshopt.getId();
                            carerEvent.setEventId(idEvent);
                            carerEvent = documentSnapshopt.toObject(CarerEvent.class);
                            carerEventList.add(carerEvent);
                            eventsId.add(idEvent);
                            Log.d("ID Evento:", idEvent);
                        }

                        sortEventList(carerEventList, carerEventsList, eventsId);
                        Log.d("Message", String.valueOf(carerEventList));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });

                return carerEventList;
    }

    public void sortEventList(List<CarerEvent> carerEventList, ListView carerEventsList, final ArrayList eventsId) {

        if(carerEventList.toString().equals("[]"))
        {
            textEventsList.setText("No tiene eventos programados para este día");
        }else {
            final List<CarerEvent> fullEvents = carerEventList;
            int max= fullEvents.size();

            ArrayList misEventos = new ArrayList<String>();

            for(int i=0; i<max; i++)
            {
                fullEvents.get(i).getEventName();
                fullEvents.get(i).getEventStartHour();
                fullEvents.get(i).getEventDescription();
                misEventos.add((i+1)+". Evento: "+ fullEvents.get(i).getEventName() + " Inicia:" + fullEvents.get(i).getEventStartHour() + "  Descripción:" + fullEvents.get(i).getEventDescription());
            }

            ArrayAdapter adapter= new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, misEventos);
            carerEventsList.setAdapter(adapter);
            carerEventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    optionDeleteEvent(eventsId.get(i).toString(), fullEvents.get(i).getEventName());
                }
            });
        }


    }

    private void optionDeleteEvent(final String event, String eventName) {

        final AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(getContext());

        CharSequence[] items = new CharSequence[1];
        items[0] = "Eliminar evento: "+ eventName;

        builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            deleteCarerEvent(event);
                            alertDialogListEvents.dismiss();
                            Toast.makeText(getContext(), "El evento fue eliminado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addCarerEventID(String eventId) {

        db.collection(Constants.Carers).document(uidCarer)
                .collection("Events").document(eventId)
                .update("eventId", eventId)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("ID :)", "ID GUARDADO");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Error id", e.toString());
                    }
                });
    }

    private void deleteCarerEvent(String eventID) {

        db.collection(Constants.Carers).document(uidCarer)
                .collection("Events").document(eventID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("ELIMINADO", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error elimi:", "Error deleting document", e);
                    }
                });
    }

}
