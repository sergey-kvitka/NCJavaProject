package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectService {
    @Autowired
    ObjectRepository repository;

    public Iterable<ObjectDB> getObjects() {
        return repository.findAll();
    }

    public ObjectDB getObject(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateObject(ObjectDB object, Long id) {
        object.setId(id);
        repository.save(object);
    }

    public void updateObject(ObjectDB object) {
        repository.save(object);
    }

    public void deleteObject(Long id) {
        repository.findById(id);
    }
}
