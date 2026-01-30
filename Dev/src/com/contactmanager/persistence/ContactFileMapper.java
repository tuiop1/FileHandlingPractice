package com.contactmanager.persistence;

import com.contactmanager.model.Contact;
import com.contactmanager.model.Tag;

import java.util.LinkedHashSet;
import java.util.Set;

public final class ContactFileMapper {

    private ContactFileMapper() {
    }


    public static String contactToLine(Contact c) {
        String toReturn = String.format("%s|%s|%s|",  c.getTitle(), c.getPhoneNumber(), c.getEmail());
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (var tag : c.getSetOfTags()) {
            if (!first) sb.append(",");
            sb.append(tag.toString());
            first = false;
        }
        toReturn += sb;
        return toReturn;
    }

    public static Contact contactFromLine(String line) throws IllegalArgumentException {
        if (line.isBlank()) throw new IllegalArgumentException("There is an empty line!");
        String[] parts = line.split("\\|", -1);

               if (parts[0].isEmpty()) throw new IllegalArgumentException("No title was read");
        String title = parts[0];

        String phoneNumber;
        if (parts[1].isEmpty()) phoneNumber = "";
        else {
            phoneNumber = parts[1];
        }
        String email;
        if (parts[2].isEmpty()) email = "";
        else {
            email = parts[2];
        }
        if (parts[3].isEmpty()) throw new IllegalArgumentException("no tags were read");


        Set<Tag> tags = new LinkedHashSet<>();
        String[] tags_string =  parts[3].split(",");
        for(var tag : tags_string){
            tags.add(new Tag(tag));
        }

        return new Contact(title, phoneNumber, email, tags);

    }


}
