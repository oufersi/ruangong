package com.example.forum.dao;

import com.example.forum.entities.Message;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
    public Optional<Message> findById(Long id);
}
