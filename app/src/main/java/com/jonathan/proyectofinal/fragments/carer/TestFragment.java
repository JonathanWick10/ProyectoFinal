package com.jonathan.proyectofinal.fragments.carer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jonathan.proyectofinal.R;

import java.util.concurrent.TimeUnit;

public class TestFragment extends Fragment implements View.OnClickListener {

    Button Start,End,tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8,tq9,tq10;
    int point1,point2,point3,point4,point5,point6,point7,point8,point9,point10;
    EditText point = null;
    String points = null;
    private long timeCountInMilliSeconds = 1 * 30000;
    private ProgressBar progressBarCircle;
    private TextView textViewTime,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,qual,ofpoint;
    private CountDownTimer countDownTimer;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_test, container, false);
        Start = view.findViewById(R.id.btnStar);      Start.setOnClickListener(this);
        End   = view.findViewById(R.id.btnEnd);        End.setOnClickListener(this);
        tq1   = view.findViewById(R.id.tq1);           tq1.setOnClickListener(this);
        tq2   = view.findViewById(R.id.tq2);           tq2.setOnClickListener(this);
        tq3   = view.findViewById(R.id.tq3);           tq3.setOnClickListener(this);
        tq4   = view.findViewById(R.id.tq4);           tq4.setOnClickListener(this);
        tq5   = view.findViewById(R.id.tq5);           tq5.setOnClickListener(this);
        tq6   = view.findViewById(R.id.tq6);           tq6.setOnClickListener(this);
        tq7   = view.findViewById(R.id.tq7);           tq7.setOnClickListener(this);
        tq8   = view.findViewById(R.id.tq8);           tq8.setOnClickListener(this);
        tq9   = view.findViewById(R.id.tq9);           tq9.setOnClickListener(this);
        tq10  = view.findViewById(R.id.tq10);          tq10.setOnClickListener(this);
        t1    = view.findViewById(R.id.t1);            t2    = view.findViewById(R.id.t2);
        t3    = view.findViewById(R.id.t3);            t4    = view.findViewById(R.id.t4);
        t5    = view.findViewById(R.id.t5);            t6    = view.findViewById(R.id.t6);
        t7    = view.findViewById(R.id.t7);            t8    = view.findViewById(R.id.t8);
        t9    = view.findViewById(R.id.t9);            t10   = view.findViewById(R.id.t10);
        point = view.findViewById(R.id.point_qualification);
        point.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "10")});
        qual  = view.findViewById(R.id.txt_qualification);
        ofpoint = view.findViewById(R.id.of10);
        progressBarCircle = view.findViewById(R.id.progressBarCircle);
        textViewTime = view.findViewById(R.id.textViewTime);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tq1:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t1.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq2:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t2.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq3:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t3.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq4:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t4.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq5:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t5.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq6:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t6.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq7:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t7.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq8:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t8.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq9:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t9.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.tq10:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t10.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                }
                break;
            case R.id.btnStar:
                iniciarTemporizador();
                break;
            case R.id.btnEnd:
                points = point.getText().toString().trim();
                if(Start.getVisibility() == v.VISIBLE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.GONE);
                }
                if(tq1.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point1 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq1.setVisibility(v.GONE);
                    tq2.setVisibility(v.VISIBLE);
                    t1.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq2.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point2 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq2.setVisibility(v.GONE);
                    tq3.setVisibility(v.VISIBLE);
                    t2.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq3.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point3 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq3.setVisibility(v.GONE);
                    tq4.setVisibility(v.VISIBLE);
                    t3.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq4.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point4 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq4.setVisibility(v.GONE);
                    tq5.setVisibility(v.VISIBLE);
                    t4.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq5.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point5 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq5.setVisibility(v.GONE);
                    tq6.setVisibility(v.VISIBLE);
                    t5.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq6.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point6 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq6.setVisibility(v.GONE);
                    tq7.setVisibility(v.VISIBLE);
                    t6.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq7.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point7 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq7.setVisibility(v.GONE);
                    tq8.setVisibility(v.VISIBLE);
                    t7.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq8.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point8 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq8.setVisibility(v.GONE);
                    tq9.setVisibility(v.VISIBLE);
                    t8.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq9.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point9 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq9.setVisibility(v.GONE);
                    tq10.setVisibility(v.VISIBLE);
                    t9.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq10.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point10 = Integer.parseInt(point.getText().toString());
                    point.getText().clear();
                    tq10.setVisibility(v.GONE);
                    progressBarCircle.setVisibility(v.GONE);
                    textViewTime.setVisibility(v.GONE);
                    t10.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                    End.setText("FINALIZAR");
                }
                else if (points.isEmpty() && progressBarCircle.getVisibility() == v.VISIBLE)
                {
                    Toast.makeText(getActivity(),"Ingrese puntuación",Toast.LENGTH_SHORT).show();
                }
                //Método que detiene el temporizador
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                break;
        }
    }

    private void iniciarTemporizador() {
        // llamado del método que le setea el valor al temporizador
        setTimerValues();
        // llamado al método que le setea los valores a la barra de progreso
        setProgressBarValues();
        // llamado al método del temporizador
        startCountDownTimer();
    }

    private void setProgressBarValues() {
        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private void setTimerValues() {
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = 30 * 1000;
    }

    //Método del Temporizador
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();

                //Método que detiene el temporizador
                countDownTimer.cancel();
            }

        }.start();
    }


    //método para el formato de tiempo
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;
    }
}
