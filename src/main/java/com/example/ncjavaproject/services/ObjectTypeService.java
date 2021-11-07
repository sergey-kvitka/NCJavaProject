package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectTypeService {
    @Autowired
    ObjectTypeRepository repository;

    public Iterable<ObjectType> getObjectTypes() {
        return repository.findAll();
    }

    public ObjectType getObjectType(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateObjectType(ObjectType objectType, Long id) {
        objectType.setId(id);
        repository.save(objectType);
    }

    public void updateObjectType(ObjectType objectType) {
        repository.save(objectType);
    }

    public void deleteObjectType(Long id) {
        repository.findById(id);
    }
}
