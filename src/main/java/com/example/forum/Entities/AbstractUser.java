package com.example.forum.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public abstract class AbstractUser {
    private @Id @GeneratedValue Long id;
    private String name;
    private String password;

    public AbstractUser(){};

    public AbstractUser(String name){
        this.name = name;
    }

    public AbstractUser(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}
