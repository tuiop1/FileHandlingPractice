package com.contactmanager.model;

public class Tag {
    private final String name;

  public Tag(String name){
       this.name = name;
   }


   @Override
    public String toString(){
       return this.name;
   }
}
