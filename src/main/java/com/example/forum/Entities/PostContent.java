package com.example.forum.entities;

import javax.persistence.Entity;
@Entity
public class PostContent extends Content{

    public PostContent(){};

    public PostContent(String title, String content, AbstractUser user){
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

    public Long addSubPost(Long id){
        this.subIds.add(id);
        return id;
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
