package com.contactmanager.service;


import com.contactmanager.model.Contact;

import java.util.LinkedHashSet;
import java.util.Set;

public class ContactDriver {

    private final Set<Contact> contacts;

    public ContactDriver(){
        this.contacts = new LinkedHashSet<>();
    }

    public boolean addContact(Contact c){
        return this.contacts.add(c);
    }

    public void deleteContact(Contact c){
        this.contacts.remove(c);
    }

       public Set<Contact> getContacts() {
        return contacts;
    }


}

