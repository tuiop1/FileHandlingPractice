package com.contactmanager.service;


import com.contactmanager.model.Contact;

import java.util.LinkedHashSet;
import java.util.Set;

public class ContactDriver {

    private final Set<Contact> contacts;

    public ContactDriver(){
        this.contacts = new LinkedHashSet<>();
    }

    public void addContact(Contact c){
        this.contacts.add(c);
    }

    public void deleteContact(Contact c){
        this.contacts.remove(c);
    }

    public String contactsToFile(){
       StringBuilder sb = new StringBuilder() ;
       this.contacts.forEach(contact -> sb.append(contact.getUniqueId()).append(contact.getTitle()).append("\n"));
       return sb.toString();
    }

    public Set<Contact> getContacts() {
        return contacts;
    }


}

