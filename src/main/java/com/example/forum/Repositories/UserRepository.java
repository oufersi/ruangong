package com.example.forum.Repositories;
import com.example.forum.Entities.NormalUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<NormalUser, Long>{
    public List<NormalUser> findByName(String name);
}
