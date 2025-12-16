package com.contactmanager;
import java.util.LinkedHashSet;
import java.util.Set;
import java.lang.StringBuilder;
public class Contact {
    private String title;
    private String phoneNumber;
    private String email;
    private Set<Tag> setOfTags;
    private int uniqueId;
    private static int idCounter = 0;

    Contact(String title, String phoneNumber, String email, Set<Tag> setOfTags   ){
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.setOfTags = setOfTags;
        ++idCounter;
        this.uniqueId = idCounter ;
    }
    public String setToFile(){
        StringBuilder sb = new StringBuilder();
        this.setOfTags.forEach(t ->sb.append(t).append(';') );
        return sb.toString();
    }

    @Override
    public String toString(){
        return String.format("%s\nID: %d\nPhone number: %s\nEmail: %s\nTags: %s",getTitle(), getUniqueId(),getPhoneNumber(),getEmail(),setToFile());

    }

    public String toFile(){
       return String.format("%s,%s,%s,%s",title,phoneNumber,email,setToFile());
    }

    public String getTitle() {
        return title;
    }
    public int getUniqueId(){
        return uniqueId;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSetOfTags(Set<Tag> setOfTags) {
        this.setOfTags = setOfTags;
    }
}
