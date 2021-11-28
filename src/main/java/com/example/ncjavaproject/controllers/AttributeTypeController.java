package com.example.ncjavaproject.controllers;


import com.example.ncjavaproject.models.AttributeType;
import com.example.ncjavaproject.repositories.AttributeTypeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("attribute_types")
public class AttributeTypeController {

    private final AttributeTypeRepository attributeTypeRepository;

    public AttributeTypeController(AttributeTypeRepository attributeTypeRepository) {
        this.attributeTypeRepository = attributeTypeRepository;
    }

    @GetMapping
    public Iterable<AttributeType> getAttributeTypes() {
        return attributeTypeRepository.findAll();
    }

    @GetMapping("{id}")
    public AttributeType get(@PathVariable Long id) {
        return attributeTypeRepository
                .findById(id)
                .orElseThrow();
    }

    @PostMapping
    public AttributeType create(
            @RequestBody AttributeType attributeType) {
        return attributeTypeRepository.save(new AttributeType(
                AttributeType.Type.valueOf(attributeType.getName())
        ));
    }

    @PutMapping("{id}")
    public AttributeType update(@PathVariable Long id, @RequestBody AttributeType attributeType) {
        attributeType.setId(id); get(id);
        return attributeTypeRepository.save(attributeType);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); attributeTypeRepository.deleteById(id);
    }
}
