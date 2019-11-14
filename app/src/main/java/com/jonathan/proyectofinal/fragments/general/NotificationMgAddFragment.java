package com.jonathan.proyectofinal.fragments.general;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Mensage;
import com.jonathan.proyectofinal.data.MensagesContent;
import com.jonathan.proyectofinal.tools.Constants;
import com.jonathan.proyectofinal.ui.NotificationMg;
import com.onesignal.OneSignal;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationMgAddFragment extends Fragment {

    //referencias
    @BindView(R.id.notification_titulo)
    public EditText titulo;
    @BindView(R.id.notification_content)
    public EditText content;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification_mg_add, container, false);
        ButterKnife.bind(this, view);

        NotificationMg.iniciarOneSignal(getContext());
        return view;
    }


    @OnClick(R.id.notification_save)
    public void clickSave(View view) {
        //get a los elementos
        final String tituloTxt = titulo.getText().toString();
        final String contentTxt = content.getText().toString();

        //validacion
        if (!tituloTxt.equals("") || !contentTxt.equals("")) {
            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                @Override
                public void idsAvailable(String userId, String registrationId) {
                    //esto es para capturar el ID unico de OneSignal, guardar en firebase
                    Toast.makeText(getContext(), "DATA: USER ID " + userId, Toast.LENGTH_LONG).show();

                    //ESTO ENVIARA AL DISPOSITIVO ACTUAL SOLAMENTE, YA QUE EL PLAYER ID ES EL MISMO DISPOSITIVO
                    createMensaje(tituloTxt, contentTxt, userId);
                }
            });
        }
    }

    private void createMensaje(final String title, final String mensaje, final String userId) {
        Gson gson = new Gson();

        //que segmento de usuarios va a enviar
        //NO SE PUEDEN AGREGAR SEGMENTOS SU HAY PLAYER ID, O SI SE USA EL
        //OneSignal.postNotification, PORQUE ESTE METODO NO SE ENVIA EL RESTKEY,
        //SI SE QUIERE ENVIAR A UN SEGMENTO SE DEBE USAR RETROFIT EL CUAL SE QUITO

        List<String> segments = new ArrayList<>();
        segments.add("All");

        //LISTA DE USUARIOS POR PLAYERID A LOS CUALES ENVIAR
        List<String> playerIds = new ArrayList<>();
        playerIds.add(userId);

        //contenido en idiomas
        MensagesContent heading = new MensagesContent();
        heading.setEs(title);
        heading.setEn(title);

        //cabecera en idiomas
        MensagesContent content = new MensagesContent();
        content.setEs(mensaje);
        content.setEn(mensaje);

        //unir toda la infomarcion en la entidad
        Mensage mensage = new Mensage();
        mensage.setApp_id(Constants.app_id);
        mensage.setTemplate_id(Constants.template_id);
        //mensage.setIncluded_segments(segments);
        mensage.setInclude_player_ids(playerIds);
        mensage.setContents(content);
        mensage.setHeadings(heading);

        Log.i("CODIGOS",gson.toJson(mensage));

        OneSignal.postNotification(gson.toJson(mensage), new OneSignal.PostNotificationResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                showMsm("Se creo el nuevo mensaje correctamente");
            }
            @Override
            public void onFailure(JSONObject response) {
                showMsm("Error"+response);
            }
        });
    }


    private void showMsm(final String msm) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //hilo de UI actual
                Toast.makeText(getContext(), "Respuesta: " + msm, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
