package com.contactmanager.validator;

import com.contactmanager.exception.InvalidInputException;

@FunctionalInterface
public interface Validator {

    boolean validate(String input) throws InvalidInputException;
}
