package com.example.forum.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passage {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private Long authorId;

    public Passage(){};

    public Passage(String title, String content, NormalUser user){
        this.title = title;
        this.content = content;
        this.author = user.getName();
        this.authorId = user.getId();
    }

    public Long setId(Long id){
        this.id = id;
        return id;
    }
    
    public String setTitle(String title){
        this.title = title;
        return title;
    }

    public String setContent(String content){
        this.content = content;
        return content;
    }

    public String setAuthor(String author){
        this.author = author;
        return author;
    }

    public Long setAuthorId(Long authorId){
        this.authorId = authorId;
        return authorId;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getAuthor(){
        return author;
    }

    public Long getAuthorId(){
        return authorId;
    }
}
