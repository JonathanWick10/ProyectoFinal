package com.jonathan.proyectofinal.fragments.general;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.NotificationManagerAdapter;
import com.jonathan.proyectofinal.data.MensagesContent;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationMgReadFragment extends Fragment {

    //referencias
    @BindView(R.id.notification_recycler)
    public RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification_mg_read, container, false);

        //iniciar todos
        ButterKnife.bind(this,view);
        initRecycler();
        return view;
    }

    private void initRecycler(){
        //crear el adapter
        NotificationManagerAdapter adapter = new NotificationManagerAdapter(getDataFormServer());

        //propiedades del rv
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private List<MensagesContent> getDataFormServer(){
        //entitys
        MensagesContent entity1 = new MensagesContent();
        entity1.setEs("PRUEBA 1");
        MensagesContent entity2 = new MensagesContent();
        entity2.setEs("PRUEBA 2");
        MensagesContent entity3 = new MensagesContent();
        entity3.setEs("PRUEBA 3");
        MensagesContent entity4 = new MensagesContent();
        entity4.setEs("PRUEBA 4");

        //list to entitys
        List<MensagesContent> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);
        entities.add(entity4);

        //return list
        return entities;
    }
}
