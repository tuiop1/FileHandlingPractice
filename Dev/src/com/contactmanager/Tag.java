package com.contactmanager;

public class Tag {
    private String name;

   Tag(String name){
       this.name = name;
   }


   @Override
    public String toString(){
       return this.name;
   }
}
