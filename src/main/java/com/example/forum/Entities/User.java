package com.example.forum.Entities;

import javax.persistence.Entity;

@Entity
public class User extends AbstractUser{
    
    private boolean isInformed;
    
    public User(){
        super();
        isInformed = false;
    }

    public User(String name){
        super(name);
        isInformed = false;
    }

    public User(String name, String password){
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
