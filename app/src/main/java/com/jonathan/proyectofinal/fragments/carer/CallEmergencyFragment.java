package com.jonathan.proyectofinal.fragments.carer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.ContactsAdapter;
import com.jonathan.proyectofinal.adapters.ContactsEmergencyAdapter;
import com.jonathan.proyectofinal.data.Contacts;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallEmergencyFragment extends Fragment {

    public CallEmergencyFragment() {
    }

    //region Variables
    //Views
    @BindView(R.id.edit_name_contact)
    TextInputEditText nameContact;
    @BindView(R.id.edit_number_contact)
    TextInputEditText numberContact;
    @BindView(R.id.button_create_contact)
    MaterialButton btnCreateContact;
    @BindView(R.id.number_emergency)
    MaterialButton btnNumberEmergency;
    @BindView(R.id.number_contacts)
    MaterialButton btnNumberContact;
    @BindView(R.id.recyclerView_emergency_numbers)
    RecyclerView rcEmergencyNumber;
    @BindView(R.id.recyclerView_contacts_numbers)
    RecyclerView rcContactsNumber;
    //Interface Adapter
    ContactsAdapter.ISelectionContact iSelectionContact;
    ContactsAdapter.IDeleteContact iDeleteContact;
    ContactsAdapter.IUpdateContact iUpdateContact;
    ContactsAdapter adapter;
    ContactsEmergencyAdapter.ISelectItemContactE iSelectItemContactE;
    ContactsEmergencyAdapter adapterEmergency;
    //Strings
    String name,number;
    //Instance POJO
    Contacts contacts = new Contacts();
    List<Contacts> contactsList = new ArrayList<>();
    //Firebase
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    //endregion

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_call,container,false);
        ButterKnife.bind(this, view);

        //Instances
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btnNumberEmergency.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black),null, null,null);
        btnNumberContact.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black),null, null,null);
        eventLogicSelectItem();
        eventLogicDeleteItem();
        eventLogicUpdateItem();
        eventLogicSelectItemEmergency();
        initRecyclerEmergency();
        initRecyclerContacts();
        return view;
    }

    //region StartListening
    @Override
    public void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    //endregion

    //region InitRecyclers
    private void initRecyclerContacts() {
        rcContactsNumber.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcContactsNumber.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Query creation
        Query query = db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                .orderBy("fullNameContacts");
        //Crea las opciones del FirestoreRecyclerOptions
        FirestoreRecyclerOptions<Contacts> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Contacts>()
                .setQuery(query, Contacts.class).build();

        //Passing parameters to the adapter
        adapter = new ContactsAdapter(firestoreRecyclerOptions,iSelectionContact,iDeleteContact, iUpdateContact);
        adapter.notifyDataSetChanged();
        rcContactsNumber.setAdapter(adapter);
    }


    private void fillRecycler() {
        contactsList.add(new Contacts("",getString(R.string.disaster_atentiont),"111"));
        contactsList.add(new Contacts("",getString(R.string.ambulance),"125"));
        contactsList.add(new Contacts("",getString(R.string.firefighters),"119"));
        contactsList.add(new Contacts("",getString(R.string.red_cross),"132"));
        contactsList.add(new Contacts("",getString(R.string.cai_national_police),"156"));
        contactsList.add(new Contacts("",getString(R.string.civil_defense),"144"));
        contactsList.add(new Contacts("",getString(R.string.police),"112"));
        contactsList.add(new Contacts("",getString(R.string.unique_emergency_number),"123"));
    }

    private void initRecyclerEmergency() {
        fillRecycler();
        rcEmergencyNumber.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterEmergency = new ContactsEmergencyAdapter(contactsList,iSelectItemContactE);
        adapterEmergency.notifyDataSetChanged();
        rcEmergencyNumber.setAdapter(adapterEmergency);
        rcEmergencyNumber.setHasFixedSize(true);
    }

    //endregion

    //region Events Click

    public void onClickLlamada(String phone) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+phone));
        startActivity(i);
    }

    private void eventLogicSelectItemEmergency() {
        iSelectItemContactE = new ContactsEmergencyAdapter.ISelectItemContactE() {
            @Override
            public void clickSelect(Contacts contacts) {
                onClickLlamada(contacts.getNumberEContacts());
            }
        };
    }

    private void eventLogicDeleteItem() {
        iDeleteContact = new ContactsAdapter.IDeleteContact() {
            @Override
            public void clickDelete(Contacts contacts) {
                alertDelete(contacts);
            }
        };
    }

    private void eventLogicSelectItem() {
        iSelectionContact = new ContactsAdapter.ISelectionContact() {
            @Override
            public void clickSelection(Contacts contacts) {
                onClickLlamada(contacts.getNumberEContacts());
            }
        };
    }

    private void eventLogicUpdateItem() {
        iUpdateContact = new ContactsAdapter.IUpdateContact() {
            @Override
            public void clickUpdate(Contacts contacts) {
                alertUpdate(contacts);
            }
        };
    }

    @OnClick({R.id.button_create_contact, R.id.number_emergency, R.id.number_contacts})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.button_create_contact:
                createContact();
                break;
            case R.id.number_emergency:
                if (rcEmergencyNumber.getVisibility() == v.VISIBLE){
                    rcEmergencyNumber.setVisibility(v.GONE);
                    btnNumberEmergency.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black),null, null,null);
                }else {
                    rcEmergencyNumber.setVisibility(v.VISIBLE);
                    btnNumberEmergency.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black),null, null,null);
                }
                break;
            case R.id.number_contacts:
                if (rcContactsNumber.getVisibility() == v.VISIBLE){
                    rcContactsNumber.setVisibility(v.GONE);
                    btnNumberContact.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black),null, null,null);
                }else {
                    rcContactsNumber.setVisibility(v.VISIBLE);
                    btnNumberContact.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black),null, null,null);
                }
                break;
        }
    }

    //endregion

    //region Metods

    public String createTransactionID(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    private void createContact() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Brainmher","Registrando en línea");
        final String uidGenerate = createTransactionID();
        name = nameContact.getText().toString();
        number = numberContact.getText().toString();
        if (!name.isEmpty() && !number.isEmpty()){
            contacts.setContactsUid(uidGenerate);
            contacts.setFullNameContacts(name);
            contacts.setNumberEContacts(number);
            contacts.setContactsUid(uidGenerate);
            db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        boolean flag = false;
                        for (QueryDocumentSnapshot documentSnapshot :
                                queryDocumentSnapshots) {
                            Contacts contactsFB = documentSnapshot.toObject(Contacts.class);
                            if (contactsFB.getFullNameContacts().equals(name)) {
                                Toast.makeText(getActivity(), "ya existe", Toast.LENGTH_SHORT).show();
                                flag = false;
                            } else {
                                flag = true;
                            }
                        }

                        if (flag) {
                            db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                                    .document(uidGenerate).set(contacts).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    nameContact.setText("");
                                    numberContact.setText("");
                                    rcContactsNumber.setVisibility(View.VISIBLE);
                                    btnNumberContact.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black),null, null,null);

                                    progressDialog.dismiss();
                                }
                            });
                        }else {
                            progressDialog.dismiss();
                        }
                    }else{
                        db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                                .document(uidGenerate).set(contacts).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                nameContact.setText("");
                                numberContact.setText("");
                                rcContactsNumber.setVisibility(View.VISIBLE);
                                btnNumberContact.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black),null, null,null);

                                progressDialog.dismiss();
                            }
                        });
                    }
                }
            });
        }else{
            Toast.makeText(getActivity(), getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteContact(Contacts contacts){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Brainmher","Eliminando en línea");
        db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                .document(contacts.getContactsUid()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        db.collection(Constants.Contacts).document(user.getUid()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
    }
    //endregion

    //region Alert Dialogs

    public void alertDelete(final Contacts contacts) {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BackgroundRounded);

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
                       deleteContact(contacts);
                        alertDialog.dismiss();
                    }
                });
                TextView tvInformation=dialogView.findViewById(R.id.textView);
                tvInformation.setText(getString(R.string.message_delete,contacts.getFullNameContacts()));
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
    }

    public void alertUpdate(final Contacts contacts){
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BackgroundRounded);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for Lollipop and newer versions
            try {
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.update_contact, null);
                builder.setView(dialogView);
                alertDialog=builder.create();

                final TextInputEditText editFullName = dialogView.findViewById(R.id.edit_name_contact_update);
                final TextInputEditText editNumber = dialogView.findViewById(R.id.edit_number_contact_update);

                editFullName.setText(contacts.getFullNameContacts());
                editNumber.setText(contacts.getNumberEContacts());

                Button btn1=(Button)dialogView.findViewById(R.id.btn_cancelar_contact);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btn2=(Button)dialogView.findViewById(R.id.btn_save_contact);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Brainmher","Actualizando en línea");

                        name = editFullName.getText().toString();
                        number = editNumber.getText().toString();
                        if (!name.isEmpty() && !number.isEmpty()){
                            contacts.setContactsUid(contacts.getContactsUid());
                            contacts.setFullNameContacts(name);
                            contacts.setNumberEContacts(number);
                            contacts.setContactsUid(contacts.getContactsUid());

                            db.collection(Constants.Contacts).document(user.getUid()).collection(Constants.Numbers)
                                    .document(contacts.getContactsUid()).set(contacts)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            progressDialog.dismiss();
                                        }
                                    });
                        }else{
                            Toast.makeText(getActivity(), getString(R.string.complete_field_please), Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                });
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
    }

    //endregion
}
