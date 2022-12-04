package com.example.forum.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forum.entities.NormalUser;

public interface NormalUserRepository extends JpaRepository<NormalUser, Long>{
    public Optional<NormalUser> findById(Long id);
    public List<NormalUser> findByName(String name);
}
