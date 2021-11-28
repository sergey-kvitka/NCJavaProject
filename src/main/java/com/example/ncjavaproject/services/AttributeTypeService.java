package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.AttributeType;
import com.example.ncjavaproject.repositories.AttributeTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class AttributeTypeService {

    private final AttributeTypeRepository attributeTypeRepository;

    public AttributeTypeService(AttributeTypeRepository validateAttribute) {
        this.attributeTypeRepository = validateAttribute;
    }

    public Iterable<AttributeType> getAttributeTypes() {
        return attributeTypeRepository.findAll();
    }

    public AttributeType getAttributeType(Long id) {
        return (attributeTypeRepository.findById(id).orElse(null));
    }

    public void updateAttributeType(AttributeType attributeType) {
        attributeTypeRepository.save(attributeType);
    }

    public void deleteAttributeType(Long id) {
        attributeTypeRepository.deleteById(id);
    }
}

