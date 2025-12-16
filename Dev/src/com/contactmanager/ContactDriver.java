package com.contactmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

public class ContactDriver {

    private Set<Contact> contacts;

    ContactDriver(){
        this.contacts = new LinkedHashSet<>();
    }

    public void addContact(Contact c){
        this.contacts.add(c);
    }

    public void deleteContact(Contact c){
        this.contacts.remove(c);
    }

    public String contactsToString(){
       StringBuilder sb = new StringBuilder() ;
       this.contacts.forEach(contact -> sb.append(contact.getUniqueId()).append(contact.getTitle()).append("\n"));
       return sb.toString();
    }

    public Set<Contact> getContacts() {
        return contacts;
    }


}

