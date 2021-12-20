package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.services.ObjectTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("object_types")
public class ObjectTypeController {

    private final ObjectTypeService service;

    public ObjectTypeController(ObjectTypeService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<ObjectType> getObjectTypes() {
        return service.getObjectTypes();
    }

    @GetMapping("{id}")
    public ObjectType get(@PathVariable String id) {
        if ("null".equals(id)) return null;
        try {
            return service.getObjectType(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @GetMapping("/getChildrenByParentId/{id}")
    public Iterable<ObjectType> getObjectTypesByParentId(@PathVariable(value = "id") String id) {
        if ("null".equals(id)) return service.getObjectTypesByParentId(null);
        try {
            return service.getObjectTypesByParentId(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return List.of();
        }
    }

    @GetMapping("/getObjectTypeWithAllChildren/{id}")
    public List<ObjectType> getObjectTypeWithAllChildren(@PathVariable(value = "id") Long id) {
        List<ObjectType> objectTypes = service.getObjectTypeAndAllChildren(id);
        System.out.println(objectTypes);
        return objectTypes;
    }

    @PostMapping("/add_new")
    public void create(@RequestBody ObjectType objectType) {
        service.updateObjectType(new ObjectType(
                objectType.getName(),
                objectType.getParentObjectTypeId()
        ));
    }

    @PutMapping("{id}/edit")
    public void update(@PathVariable Long id, @RequestBody ObjectType objectType) {
        objectType.setId(id);
        service.updateObjectType(objectType);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id) {
        service.deleteObjectType(id);
    }

    @DeleteMapping("deleteAllWithRootId/{id}")
    public void deleteAllWithRootId(@PathVariable(value = "id") Long id) {
        service.deleteObjectType(id);
    }

    @GetMapping("validate_object_type/{name}")
    public boolean validate(@PathVariable String name) {
        ObjectType objectType = new ObjectType();
        objectType.setName(name.trim());
        return service.validate(objectType);
    }
}
