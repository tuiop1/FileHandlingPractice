package com.contactmanager.model;

import java.util.Set;
import java.lang.StringBuilder;
public class Contact {
    private final String title;
    private String phoneNumber;
    private String email;
    private Set<Tag> setOfTags;



   public Contact(String title, String phoneNumber, String email, Set<Tag> setOfTags   ){
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.setOfTags = setOfTags;


    }
    public Contact(String title, Contact c){
       this.title = title;
        this.phoneNumber = c.getPhoneNumber();
        this.email = c.getEmail();
        this.setOfTags = c.getSetOfTags();

    }
    public String setToString(){
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (var tag : setOfTags){
            if(!first) sb.append(',');
            sb.append(tag);
            first = false;
        }
        return sb.toString();
    }

    @Override
    public String toString(){
        return String.format("%s\nPhone number: %s\nEmail: %s\nTags: %s",getTitle(), getPhoneNumber(),getEmail(),setToString());

    }



    public String getTitle() {
        return title;
    }

    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public Set<Tag> getSetOfTags() {
        return setOfTags;
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




    //used for sets, at first will be checked hashCode, if already exists, check the equals, not equal -> do not add element
    // always use equals() + hashCode()
    @Override
    public boolean equals(Object o){
       if(o==this)return true;
       //new feature, already contains casting to Contact if instanceof true
       if(!(o instanceof Contact c )) return false;

       return this.normalizedTitle().equals(c.normalizedTitle());


    }

    @Override
    public int hashCode(){
       return this.normalizedTitle().hashCode();
    }


    public String normalizedTitle(){
       return this.title.trim().toLowerCase();
    }

}
