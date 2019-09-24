package com.jonathan.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.database.CollectionCreation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_create)
    Button btnCreate;
    @BindView(R.id.list_collections)
    ListView listView;

    CollectionCreation collectionCreation = new CollectionCreation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //region Logic event OnClick
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        collectionCreation.createCollections(MainActivity.this));
                listView.setAdapter(adapter);
                btnCreate.setEnabled(true);
            }
        });
        //endregion

    }
}
