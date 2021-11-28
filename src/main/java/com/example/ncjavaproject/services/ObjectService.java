package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.repositories.ObjectRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ObjectService {

    private final ObjectRepository objectRepository;

    public ObjectService(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    public Iterable<ObjectDB> getObjects() {
        List<ObjectDB> objects = (List<ObjectDB>) objectRepository.findAll();
        objects.sort(Comparator.comparing(ObjectDB::getId));
        return objects;
    }

    public ObjectDB getObject(Long id) {
        return (objectRepository.findById(id).orElse(null));
    }

    public void updateObject(ObjectDB object) {
        objectRepository.save(object);
    }

    public void deleteObject(Long id) {
        objectRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return objectRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return objectRepository.existsByName(name);
    }

}
