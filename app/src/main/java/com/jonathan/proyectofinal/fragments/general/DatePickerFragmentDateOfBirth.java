package com.jonathan.proyectofinal.fragments.general;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragmentDateOfBirth extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

    //private static final String TAG = "DatePickerFragmentDateOfBirth";
    final Calendar c = Calendar.getInstance();

    /*
    public DatePickerFragmentDateOfBirth() {
        // Required empty public constructor
    }
    */

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Establece la fecha actual como la fecha predeterminada
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Devuelve una nueva instancia de DatePickerDialog
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), DatePickerFragmentDateOfBirth.this, year - 18, month, day);
        c.set(Calendar.YEAR, year - 100);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c.set(Calendar.YEAR, year - 18);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return dialog;
        //return super.onCreateDialog(savedInstanceState);
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }
    */

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String selectDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(c.getTime());

        // Devuelve la fecha al fragmento de destino
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedDate", selectDate)
        );
    }
}
