package com.jonathan.proyectofinal.fragments.games;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemoramaAdapter;
import com.jonathan.proyectofinal.data.MemoramaEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Memorama extends Fragment implements MemoramaAdapter.MemoramaAdapterI {

    @BindView(R.id.memorama_rv)
    public RecyclerView cartas;

    private HashMap<String, Integer> data;
    private MemoramaAdapter memoramaAdapter;
    private List<MemoramaEntity> listComplete;
    private MemoramaI listener;

    public Memorama(HashMap<String, Integer> data, MemoramaI listener) {
        this.data = data;
        this.listener = listener;
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_memorama, container, false);
        ButterKnife.bind(this, view);

        //settear datos de recycler view
        listComplete = getDataListComplete();
        memoramaAdapter = new MemoramaAdapter(listComplete, this, data);

        cartas.setAdapter(memoramaAdapter);
        cartas.setLayoutManager(new GridLayoutManager(view.getContext(), 4));
        cartas.setHasFixedSize(true);

        //actualizar items
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (MemoramaEntity item : listComplete) {
                    item.setShow(false);
                    item.setClickeable(true);
                }
                memoramaAdapter.notifyDataSetChanged();
            }
        }, 5000);

        return view;
    }


    private List<MemoramaEntity> getDataListComplete() {
        //crear las listas
        List<MemoramaEntity> memoramaEntities = getDataList();
        List<Integer> numersUsed = new ArrayList<>();

        //obtienen los 16 elementos y se reccorren
        List<MemoramaEntity> items = getDataList();
        for (MemoramaEntity item : items) {

            //busca una posicion no usada dentro de la lsita
            boolean notFoundPosition = true;
            while (notFoundPosition) {
                //valida si ya existe esa posocion guardada
                int aletarori = numberAleatory();

                if (!numersUsed.contains(aletarori)) {
                    //agrega el elemento a la psoicion
                    numersUsed.add(aletarori);

                    //se editar eleento y se cambia al siguiente
                    item.setPosition(aletarori);
                    memoramaEntities.set(aletarori, item);
                    notFoundPosition = false;
                }
            }
        }

        return memoramaEntities;
    }

    private List<MemoramaEntity> getDataList() {
        //Lista
        List<MemoramaEntity> memoramaEntities = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            memoramaEntities.add(new MemoramaEntity("https://st2.depositphotos.com/5960950/8671/v/950/depositphotos_86717778-stock-illustration-abstract-colorful-geometric-triangles-rhombus.jpg", 1));

            memoramaEntities.add(new MemoramaEntity("https://previews.123rf.com/images/ksena32/ksena321710/ksena32171000413/87933208-fondo-de-confeti-de-peque%C3%B1as-estrellas-de-colores.jpg", 2));

            memoramaEntities.add(new MemoramaEntity("https://previews.123rf.com/images/pwg89/pwg891509/pwg89150900025/45306031-hojas-peque%C3%B1as.jpg", 3));

            memoramaEntities.add(new MemoramaEntity("https://bolsas-de-organza.es/wp-content/uploads/2014/01/Bolsas-de-organza-peque%C3%B1as-tama%C3%B1o-10x15cm.jpg", 4));

            memoramaEntities.add(new MemoramaEntity("http://maitebayona.com/wp-content/uploads/2017/04/Cosas-pequeU251cU2592as.jpg", 5));

            memoramaEntities.add(new MemoramaEntity("http://www.habitosvitales.com/wp-content/uploads/2008/10/smallstuff.jpg", 6));

            memoramaEntities.add(new MemoramaEntity("https://www.olimanitas.com/wp-content/uploads/2018/05/como-hacer-flores-de-papel-peque%C3%B1as-1024x683.jpg", 7));

            memoramaEntities.add(new MemoramaEntity("https://coolties.es/wp-content/uploads/2018/11/PEQUE%C3%91AS-AMEBAS1.jpg", 8));
        }

        return memoramaEntities;
    }

    private int numberAleatory() {
        //funcion que retorna numero aleatorio
        final int min = 0;
        final int max = 15;
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public void updateAllItems() {
        int cont = 0;
        for (MemoramaEntity item : listComplete) {
           if (item.isFound()){
               cont ++ ;
           }

            item.setClickeable(false);
        }
        Log.v("CONTADOR",cont+"");
        memoramaAdapter.notifyDataSetChanged();

        if(cont < listComplete.size()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (MemoramaEntity item : listComplete) {
                        if (!item.isFound()) {
                            item.setShow(false);
                            item.setClickeable(true);
                        }else{
                            item.setShow(true);
                            item.setClickeable(false);
                        }
                    }
                    memoramaAdapter.notifyDataSetChanged();
                }
            }, 2000);
        }else{
            listener.alertWin().show();
        }

    }

    @Override
    public void showMsm(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    public interface MemoramaI {
        void reload(Integer i);
        AlertDialog.Builder alertWin();
    }
}
