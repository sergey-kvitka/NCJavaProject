package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.entities.Attribute;
import com.example.ncjavaproject.services.AttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "attributes")
public class AttributeRestController {

    private final AttributeService service;

    public AttributeRestController(AttributeService service) {
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
    public Iterable<Attribute> getAttributesByObjectTypeId(@PathVariable String id){
        if ("null".equals(id)) return new ArrayList<>();
        try {
            return service.getAllAttributesIncludingParents(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("isAvailableAttribute/{name}/{objectTypeId}")
    public boolean isAvailableAttribute(@PathVariable String name, @PathVariable String objectTypeId) {
        if ("null".equals(objectTypeId)) return false;
        try {
            return validateAttribute(name, Long.parseLong(objectTypeId));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @GetMapping("validate_attribute/{name}/{objectTypeId}")
    public boolean validateAttribute(
            @PathVariable String name,
            @PathVariable Long objectTypeId)
    {
        return service.validate(name.trim(), objectTypeId);
    }
}
