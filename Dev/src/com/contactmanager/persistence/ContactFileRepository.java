package com.contactmanager.persistence;

import com.contactmanager.model.Contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashSet;
import java.util.Set;

public class ContactFileRepository {
    private final Path path;


    public ContactFileRepository(Path path) {
        this.path = path.resolve("contacts.txt");
    }


    public boolean initializeFileRepository() throws IOException {
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
            return true;
        }
        return false;

    }


    public Set<Contact> readAllContacts() throws IOException, IllegalArgumentException {
        Set<Contact> contacts = new LinkedHashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {


                contacts.add(ContactFileMapper.contactFromLine(line));
            }


        }


        return contacts;

    }


    public void writeContact(Contact c) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {

            writer.write(ContactFileMapper.contactToLine(c));
            writer.newLine();

        }

    }


    public void writeAllContacts(Set<Contact> contacts) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (var c : contacts) {
                writer.write(ContactFileMapper.contactToLine(c));
                writer.newLine();


            }


        }


    }


}
