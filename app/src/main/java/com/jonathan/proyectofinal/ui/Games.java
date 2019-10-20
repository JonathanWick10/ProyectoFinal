package com.jonathan.proyectofinal.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.games.Memorama;

import java.util.HashMap;

public class Games extends AppCompatActivity {


    private HashMap<String , Integer> data;
    private FragmentTransaction ft;
    private FrameLayout conte;

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

        FragmentManager frg = getSupportFragmentManager();

        switch (nameGame){
            case "Memorama":
                ft = frg.beginTransaction();
                ft.replace(R.id.contanedor_games, new Memorama(sizeWindow()));
                ft.commit();
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

}
