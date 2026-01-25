package com.contactmanager.persistence;

import com.contactmanager.model.Contact;

public final class ContactFileMapper {

    private ContactFileMapper() {
    }




    public static String ContactToLine(Contact c) {
        String toReturn = String.format("%d|%s|%s|%s|", c.getUniqueId(), c.getTitle(), c.getPhoneNumber(), c.getEmail());
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (var tag : c.getSetOfTags()) {
            if (!first) sb.append(",");
            sb.append(tag.toString());
            first = false;
        }
       return toReturn+=sb;
    }

    public static Contact ContactFromLine(String line) throws RuntimeException{
       if(line.isEmpty()) throw new RuntimeException("There is an empty line!") ;
       String[] parts = line.split("\\|");
       if(parts[0].isEmpty()) throw new RuntimeException("The title is empty");
       String title = parts[0];


    }

}
