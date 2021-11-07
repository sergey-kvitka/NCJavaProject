package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.AttributeType;
import com.example.ncjavaproject.repositories.AttributeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeTypeService {
    @Autowired
    AttributeTypeRepository repository;

    public Iterable<AttributeType> getAttributeTypes() {
        return repository.findAll();
    }

    public AttributeType getAttributeType(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateAttributeType(AttributeType attributeType, Long id) {
        attributeType.setId(id);
        repository.save(attributeType);
    }

    public void updateAttributeType(AttributeType attributeType) {
        repository.save(attributeType);
    }

    public void deleteAttributeType(Long id) {
        repository.findById(id);
    }
}
