package com.example.forum.Repositories;

import com.example.forum.Entities.Passage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageRepository extends JpaRepository<Passage, Long> {
    
}
