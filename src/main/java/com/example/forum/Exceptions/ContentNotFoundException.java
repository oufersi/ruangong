package com.example.forum.exceptions;

public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException(Long id){
        super("Could not found " + id);
    }
}
