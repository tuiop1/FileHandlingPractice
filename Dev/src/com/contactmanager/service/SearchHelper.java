package com.contactmanager.service;

import com.contactmanager.validator.InputValidator;
import com.contactmanager.exception.NotFoundException;
import com.contactmanager.model.Contact;

import java.util.Set;

public final class SearchHelper {
    public static Contact findContactByParameter(String parameter, Set<Contact> setOfContacts) throws NotFoundException {

        for (var c : setOfContacts) {
            if (c.getTitle().equals(parameter) || c.getPhoneNumber().equals(parameter)|| c.getEmail().equals(parameter)) {

                return c;
            }
        }


        throw new NotFoundException("Contact with title \"" + parameter + "\" was not found!");


    }

    public static Contact findContactByTitle(String title, Set<Contact> setOfContacts) throws NotFoundException {
        if (InputValidator.validateTitle(title)) {
            for (var v : setOfContacts) {
                if (v.getTitle().equals(title)) {
                    return v;
                }
            }

        }
        throw new NotFoundException("Sorry, contact was not found!");

    }

    public static Contact findContactByID(int id, Set<Contact> setOfContacts) throws NotFoundException {

        for (var v : setOfContacts) {
            if (v.getUniqueId() == (id)) {
                return v;
            }
        }


        throw new NotFoundException("Sorry, contact was not found!");

    }


}
