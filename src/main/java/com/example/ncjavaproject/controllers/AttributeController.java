package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.services.AttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("attributes")
public class AttributeController {

    private final AttributeService service;

    public AttributeController(AttributeService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Attribute> getAttributes() {
        return service.getAttributes();
    }

    @GetMapping("{id}")
    public Attribute get(@PathVariable Long id) {
        return service.getAttribute(id);
    }

    @PostMapping("/add_new")
    public void create(@RequestBody Attribute attribute) {
        service.updateAttribute(new Attribute(
                attribute.getName(),
                attribute.getAttributeTypeId(),
                attribute.getObjectTypeId()
        ));
    }

    @PutMapping("{id}/edit")
    public void update(@PathVariable Long id, @RequestBody Attribute attribute) {
        attribute.setId(id);
        attribute.setName(attribute.getName().trim());
        service.updateAttribute(attribute);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id) {
        service.deleteAttribute(id);
    }

    @GetMapping("getAttributesByObjectTypeId/{id}")
    public Iterable<Attribute> getAttributesByObjectTypeId(@PathVariable Long id) {
        return service.getAllAttributesIncludingParents(id);
    }

    @GetMapping("isAvailableAttribute/{name}/{objectTypeId}")
    public boolean isAvailableAttribute(@PathVariable String name, @PathVariable Long objectTypeId) {
        return validateAttribute(name, objectTypeId);
    }

    @GetMapping("validate_attribute/{name}/{objectTypeId}")
    public boolean validateAttribute(
            @PathVariable String name,
            @PathVariable Long objectTypeId)
    {
        return service.validate(name.trim(), objectTypeId);
    }
}
