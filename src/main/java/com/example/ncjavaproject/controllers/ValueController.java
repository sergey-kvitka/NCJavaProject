package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("values")
public class ValueController {
    @Autowired
    ValueRepository valueRepository;

    @GetMapping
    public Iterable<Value> getValues() {
        return valueRepository.findAll();
    }

    @GetMapping("{id}")
    public Value get(@PathVariable Long id) {
        return valueRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Value create(@RequestBody Value value) {
        return valueRepository.save(new Value(
                value.getObjectId(),
                value.getAttributeId(),
                value.getValue() == null
                        ? null
                        : value.getValue(),
                value.getValue() == null
                        ? value.getDateValue()
                        : null
        ));
    }

    @PutMapping("{id}")
    public Value update(@PathVariable Long id, @RequestBody Value value) {
        value.setId(id); get(id);
        return valueRepository.save(value);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); valueRepository.deleteById(id);
    }
}
