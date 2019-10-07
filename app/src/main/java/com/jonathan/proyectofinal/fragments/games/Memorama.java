package com.jonathan.proyectofinal.fragments.games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        //interface alerta
        MemoramaAdapter.MemoramaAdapterI adapterI = new MemoramaAdapter.MemoramaAdapterI() {
            @Override
            public void clickItem(String mensaje) {
                //cuando aserte la persona
                Toast.makeText(Memorama.this.getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        };

        //settear datos de recycler view
        final List<MemoramaEntity> listComplete = getDataListComplete();
        final MemoramaAdapter memoramaAdapter  = new MemoramaAdapter(listComplete,adapterI);

        cartas.setAdapter(memoramaAdapter);
        cartas.setLayoutManager(new GridLayoutManager(view.getContext(),4));
        cartas.setHasFixedSize(true);
        cartas.setNestedScrollingEnabled(true);

        //esperar 2s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (MemoramaEntity item:listComplete) {
                    item.setShow(false);
                }
                memoramaAdapter.notifyDataSetChanged();
            }
        },5000);

        return view;
    }

    private List<MemoramaEntity> getDataListComplete() {
        List<MemoramaEntity> memoramaEntities = getDataList();
        List<Integer> numersUsed = new ArrayList<>();


        List<MemoramaEntity> items = getDataList();
        for (MemoramaEntity item: items) {

            boolean notFoundPosition =true;
            while (notFoundPosition){
                //valida si ya existe esa posocion guardad
                int aletarori = numberAleatory();

                if(!numersUsed.contains(aletarori)){
                    //add to list numbersUsed
                    //modifity entrity to list position
                    //exit to while loop
                    numersUsed.add(aletarori);

                    item.setPosition(aletarori);
                    memoramaEntities.set(aletarori,item);
                    notFoundPosition = false;
                }
            }
        }

        return memoramaEntities;
    }

    private List<MemoramaEntity> getDataList() {
        //Lista
        List<MemoramaEntity> memoramaEntities = new ArrayList<>();

        for(int i=0; i<2; i++) {
            memoramaEntities.add(addData("https://st2.depositphotos.com/5960950/8671/v/950/depositphotos_86717778-stock-illustration-abstract-colorful-geometric-triangles-rhombus.jpg", 1));

            memoramaEntities.add(addData("https://previews.123rf.com/images/ksena32/ksena321710/ksena32171000413/87933208-fondo-de-confeti-de-peque%C3%B1as-estrellas-de-colores.jpg", 2));

            memoramaEntities.add(addData("https://previews.123rf.com/images/pwg89/pwg891509/pwg89150900025/45306031-hojas-peque%C3%B1as.jpg", 3));

            memoramaEntities.add(addData("https://bolsas-de-organza.es/wp-content/uploads/2014/01/Bolsas-de-organza-peque%C3%B1as-tama%C3%B1o-10x15cm.jpg", 4));

            memoramaEntities.add(addData("http://maitebayona.com/wp-content/uploads/2017/04/Cosas-pequeU251cU2592as.jpg", 5));

            memoramaEntities.add(addData("http://www.habitosvitales.com/wp-content/uploads/2008/10/smallstuff.jpg", 6));

            memoramaEntities.add(addData("https://www.olimanitas.com/wp-content/uploads/2018/05/como-hacer-flores-de-papel-peque%C3%B1as-1024x683.jpg", 7));

            memoramaEntities.add(addData("https://coolties.es/wp-content/uploads/2018/11/PEQUE%C3%91AS-AMEBAS1.jpg", 8));
        }

        return memoramaEntities;
    }

    private MemoramaEntity addData(String url, int imageGroup){
        return new MemoramaEntity(url, imageGroup);
    }


    private int numberAleatory() {
        final int min = 0;
        final int max = 15;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

}
