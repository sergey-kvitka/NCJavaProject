package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;

@RestController
@RequestMapping("objects")
public class ObjectController {
    @Autowired
    ObjectRepository objectRepository;

    @GetMapping
    public Iterable<ObjectDB> getObjects() {
        return objectRepository.findAll();
    }

    @GetMapping("{id}")
    public ObjectDB get(@PathVariable Long id) {
        return objectRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ObjectDB create(@RequestBody ObjectDB object) {
        return objectRepository.save(new ObjectDB(
                object.getName(),
                object.getObjectTypeId()
        ));
    }

    @PutMapping("{id}")
    public ObjectDB update(@PathVariable Long id, @RequestBody ObjectDB object) {
        object.setId(id); get(id);
        return objectRepository.save(object);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); objectRepository.deleteById(id);
    }
}
