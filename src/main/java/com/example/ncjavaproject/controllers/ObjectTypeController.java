package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("object_types")
public class ObjectTypeController {
    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @GetMapping
    public Iterable<ObjectType> getObjectTypes() {
        return objectTypeRepository.findAll();
    }

    @GetMapping("{id}")
    public void get(@PathVariable Long id) {
        objectTypeRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void create(@RequestBody ObjectType objectType) {
        objectTypeRepository.save(new ObjectType(
                objectType.getName()
        ));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody ObjectType objectType) {
        objectType.setId(id); get(id);
        objectTypeRepository.save(objectType);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); objectTypeRepository.deleteById(id);
    }
}
