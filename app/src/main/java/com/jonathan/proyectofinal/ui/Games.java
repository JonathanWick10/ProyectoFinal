package com.jonathan.proyectofinal.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.games.Memorama;

import java.util.HashMap;

public class Games extends AppCompatActivity implements Memorama.MemoramaI {

    private FrameLayout conte;
    private AlertDialog.Builder  builder;
    private FragmentManager frg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //flecha de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String nameGame =  getIntent().getExtras().getString("Game");

        frg = getSupportFragmentManager();

        switch (nameGame){
            case "Memorama":
              reload(1);
                break;
        }
    }

    private HashMap<String , Integer> sizeWindow(){
        DisplayMetrics metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels - getSupportActionBar().getHeight(); // alto absoluto en pixels

        HashMap<String,Integer> has =new HashMap<>();
        has.put("height",height/4);
        has.put("width",width/4);

        return has;
    }

    public void reload(Integer i) {
        switch (i){
            case 1:
                FragmentTransaction ft;
                ft = frg.beginTransaction();
                ft.replace(R.id.contanedor_games, new Memorama(sizeWindow(),this));
                ft.commit();
                break;
            case 0 :
                onBackPressed();
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public AlertDialog.Builder alertWin() {

        builder = new AlertDialog.Builder(Games.this);
        builder.setMessage(getString(R.string.win_messege));
        builder.setPositiveButton(getString(R.string.reload), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reload(1);
            }
        });
        builder.setNegativeButton(getString(R.string.volver_juego), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reload(0);
            }
        });
        return builder;
    }


}
