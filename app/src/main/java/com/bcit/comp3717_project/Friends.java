package com.bcit.comp3717_project;

public class Friends {
    public String name, email, id;


    public Friends(){
    }

    public Friends(String name, String email, String id){
        this.name = name;
        this.email = email;
        this.id = id;

    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
