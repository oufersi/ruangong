package com.example.forum.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean isChecked;
    private Long senderId;

    public Message(){};

    public Message(String content, Long senderid){
        this.content = content;
        this.senderId = senderid;
        this.isChecked = false;
    }
    
    public Long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public boolean getIsChecked(){
        return isChecked;
    }

    public Long getSenderId(){
        return senderId;
    }
}
