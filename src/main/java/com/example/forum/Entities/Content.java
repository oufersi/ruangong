package com.example.forum.entities;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public ArrayList<Long> subIds;
    public String title;
    public String content;
    public String author;
    public Long authorId;

    protected Content(){
        subIds = new ArrayList<Long>();
        title = "Œﬁ±ÍÃ‚";
    }
}
