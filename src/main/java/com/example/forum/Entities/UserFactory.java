package com.example.forum.Entities;

public class UserFactory {
    public User createUser(){
        return new User();
    }

    public User createUser(String name){
        return new User(name);
    }

    public User createUser(String name, String password){
        return new User(name, password);
    }
}
