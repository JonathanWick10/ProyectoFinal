package com.jonathan.proyectofinal.data;

public class Contacts {
    private String contactsUid;
    private String fullNameContacts;
    private String numberEContacts;

    public Contacts() {
    }

    public Contacts(String contactsUid, String fullNameContacts, String numberEContacts) {
        this.contactsUid = contactsUid;
        this.fullNameContacts = fullNameContacts;
        this.numberEContacts = numberEContacts;
    }

    public String getContactsUid() {
        return contactsUid;
    }

    public void setContactsUid(String contactsUid) {
        this.contactsUid = contactsUid;
    }

    public String getFullNameContacts() {
        return fullNameContacts;
    }

    public void setFullNameContacts(String fullNameContacts) {
        this.fullNameContacts = fullNameContacts;
    }

    public String getNumberEContacts() {
        return numberEContacts;
    }

    public void setNumberEContacts(String numberEContacts) {
        this.numberEContacts = numberEContacts;
    }
}
