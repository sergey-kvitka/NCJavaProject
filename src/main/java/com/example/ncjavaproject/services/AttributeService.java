package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.*;
import com.example.ncjavaproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    AttributeRepository repository;

    public Iterable<Attribute> getAttributes() {
        return repository.findAll();
    }

    public Attribute getAttribute(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateAttribute(Attribute attribute, Long id) {
        attribute.setId(id);
        repository.save(attribute);
    }

    public void updateAttribute(Attribute attribute) {
        repository.save(attribute);
    }

    public void deleteAttribute(Long id) {
        repository.findById(id);
    }
}

