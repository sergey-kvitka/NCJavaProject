package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("value")
public class ValueController {

    @Autowired
    private ValueRepository valueRepository;

    @GetMapping
    public Iterable<Value> getAll() {
        return valueRepository.findAll();
    }

    @GetMapping("{id}")
    public Value get(@PathVariable String id) {
        return valueRepository
                .findById(Integer.parseInt(id))
                .orElseThrow(NotFoundException::new);
    }
    //
    @PostMapping
    public Value create(@RequestBody Value value) {
        return valueRepository.save(value);
    }

    @PutMapping("{id}")
    public Value update(@PathVariable String id, @RequestBody Value value) {
        value.setId(Integer.parseInt(id));
        get(id);
        return valueRepository.save(value);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        get(id);
        valueRepository.deleteById(Integer.parseInt(id));
    }

}
