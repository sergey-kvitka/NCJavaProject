package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.services.ObjectService;
import com.example.ncjavaproject.services.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("objects")
public class ObjectController {

    @Autowired
    private ObjectService service;
    @Autowired
    ObjectTypeService objectTypeService;

    @GetMapping
    public Iterable<ObjectDB> getObjects() {
        return service.getObjects();
    }

    @PostMapping("/add_new")
    public void addObject(@RequestBody ObjectDB object) {
        service.updateObject(new ObjectDB(
                object.getName(),
                object.getObjectTypeId(),
                object.getParentObjectId()
        ));
    }

    @PutMapping("{id}/edit")
    public void updateObject(@PathVariable("id") Long id,
                             @RequestBody ObjectDB object) {
        object.setId(id);
        service.updateObject(object);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable("id") Long id) {
        service.deleteObject(id);
    }

    @GetMapping("/isAvailableObject/{name}/{objectTypeId}/{parentObjectId}")
    public Iterable<String> isAvailableObject(@PathVariable("name") String name,
                                    @PathVariable("objectTypeId") String objectTypeIdStr,
                                    @PathVariable("parentObjectId") String parentObjectIdStr) {
        name = name.trim();
        if ("".equals(name)) return Collections.singleton("Name can't be empty string.");
        long objectTypeId;
        Long parentObjectId;

        try {
            if ("null".equals(parentObjectIdStr) || "".equals(parentObjectIdStr)) parentObjectId = null;
            else parentObjectId = Long.parseLong(parentObjectIdStr);
            objectTypeId = Long.parseLong(objectTypeIdStr);
        }
        catch (NumberFormatException e) {
            return singleton("Object type ID and parent object ID must be numbers.");
        }

        if (service.existsByName(name))
            return singleton("Object with such name already exists.");
        if (!objectTypeService.existsById(objectTypeId))
            return singleton("Object type with ID " + objectTypeId + " doesn't exist.");
        if (parentObjectId != null && !service.existsById(parentObjectId))
            return singleton("Parent object with ID " + parentObjectId + " doesn't exist.");

        return singleton("true");
    }

    private Iterable<String> singleton(String string) {
        return Collections.singleton(string);
    }
}
