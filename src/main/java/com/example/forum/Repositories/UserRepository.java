package com.example.forum.Repositories;
import com.example.forum.Entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    public List<User> findByName(String name);
}
