package com.contactmanager.app;

import java.io.IOException;


public class Main {


    public static void main(String[] args) {



        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
           MenuDriver.getFileRepository().writeAllContacts(MenuDriver.getCd().getContacts());
                System.out.println("Contacts were successfully saved!");
            } catch (IOException e) {
                System.out.println("Unfortunately, saving contacts was not successful!");
            }


        }));

        MenuDriver.run();
    }
}
