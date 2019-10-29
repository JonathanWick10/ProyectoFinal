package com.jonathan.proyectofinal.fragments.carer;


import android.content.Context;
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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

import java.util.concurrent.TimeUnit;

public class TestFragment extends Fragment implements View.OnClickListener {

    private IMainCarer mIMainCarer;

    private Button Start,End,tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8,tq9,tq10;
    private int total,point1,point2,point3,point4,point5,point6,point7,point8,point9,point10;
    private EditText point = null;
    private String points = null;
    private TableLayout table;
    private long timeCountInMilliSeconds = 1 * 40000;
    private ProgressBar progressBarCircle;
    private TextView textViewTime,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,qual,ofpoint,Res
            ,toneD,toneE,toneF,ttwoD,ttwoE,ttwoF,tthreD,tthreE,tthreF,tfourD
            ,tfourE,tfourF,tfiveD,tfiveE,tfiveF,tsixD,tsixE,tsixF,tsevenD,tsevenE
            ,tsevenF,teigthD,teigthE,teigthF,tnineD,tnineE,tnineF,ttentD,ttenE,ttenF;
    private CountDownTimer countDownTimer;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_test, container, false);
        Start   = view.findViewById(R.id.btnStar);      Start.setOnClickListener(this);
        End     = view.findViewById(R.id.btnEnd);        End.setOnClickListener(this);
        tq1     = view.findViewById(R.id.tq1);           tq1.setOnClickListener(this);
        tq2     = view.findViewById(R.id.tq2);           tq2.setOnClickListener(this);
        tq3     = view.findViewById(R.id.tq3);           tq3.setOnClickListener(this);
        tq4     = view.findViewById(R.id.tq4);           tq4.setOnClickListener(this);
        tq5     = view.findViewById(R.id.tq5);           tq5.setOnClickListener(this);
        tq6     = view.findViewById(R.id.tq6);           tq6.setOnClickListener(this);
        tq7     = view.findViewById(R.id.tq7);           tq7.setOnClickListener(this);
        tq8     = view.findViewById(R.id.tq8);           tq8.setOnClickListener(this);
        tq9     = view.findViewById(R.id.tq9);           tq9.setOnClickListener(this);
        tq10    = view.findViewById(R.id.tq10);          tq10.setOnClickListener(this);
        t1      = view.findViewById(R.id.t1);            t2     = view.findViewById(R.id.t2);
        t3      = view.findViewById(R.id.t3);            t4     = view.findViewById(R.id.t4);
        t5      = view.findViewById(R.id.t5);            t6     = view.findViewById(R.id.t6);
        t7      = view.findViewById(R.id.t7);            t8     = view.findViewById(R.id.t8);
        t9      = view.findViewById(R.id.t9);            t10    = view.findViewById(R.id.t10);
        toneD   = view.findViewById(R.id.txtUnod);       toneE  = view.findViewById(R.id.txtUnoe);
        toneF   = view.findViewById(R.id.txtUnof);       ttwoD  = view.findViewById(R.id.txtDosd);
        ttwoE   = view.findViewById(R.id.txtDose);       ttwoF  = view.findViewById(R.id.txtDosf);
        tthreD  = view.findViewById(R.id.txtTresd);      tthreE = view.findViewById(R.id.txtTrese);
        tthreF  = view.findViewById(R.id.txtTresf);      tfourD = view.findViewById(R.id.txtCuatrod);
        tfourE  = view.findViewById(R.id.txtCuatroe);    tfourF = view.findViewById(R.id.txtCuatrof);
        tfiveD  = view.findViewById(R.id.txtCincod);     tfiveE = view.findViewById(R.id.txtCincoe);
        tfiveF  = view.findViewById(R.id.txtCincof);     tsixD  = view.findViewById(R.id.txtSeisd);
        tsixE   = view.findViewById(R.id.txtSeise);      tsixF  = view.findViewById(R.id.txtSeisf);
        tsevenD = view.findViewById(R.id.txtSieted);    tsevenE = view.findViewById(R.id.txtSietee);
        tsevenF = view.findViewById(R.id.txtSietef);   teigthD  = view.findViewById(R.id.txtOchod);
        teigthE = view.findViewById(R.id.txtOchoe);    teigthF  = view.findViewById(R.id.txtOchof);
        tnineD  = view.findViewById(R.id.txtNueved);   tnineE   = view.findViewById(R.id.txtNuevee);
        tnineF  = view.findViewById(R.id.txtNuevef);   ttentD   = view.findViewById(R.id.txtDiezd);
        ttenE   = view.findViewById(R.id.txtDieze);    ttenF    = view.findViewById(R.id.txtDiezf);
        point = view.findViewById(R.id.point_qualification);
        point.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "10")});
        qual  = view.findViewById(R.id.txt_qualification);
        ofpoint = view.findViewById(R.id.of10);
        progressBarCircle = view.findViewById(R.id.progressBarCircle);
        textViewTime = view.findViewById(R.id.textViewTime);
        table = view.findViewById(R.id.table);
        Res = view.findViewById(R.id.txtDiferencia);
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
                    tq1.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq2:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t2.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq2.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq3:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t3.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq3.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq4:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t4.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq4.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq5:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t5.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq5.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq6:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t6.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq6.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq7:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t7.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq7.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq8:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t8.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq8.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq9:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t9.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq9.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.tq10:
                if(Start.getVisibility() == v.GONE) { //si es Visible lo pones Gone
                    Start.setVisibility(v.VISIBLE);
                    t10.setVisibility(v.VISIBLE);
                    qual.setVisibility(v.VISIBLE);
                    point.setVisibility(v.VISIBLE);
                    ofpoint.setVisibility(v.VISIBLE);
                    tq10.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_autoreturn),null);
                }
                break;
            case R.id.btnStar:
                //Método que detiene el temporizador
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
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
                    if (point1 < 7.77){
                        toneD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point1 > 7.76 && point1 < 9.04){
                        toneE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point1 > 9.04 && point1 <= 10){
                        toneF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
                    tq1.setVisibility(v.GONE);
                    tq2.setVisibility(v.VISIBLE);
                    t1.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                }
                else if(tq2.getVisibility() == v.VISIBLE && points != null && !points.isEmpty()){ //si es Visible lo pones Gone
                    point2 = Integer.parseInt(point.getText().toString());
                    if (point2 < 7.54){
                        ttwoD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point2 > 7.53 && point2 < 8.56){
                        ttwoE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point2 > 8.55 && point2 <= 10){
                        ttwoF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point3 < 7.62){
                        tthreD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point3 > 7.61 && point3 < 8.44){
                        tthreE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point3 > 8.43 && point3 <= 10){
                        tthreF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point4 < 7.58){
                        tfourD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point4 > 7.57 && point4 < 8.32){
                        tfourE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point4 > 8.31 && point4 <= 10){
                        tfourF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point5 < 6.73){
                        tfiveD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point5 > 6.72 && point5 < 8.28){
                        tfiveE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point5 > 8.27 && point5 <= 10){
                        tfiveF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point6 < 7.38){
                        tsixD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point6 > 7.37 && point6 < 8.60){
                        tsixE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point6 > 8.59 && point6 <= 10){
                        tsixF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point7 < 7.81){
                        tsevenD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point7 > 7.80 && point7 < 8.56){
                        tsevenE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point7 > 8.55 && point7 <= 10){
                        tsevenF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point8 < 7.32){
                        teigthD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point8 > 7.31 && point8 < 8.05){
                        teigthE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point8 > 8.04 && point8 <= 10){
                        teigthF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point9 < 6.40){
                        tnineD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point9 > 6.39 && point9 < 7.20){
                        tnineE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point9 > 7.19 && point9 <= 10){
                        tnineF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
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
                    if (point10 < 6.96){
                        ttentD.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (point10 > 6.95 && point10 < 7.56){
                        ttenE.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (point10 > 7.55 && point10 <= 10){
                        ttenF.setTextColor(getResources().getColorStateList(R.color.greenDark));
                    }
                    point.getText().clear();
                    tq10.setVisibility(v.GONE);
                    progressBarCircle.setVisibility(v.GONE);
                    textViewTime.setVisibility(v.GONE);
                    t10.setVisibility(v.GONE);
                    qual.setVisibility(v.GONE);
                    point.setVisibility(v.GONE);
                    ofpoint.setVisibility(v.GONE);
                    total = point1+point2+point3+point4+point5+point6+point7+point8+point9+point10;
                    String res = total + "/100";
                    Res.setText(res);
                    if (total < 73.11){
                        Res.setTextColor(getResources().getColorStateList(R.color.redDark));
                    }
                    if (total > 73.10 && total < 82.61){
                        Res.setTextColor(getResources().getColorStateList(R.color.yellowDark));
                    }
                    if (total > 82.60 && total <= 100){
                        Res.setTextColor(getResources().getColorStateList(R.color.blueDark));
                    }
                    table.setVisibility(v.VISIBLE);
                    End.setText("FINALIZAR");
                }
                else if (points.isEmpty() && progressBarCircle.getVisibility() == v.VISIBLE)
                {
                    Toast.makeText(getActivity(),"Ingrese puntuación",Toast.LENGTH_SHORT).show();
                }
                else if (table.getVisibility() == v.VISIBLE){
                    mIMainCarer.inflateFragment(getString(R.string.test));
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
        timeCountInMilliSeconds = 40 * 1000;
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
