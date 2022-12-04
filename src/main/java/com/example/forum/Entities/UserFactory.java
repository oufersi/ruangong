package com.example.forum.entities;

public class UserFactory {
    public NormalUser createUser(){
        return new NormalUser();
    }

    public NormalUser createUser(String name){
        return new NormalUser(name);
    }

    public NormalUser createUser(String name, String password){
        return new NormalUser(name, password);
    }
}
