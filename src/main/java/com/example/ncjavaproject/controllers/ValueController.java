package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import com.example.ncjavaproject.services.AttributeService;
import com.example.ncjavaproject.services.LinkValueService;
import com.example.ncjavaproject.services.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("values")
public class ValueController {

    private final ValueService service;
    private final LinkValueService linkValueService;
    private final AttributeService attributeService;

    public ValueController(ValueService service, LinkValueService linkValueService, AttributeService attributeService) {
        this.service = service;
        this.attributeService = attributeService;
        this.linkValueService = linkValueService;
    }

    @GetMapping
    public Iterable<Value> getValues() {
        return service.getValues();
    }

    @GetMapping("{id}")
    public Value get(@PathVariable Long id) {
        return service.getValue(id);
    }

    @PostMapping("add_new")
    @Transactional
    public void create(@RequestBody Value value) {
        System.out.println(value);
        if ("".equals(value.getValue())) value.setValue(null);
        Value newValue = new Value(
                value.getObjectId(),
                value.getAttributeId(),
                value.getValue() == null
                        ? null
                        : value.getValue(),
                value.getValue() == null
                        ? value.getDateValue()
                        : null
        );
        System.out.println(newValue);
        service.deleteByObjectIdAndAttributeId(newValue.getObjectId(), newValue.getAttributeId());
        linkValueService.deleteByObjectIdAndAttributeId(newValue.getObjectId(), newValue.getAttributeId());
        attributeService.setAttributeType(1L, newValue.getAttributeId());
        service.updateValue(newValue);
    }

    @PutMapping("{id}/edit")
    public void update(@PathVariable Long id, @RequestBody Value value) {
        value.setId(id);
        service.updateValue(value);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id) {
        service.deleteValue(id);
    }

    @DeleteMapping("{id}/delete_by_attribute_id")
    public void deleteByAttributeId(@PathVariable Long id) {
        service.deleteByAttributeId(id);
    }

    @GetMapping("get_by_object_id/{id}")
    public List<Value> getValuesByObjectId(@PathVariable Long id) {
        return service.getValuesByObjectId(id);
    }
}
