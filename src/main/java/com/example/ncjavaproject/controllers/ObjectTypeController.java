package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("object_types")
public class ObjectTypeController {
    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @GetMapping
    public Iterable<ObjectType> getObjectTypes() {
        return objectTypeRepository.findAll();
    }

    @GetMapping("{id}")
    public ObjectType get(@PathVariable Long id) {
        return objectTypeRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ObjectType create(@RequestBody ObjectType objectType) {
        return objectTypeRepository.save(new ObjectType(
                objectType.getName()
        ));
    }

    @PutMapping("{id}")
    public ObjectType update(@PathVariable Long id, @RequestBody ObjectType objectType) {
        objectType.setId(id); get(id);
        return objectTypeRepository.save(objectType);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); objectTypeRepository.deleteById(id);
    }
}
