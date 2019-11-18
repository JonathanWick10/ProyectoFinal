package com.jonathan.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Contacts;

import java.util.List;

public class ContactsEmergencyAdapter extends RecyclerView.Adapter<ContactsEmergencyAdapter.ContactsEmergencyViewHolder>{

    //region Variables
    List<Contacts> list;
    ContactsEmergencyAdapter.ISelectItemContactE iSelectItemContactE;
    //endregion

    //region Build

    public ContactsEmergencyAdapter(List<Contacts> list, ISelectItemContactE iSelectItemContactE) {
        this.list = list;
        this.iSelectItemContactE = iSelectItemContactE;
    }

    //endregion

    @NonNull
    @Override
    public ContactsEmergencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts_emergency, parent, false);
        return new ContactsEmergencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsEmergencyViewHolder holder, final int position) {
        //setData
        holder.setData(list.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectItemContactE.clickSelect(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //region ViewHolder Contacts Emergency
    public class ContactsEmergencyViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtNumber;
        View layout;
        Contacts item;

        public ContactsEmergencyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtName = itemView.findViewById(R.id.txt_full_name2);
            txtNumber = itemView.findViewById(R.id.txt_number_emergency2);
        }

        //Set data to views
        public void setData(Contacts item) {
            this.item = item;
            txtName.setText(item.getFullNameContacts());
            txtNumber.setText(item.getNumberEContacts());
        }
    }
    //endregion

    //region Interface
    public interface ISelectItemContactE{
        void clickSelect(Contacts contacts);
    }
    //endregion
}
