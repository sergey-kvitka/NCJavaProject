package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("attribute")
public class AttributeController {

    @Autowired
    private AttributeRepository attributeRepository;

    @GetMapping
    public Iterable<Attribute> getAll() {
        return attributeRepository.findAll();
    }

    @GetMapping("{id}")
    public Attribute get(@PathVariable String id) {
        return attributeRepository
                .findById(Integer.parseInt(id))
                .orElseThrow(NotFoundException::new);
    }
    //
    @PostMapping
    public Attribute create(@RequestBody Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @PutMapping("{id}")
    public Attribute update(@PathVariable String id, @RequestBody Attribute attribute) {
        attribute.setId(Integer.parseInt(id));
        get(id);
        return attributeRepository.save(attribute);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        get(id);
        attributeRepository.deleteById(Integer.parseInt(id));
    }

}
/*
Добавил рест контроллер и протестировал на нём несколько команд в браузерной консоли:

fetch('/attribute', {method: 'POST', headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({name: 'testAttr', "objectId": 2, "valueId": 3})})
    .then(r => console.log(r))

fetch('/attribute/34', {method: 'PUT', headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({"id": 100, "name": "testAttr(updated)", "objectId": 2, "valueId": 4 })})
    .then(r => console.log(r))


fetch('/attribute/34', {method: 'DELETE'}).then(r => console.log(r))
* */