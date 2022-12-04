package com.example.forum.entities;

import javax.persistence.Entity;

@Entity
public class NormalUser extends AbstractUser{
    
    private boolean isInformed;
    
    public NormalUser(){
        super();
        isInformed = false;
    }

    public NormalUser(String name){
        super(name);
        isInformed = false;
    }

    public NormalUser(String name, String password){
        super(name, password);
        isInformed = false;
    }

    public boolean getInformStatus(){
        return isInformed;
    }

    public void getInformed(){
        isInformed = true;
    }

    public void infoChecked(){
        isInformed = false;
    }
}
