package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueService {
    @Autowired
    ValueRepository repository;

    public Iterable<Value> getValues() {
        return repository.findAll();
    }

    public Value getValue(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateValue(Value value, Long id) {
        value.setId(id);
        repository.save(value);
    }

    public void updateValue(Value value) {
        repository.save(value);
    }

    public void deleteValue(Long id) {
        repository.findById(id);
    }
}
