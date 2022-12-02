package com.example.forum.Exceptions;

public class PassageNotFoundException extends RuntimeException{
    public PassageNotFoundException(Long id){
        super("Could not found " + id);
    }
}
