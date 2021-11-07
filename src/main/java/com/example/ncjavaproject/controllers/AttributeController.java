package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("attributes")
public class AttributeController {
    @Autowired
    AttributeRepository attributeRepository;

    @GetMapping
    public Iterable<Attribute> getAttributes() {
        return attributeRepository.findAll();
    }

    @GetMapping("{id}")
    public Attribute get(@PathVariable Long id) {
        return attributeRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Attribute create(@RequestBody Attribute attribute) {
        return attributeRepository.save(new Attribute(
                attribute.getName(),
                attribute.getAttributeTypeId(),
                attribute.getObjectTypeId()
        ));
    }

    @PutMapping("{id}")
    public Attribute update(@PathVariable Long id, @RequestBody Attribute attribute) {
        attribute.setId(id); get(id);
        return attributeRepository.save(attribute);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); attributeRepository.deleteById(id);
    }
}
