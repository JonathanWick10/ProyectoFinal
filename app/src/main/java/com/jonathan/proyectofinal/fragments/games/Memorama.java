package com.jonathan.proyectofinal.fragments.games;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MemoramaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Memorama extends Fragment {

    //region imagesViews
    @BindView(R.id.item_uno)
    public ImageView itemUno;

    @BindView(R.id.item_dos)
    public ImageView itemDos;

    @BindView(R.id.item_tres)
    public ImageView itemTres;

    @BindView(R.id.item_cuatro)
    public ImageView itemCuatro;

    @BindView(R.id.item_cinco)
    public ImageView itemCinco;

    @BindView(R.id.item_seis)
    public ImageView itemSeis;

    @BindView(R.id.item_siete)
    public ImageView itemSiete;

    @BindView(R.id.item_ocho)
    public ImageView itemOcho;

    @BindView(R.id.item_nueve)
    public ImageView itemNueve;

    @BindView(R.id.item_diez)
    public ImageView itemDiez;

    @BindView(R.id.item_once)
    public ImageView itemOnce;

    @BindView(R.id.item_doce)
    public ImageView itemDoce;

    @BindView(R.id.item_trece)
    public ImageView itemTrese;

    @BindView(R.id.item_catorce)
    public ImageView itemCatorse;

    @BindView(R.id.item_quince)
    public ImageView itemQuinse;

    @BindView(R.id.item_dieciseis)
    public ImageView itemDieciceis;
    //endregion

    private List<ImageView> imageViews;
    private List<MemoramaEntity> listaComplete;
    private MemoramaEntity elemetSave;
    private boolean clickAllElemets = true;
    private Memoramai memoramai;

    public Memorama(Memoramai memoramai) {
        this.memoramai = memoramai;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflar vista
        View view = inflater.inflate(R.layout.fragment_memorama, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //metodos despues de cargar vista
        initGame();
    }

    private void initGame() {
        listaComplete = getDataListComplete();
        //lista de imagene views
        imageViews = new ArrayList<>();
        imageViews.add(itemUno);
        imageViews.add(itemDos);
        imageViews.add(itemTres);
        imageViews.add(itemCuatro);
        imageViews.add(itemCinco);
        imageViews.add(itemSeis);
        imageViews.add(itemSiete);
        imageViews.add(itemOcho);
        imageViews.add(itemNueve);
        imageViews.add(itemDiez);
        imageViews.add(itemOnce);
        imageViews.add(itemDoce);
        imageViews.add(itemTrese);
        imageViews.add(itemCatorse);
        imageViews.add(itemQuinse);
        imageViews.add(itemDieciceis);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //mostrar y ocultar imagenes
                    runOnUIThread(true);
                    Thread.sleep(5000);
                    runOnUIThread(false);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }
        }).start();
    }

    private void runOnUIThread(final boolean showAllImages) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                for (ImageView item : imageViews) {
                    //setar parametros
                    if (showAllImages) {
                        //mostrar carta
                        item.setImageResource(listaComplete.get(position).getImageId());
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //eso es para anular un onlick si ya tiene, porque si se le pasa null creo que no sirve
                            }
                        });
                    } else {
                        //ocultar carta
                        item.setImageResource(R.drawable.ic_logo_carta);
                        final int finalPosition = position;
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clickListener(finalPosition);
                            }
                        });
                    }
                    position++;
                }
            }
        });
    }

    private void clickListener(final int finalPosition) {
        if (clickAllElemets) {
            final MemoramaEntity lista = listaComplete.get(finalPosition);

            if (lista.isClick() && !lista.isFinded()) {
                //variables locales
                if (elemetSave == null) {
                    //es la primera ves
                    elemetSave = lista;
                    imageViews.get(finalPosition).setImageResource(lista.getImageId());
                } else {

                    if (elemetSave != lista) {

                        final MemoramaEntity listaSave = listaComplete.get(elemetSave.getPosition());
                        imageViews.get(finalPosition).setImageResource(lista.getImageId());

                        if (lista.getImgGroup() == elemetSave.getImgGroup()) {
                            //setear find del actual y antiguo
                            lista.setFinded(true);
                            listaSave.setFinded(true);

                            lista.setClick(false);
                            listaSave.setClick(false);

                            elemetSave = null;

                            //validar si ya estan todos completos
                            int cont = 0;
                            for (MemoramaEntity item : listaComplete) if (item.isFinded()) cont++;
                            if (cont >= listaComplete.size()) alertWin();

                        } else {
                            Toast.makeText(getContext(), "Fallo.", Toast.LENGTH_SHORT).show();
                            lista.setClick(false);
                            listaSave.setClick(false);
                            clickAllElemets = false;

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //change img
                                    imageViews.get(elemetSave.getPosition()).setImageResource(R.drawable.ic_logo_carta);
                                    imageViews.get(finalPosition).setImageResource(R.drawable.ic_logo_carta);

                                    lista.setClick(true);
                                    listaSave.setClick(true);

                                    clickAllElemets = true;
                                    elemetSave = null;
                                }
                            }, 2000);
                        }
                    }
                }
            }
        }
    }

    private List<MemoramaEntity> getDataListComplete() {
        //crear las listas
        List<MemoramaEntity> memoramaEntities = getDataList();
        List<Integer> numbersUsed = new ArrayList<>();

        //obtienen los 16 elementos y se reccorren
        List<MemoramaEntity> items = getDataList();
        for (MemoramaEntity item : items) {

            //busca una posicion no usada dentro de la lsita
            boolean notFoundPosition = true;
            while (notFoundPosition) {
                //valida si ya existe esa posocion guardada
                int aletarori = numberAleatory();

                if (!numbersUsed.contains(aletarori)) {
                    //agrega el elemento a la psoicion
                    numbersUsed.add(aletarori);

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
        List<MemoramaEntity> memoramaEntities = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            //crea los 8 lementos primarios
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_uno_icon, 1));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_dos_icon, 2));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_tres_icon, 3));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_cuatro_icon, 4));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_cinco_icon, 5));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_seis_icon, 6));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_siete_icon, 7));
            memoramaEntities.add(new MemoramaEntity(R.drawable.boy_ocho_icon, 8));
        }
        return memoramaEntities;
    }

    private int numberAleatory() {
        //funcion que retorna numero aleatorio
        final int min = 0;
        final int max = 15;
        return new Random().nextInt((max - min) + 1) + min;
    }

    private void alertWin() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View layoutInflater = getLayoutInflater().inflate(R.layout.memorama_win_plantilla, null);
        builder.setView(layoutInflater);
        Button btnOnback = layoutInflater.findViewById(R.id.mwmorama_winp_btnonback);
        final Button btnReload = layoutInflater.findViewById(R.id.mwmorama_winp_reload);

        AlertDialog dialog = builder.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;

        btnOnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                memoramai.callOnbackPressed();
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                memoramai.reloadGame();
            }
        });
    }

    public interface Memoramai {
        void reloadGame();

        void callOnbackPressed();
    }
}
