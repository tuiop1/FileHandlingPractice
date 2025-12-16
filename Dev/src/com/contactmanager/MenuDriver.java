package com.contactmanager;

import com.contactmanager.customexceptions.InvalidInputException;
import com.contactmanager.customexceptions.NotFoundException;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public final class MenuDriver {
    static ContactDriver cd = new ContactDriver();

    public static void run() {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("""
                        ----------------------------------
                                    CONTACT MANAGER
                        ----------------------------------
                        CHOOSE OPTION(1-5):
                        Add new contact      - 1
                        Edit a contact       - 2
                        Delete a contact     - 3
                        View all contacts    - 4
                        Search for a contact - 5
                        :""");

                int option;


                try {

                    option = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input!");
                    continue;
                }

                try {
                    MenuOption selected = MenuOption.fromInt(option);
                    switch (selected) {
                        case ADD -> addMenu(input);
                        case EDIT -> editMenu(input);
                        case DELETE -> deleteMenu(input);
                        case VIEW -> viewMenu(input);
                        case SEARCH -> searchMenu(input);

                    }
                } catch (InvalidInputException e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                    continue;
                }

            }
        }

    }


    public static void addMenu(Scanner input) {


        String title = MenuDriver.handleInput(input, "Enter title of new contact", InputValidator::validateTitle, true);
        String email = MenuDriver.handleInput(input, "Enter email of new contact", InputValidator::validateEmail, false);
        String phoneNumber = MenuDriver.handleInput(input, "Enter phone number of new contact", InputValidator::validatePhoneNumber, false);
        Set<Tag> setOfTags = MenuDriver.handleInputOfTags(input, "Enter tags for new contact in form(tag,tag,tag)", true);


        cd.addContact(new Contact(title, email, phoneNumber, setOfTags));
        System.out.printf("%s was successfully added!\n", title);
        EnterToContinue(input);


    }

    public static void editMenu(Scanner input) {

        while (true) {
            try {
                System.out.print("Enter the title/id of the contact you want to edit:");
                String value = input.nextLine().trim();
                if (value.isEmpty()) {
                    return;
                }
                Contact contactToEdit;
                try {
                    int ID = Integer.parseInt(value);
                    contactToEdit = SearchHelper.findContactByID(ID, cd.getContacts());

                } catch (NumberFormatException e) {
                    contactToEdit = SearchHelper.findContactByTitle(value, cd.getContacts());
                }
                String title = MenuDriver.handleInput(input, "Enter new title of the contact", InputValidator::validateTitle, false);
                String email = MenuDriver.handleInput(input, "Enter new email of the contact", InputValidator::validateEmail, false);
                String phoneNumber = MenuDriver.handleInput(input, "Enter new phone number of new contact", InputValidator::validatePhoneNumber, false);
                Set<Tag> setOfTags = MenuDriver.handleInputOfTags(input, "Enter new tags for new contact in form(tag,tag,tag)", false);

                if(!title.isEmpty()){
                    contactToEdit.setTitle(title);
                }
                if(!email.isEmpty()){
                    contactToEdit.setEmail(email);
                }

                if(!phoneNumber.isEmpty()){
                    contactToEdit.setPhoneNumber(phoneNumber);

                }

                if(!setOfTags.isEmpty()){
                    contactToEdit.setSetOfTags(setOfTags);
                }

                System.out.printf("%s was successfully edited!\n", contactToEdit.getTitle());

                EnterToContinue(input);
                return;

            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again or skip!");

            }
        }


    }

    public static void deleteMenu(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter the title/id of the contact you want to delete:");
                String value = input.nextLine().trim();
                if (value.isEmpty()) {
                    return;
                }

                Contact contactToDelete;
                try {
                    int ID = Integer.parseInt(value);
                    contactToDelete = SearchHelper.findContactByID(ID, cd.getContacts());

                } catch (NumberFormatException e) {
                    contactToDelete = SearchHelper.findContactByTitle(value, cd.getContacts());
                }

                String titleOfDeletedContact = contactToDelete.getTitle();
                cd.deleteContact(contactToDelete);
                System.out.printf("%s was successfully deleted!\n", titleOfDeletedContact);

                EnterToContinue(input);
                return;

            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again or skip!");

            }
        }


    }

    public static void viewMenu(Scanner input) {
        if (cd.getContacts().isEmpty()) {
            System.out.println("""
                    There are no contacts yet!
                    """);
            EnterToContinue(input);
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (var contact : cd.getContacts()) {
            sb.append(contact.toString()).append("\n----------------------------------\n");
        }

        System.out.println("""
                ----------------------------------
                           ALL CONTACTS
                ----------------------------------
                """);
        System.out.println(sb.toString());
        EnterToContinue(input);

    }

    public static void searchMenu(Scanner input) {
        while (true) {
            System.out.println("Enter the name/email/phone number of a contact(enter to skip)  ");
            System.out.print(":");
            String value = input.nextLine().trim();
            try {
                if (value.isEmpty()) {
                    break;
                }
                Contact searchedContact = SearchHelper.findContactByParameter(value, cd.getContacts());
                System.out.println("----------------------------------");
                System.out.println(searchedContact.toString());
                System.out.println("----------------------------------");
                EnterToContinue(input);
                break;

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());

            }

        }

    }


    private static void checkOption(int option) throws InvalidInputException {
        if (option > 5 || option < 0) {
            throw new InvalidInputException("Invalid input!");
        }
    }


    enum MenuOption {
        ADD(1),
        EDIT(2),
        DELETE(3),
        VIEW(4),
        SEARCH(5);

        private final int code;

        MenuOption(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static MenuOption fromInt(int code) {
            for (MenuOption option : MenuOption.values()) {
                if (option.getCode() == code) {
                    return option;
                }
            }

            throw new InvalidInputException("Invalid Input!");
        }

    }

    private static String handleInput(Scanner input, String message, Validator o, boolean inputRequired) {
        while (true) {
            System.out.println(message + ((!inputRequired) ? "(enter to skip)" : "") + ":");
            String value = input.nextLine().trim();
            if (!inputRequired) {
                if (value.isEmpty()) {
                    return "";
                }
            }

            try {
                if (o.validate(value)) return value;

            } catch (InvalidInputException e) {

                System.out.println(e.getMessage());
            }


        }
    }

    private static void EnterToContinue(Scanner input) {
        System.out.print("Enter to continue...");

        input.nextLine();


    }

    private static Set<Tag> handleInputOfTags(Scanner input, String message, boolean inputRequired) {
        Set<Tag> setOfTags = new LinkedHashSet<>();
        while (true) {
            System.out.println(message + ((!inputRequired) ? "(enter to skip)" : "") + ":");
            String value = input.nextLine().trim();
            if (!inputRequired) {
                if (value.isEmpty()) {
                    return setOfTags;
                }
            }
            try {
                InputValidator.validateTag(value);
                String[] parts = value.split(",");
                for (var part : parts) {
                    setOfTags.add(new Tag(part.trim()));

                }
                return setOfTags;
            } catch (InvalidInputException e) {

                System.out.println(e.getMessage());
            }


        }
    }

}






