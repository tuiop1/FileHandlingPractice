package com.contactmanager;

import com.contactmanager.customexceptions.InvalidInputException;

@FunctionalInterface
public interface Validator {

    boolean validate(String input) throws InvalidInputException;
}
