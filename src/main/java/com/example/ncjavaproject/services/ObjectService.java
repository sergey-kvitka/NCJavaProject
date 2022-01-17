package com.example.ncjavaproject.services;

import com.example.ncjavaproject.entities.ObjectDB;
import com.example.ncjavaproject.repositories.ObjectRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectService {

    private final ObjectTypeService objectTypeService;
    private final ObjectRepository objectRepository;

    public ObjectService(ObjectTypeService objectTypeService, ObjectRepository objectRepository) {
        this.objectTypeService = objectTypeService;
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

    public List<ObjectDB> getObjectsByObjectTypeId(Long objectTypeId) {
        return objectRepository.findAllByObjectTypeId(objectTypeId);
    }

    public List<ObjectDB> getObjectsByObjectTypeIdWithChildren(Long objectTypeId) {
        return objectTypeService.getObjectTypeAndAllChildren(objectTypeId)
                .stream()
                .map(objectType -> getObjectsByObjectTypeId(objectType.getId()))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
