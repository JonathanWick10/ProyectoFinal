package com.jonathan.proyectofinal.fragments.games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemoramaAdapter;
import com.jonathan.proyectofinal.data.MemoramaEntity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Memorama extends Fragment {

    @BindView(R.id.memorama_rv)
    public RecyclerView cartas;


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_memorama, container, false);
        ButterKnife.bind(this,view);

        //settear datos de recycler view
        MemoramaAdapter memoramaAdapter  = new MemoramaAdapter(getDataListComplete());

        cartas.setAdapter(memoramaAdapter);
        cartas.setLayoutManager(new GridLayoutManager(view.getContext(),4));
        cartas.setHasFixedSize(true);

        return view;
    }

    private List<MemoramaEntity> getDataListComplete() {
        List<MemoramaEntity> memoramaEntities = new ArrayList<>();

        //add two times
        for (MemoramaEntity item: getDataList()){
            memoramaEntities.add(item);
            memoramaEntities.add(item);
        }

        return memoramaEntities;
    }

    private List<MemoramaEntity> getDataList() {
        //Lista
        List<MemoramaEntity> memoramaEntities = new ArrayList<>();
        MemoramaEntity entity1 = new MemoramaEntity("https://st2.depositphotos.com/5960950/8671/v/950/depositphotos_86717778-stock-illustration-abstract-colorful-geometric-triangles-rhombus.jpg",1);
        memoramaEntities.add(entity1);

        MemoramaEntity entity2 = new MemoramaEntity("https://previews.123rf.com/images/ksena32/ksena321710/ksena32171000413/87933208-fondo-de-confeti-de-peque%C3%B1as-estrellas-de-colores.jpg",2);
        memoramaEntities.add(entity2);

        MemoramaEntity entity3 = new MemoramaEntity("https://previews.123rf.com/images/pwg89/pwg891509/pwg89150900025/45306031-hojas-peque%C3%B1as.jpg",3);
        memoramaEntities.add(entity3);

        MemoramaEntity entity4 = new MemoramaEntity("https://bolsas-de-organza.es/wp-content/uploads/2014/01/Bolsas-de-organza-peque%C3%B1as-tama%C3%B1o-10x15cm.jpg",4);
        memoramaEntities.add(entity4);

        MemoramaEntity entity5 = new MemoramaEntity("http://maitebayona.com/wp-content/uploads/2017/04/Cosas-pequeU251cU2592as.jpg",5);
        //memoramaEntities.add(entity5);

        MemoramaEntity entity6 = new MemoramaEntity("http://www.habitosvitales.com/wp-content/uploads/2008/10/smallstuff.jpg",6);
        //memoramaEntities.add(entity6);

        MemoramaEntity entity7 = new MemoramaEntity("https://www.olimanitas.com/wp-content/uploads/2018/05/como-hacer-flores-de-papel-peque%C3%B1as-1024x683.jpg",7);
        //memoramaEntities.add(entity7);

        MemoramaEntity entity8 = new MemoramaEntity("https://coolties.es/wp-content/uploads/2018/11/PEQUE%C3%91AS-AMEBAS1.jpg",8);
        //memoramaEntities.add(entity8);

        return memoramaEntities;
    }


    private void numberAleatory() {

        final int min = 1;
        final int max = 16;
        final int random = new Random().nextInt((max - min) + 1) + min;
    }

}
