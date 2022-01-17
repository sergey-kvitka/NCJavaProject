package com.example.ncjavaproject.security.services;

import com.example.ncjavaproject.security.repositories.UserRepository;
import com.example.ncjavaproject.security.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
