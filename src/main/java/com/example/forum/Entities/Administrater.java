package com.example.forum.entities;

import javax.persistence.Entity;

@Entity
public class Administrater extends AbstractUser{
    public Administrater(){
        super();
    }

    public Administrater(String name){
        super(name);
    }

    public Administrater(String name, String password){
        super(name, password);
    }
}
