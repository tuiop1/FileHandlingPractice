package com.contactmanager;

import com.contactmanager.customexceptions.InvalidInputException;

import java.util.Set;

public final class InputValidator {


    public static boolean validateTitle(String s) throws InvalidInputException {
        if (s.isEmpty() || s.trim().length() < 3) {

            throw new InvalidInputException("Invalid input of title!!!");
        }
        return true;
    }

    public static boolean validateEmail(String s) throws InvalidInputException {
        if (!s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new InvalidInputException("Invalid input of email!!!");
        }
        return true;
    }


    public static boolean validatePhoneNumber(String s) throws InvalidInputException {
        if (!s.matches("^\\+?\\d{7,15}$")) {

            throw new InvalidInputException("Invalid input of phone number!!!");
        }
        return true;
    }

    public static boolean validateTag(String s) throws InvalidInputException {
        if (!s.matches("^[A-Za-z]{3,}(\\s*,\\s*[A-Za-z]{3,})*$")) {

            throw new InvalidInputException("Invalid input of tags!!!");
        }
        return true;
    }


}
