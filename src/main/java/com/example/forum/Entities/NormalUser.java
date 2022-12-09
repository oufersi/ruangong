package com.example.forum.entities;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class NormalUser extends AbstractUser{
    
    private boolean isInformed;
    private ArrayList<Long> msgIds;
    
    public NormalUser(){
        super();
        isInformed = false;
        msgIds = new ArrayList<Long>();
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

    public void receiveMsg(Long id){
        msgIds.add(id);
    }
}
