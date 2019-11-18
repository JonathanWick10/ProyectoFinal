package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Contacts;

public class ContactsAdapter extends FirestoreRecyclerAdapter<Contacts, ContactsAdapter.ContactsViewHolder> {

    //region Variables
    ISelectionContact iSelectionContact;
    IDeleteContact iDeleteContact;
    IUpdateContact iUpdateContact;
    //endregion

    //region Build

    public ContactsAdapter(@NonNull FirestoreRecyclerOptions<Contacts> options, ISelectionContact iSelectionContact, IDeleteContact iDeleteContact,
                           IUpdateContact iUpdateContact) {
        super(options);
        this.iSelectionContact = iSelectionContact;
        this.iDeleteContact = iDeleteContact;
        this.iUpdateContact = iUpdateContact;
    }

    //endregion

    @Override
    protected void onBindViewHolder(@NonNull ContactsViewHolder holder, int position, @NonNull Contacts model) {
        //Get the position of the professional inside the adapter
        final DocumentSnapshot contactDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());

        //put the data in the views
        holder.setData(contactDocument.toObject(Contacts.class));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iSelectionContact.clickSelection(contactDocument.toObject(Contacts.class));
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Converts the document obtained from the adapter into a professional object
                iDeleteContact.clickDelete(contactDocument.toObject(Contacts.class));
            }
        });
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iUpdateContact.clickUpdate(contactDocument.toObject(Contacts.class));
            }
        });
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ContactsViewHolder(view);
    }

    //region ViewHolder Recycler Contacts
    public class ContactsViewHolder extends RecyclerView.ViewHolder{

        //Variables for views
        TextView txtFullName, txtNumber;
        ImageView imgDelete, imgUpdate;
        View layout;
        Contacts item;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtFullName = itemView.findViewById(R.id.txt_full_name);
            txtNumber = itemView.findViewById(R.id.txt_number_emergency);
            imgDelete = itemView.findViewById(R.id.img_contacts_delete);
            imgUpdate = itemView.findViewById(R.id.img_contacts_update);
        }

        //Set data to views
        public void setData(Contacts item) {
            this.item = item;
            txtFullName.setText(item.getFullNameContacts());
            txtNumber.setText(item.getNumberEContacts());
        }
    }
    //endregion

    //region Interface
    public interface ISelectionContact{
        void clickSelection(Contacts contacts);
    }

    public interface IDeleteContact{
        void clickDelete(Contacts contacts);
    }

    public interface IUpdateContact{
        void clickUpdate(Contacts contacts);
    }
    //endregion
}
