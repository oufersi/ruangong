package com.example.forum.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forum.entities.Administrater;

public interface AdministraterRepository extends JpaRepository<Administrater, Long>{
    public Optional<Administrater> findById(Long id);
}
