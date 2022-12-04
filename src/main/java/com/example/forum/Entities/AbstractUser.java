package com.example.forum.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public abstract class AbstractUser {
    protected @Id @GeneratedValue Long id;
    protected String name;
    protected String password;

    public AbstractUser(){};

    public AbstractUser(String name){
        this.name = name;
    }

    public AbstractUser(String name, String password){
        this.name = name;
        this.password = password;
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
