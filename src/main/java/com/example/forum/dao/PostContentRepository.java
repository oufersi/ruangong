package com.example.forum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forum.entities.PostContent;

public interface PostContentRepository extends JpaRepository<PostContent, Long> {
    
}
