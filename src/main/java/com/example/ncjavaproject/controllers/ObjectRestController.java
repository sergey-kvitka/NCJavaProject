package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.entities.ObjectDB;
import com.example.ncjavaproject.services.ObjectService;
import com.example.ncjavaproject.services.ObjectTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("objects")
public class ObjectRestController {

    private final ObjectService service;
    private final ObjectTypeService objectTypeService;

    public ObjectRestController(ObjectService service, ObjectTypeService objectTypeService) {
        this.service = service;
        this.objectTypeService = objectTypeService;
    }

    @GetMapping
    public Iterable<ObjectDB> getObjects() {
        return service.getObjects();
    }

    @GetMapping("{id}")
    public ObjectDB getObject(@PathVariable("id") String id) {
        if ("null".equals(id)) return null;
        try {
            return service.getObject(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @PostMapping("/add_new")
    public void addObject(@RequestBody ObjectDB object) {
        service.updateObject(new ObjectDB(
                object.getName().trim(),
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
        if ("".equals(name)) return List.of("Name can't be empty string.");
        long objectTypeId;
        Long parentObjectId;

        try {
            if ("null".equals(parentObjectIdStr) || "".equals(parentObjectIdStr)) parentObjectId = null;
            else parentObjectId = Long.parseLong(parentObjectIdStr);
            objectTypeId = Long.parseLong(objectTypeIdStr);
        } catch (NumberFormatException e) {
            return List.of("Object type ID and parent object ID must be numbers.");
        }

        if (service.existsByName(name))
            return List.of("Object with such name already exists.");

        if (!objectTypeService.existsById(objectTypeId))
            return List.of("Object type with ID " + objectTypeId + " doesn't exist.");

        if (parentObjectId != null && !service.existsById(parentObjectId))
            return List.of("Parent object with ID " + parentObjectId + " doesn't exist.");

        return List.of("true");
    }

    @GetMapping("get_objects_by_object_type_id_with_children/{id}")
    public List<ObjectDB> getObjectsByObjectTypeIdWithChildren(@PathVariable("id") String objectTypeId) {
        if ("null".equals(objectTypeId)) return new ArrayList<>();
        try {
            return service.getObjectsByObjectTypeIdWithChildren(Long.parseLong(objectTypeId));
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }

}
