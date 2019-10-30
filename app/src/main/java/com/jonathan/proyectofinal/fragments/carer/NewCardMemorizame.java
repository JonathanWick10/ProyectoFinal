package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.ui.HealthProfessionalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCardMemorizame extends Fragment {
    public NewCardMemorizame(){}

    Context context;


    @BindView(R.id.button_create_memorizame)
    MaterialButton btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.new_card_memorizame,container,false);
        ButterKnife.bind(this, view);
        context=container.getContext();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String option= "option";
                Alert(option);
            }
        });

        return view;
    }



    public void Alert(String option) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_one_textview_two_buttons, null);
                builder.setView(dialogView);
                alertDialog=builder.create();

                Button btn1=(Button)dialogView.findViewById(R.id.btn1);
                btn1.setText(R.string.no);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn2);
                btn2.setText(R.string.yes);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation=dialogView.findViewById(R.id.textView);
                tvInformation.setText(R.string.ask_dialog_memorizame);
                alertDialog.show();




            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        else{
            builder.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setCancelable(false);
            builder.show();

        }

        switch (option){
            case "eliminar": break;
        }

    }

}
