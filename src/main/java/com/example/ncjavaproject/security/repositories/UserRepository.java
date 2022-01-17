package com.example.ncjavaproject.security.repositories;

import com.example.ncjavaproject.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
