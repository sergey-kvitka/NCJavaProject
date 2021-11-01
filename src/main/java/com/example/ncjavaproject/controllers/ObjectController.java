package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("object")
public class ObjectController {

    @Autowired
    private ObjectRepository objectRepository;

    @GetMapping
    public Iterable<ObjectDB> getAll() {
        return objectRepository.findAll();
    }

    @GetMapping("{id}")
    public ObjectDB get(@PathVariable String id) {
        return objectRepository
                .findById(Integer.parseInt(id))
                .orElseThrow(NotFoundException::new);
    }
    //
    @PostMapping
    public ObjectDB create(@RequestBody ObjectDB object) {
        return objectRepository.save(object);
    }

    @PutMapping("{id}")
    public ObjectDB update(@PathVariable String id, @RequestBody ObjectDB object) {
        object.setId(Integer.parseInt(id));
        get(id);
        return objectRepository.save(object);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        get(id);
        objectRepository.deleteById(Integer.parseInt(id));
    }

}
