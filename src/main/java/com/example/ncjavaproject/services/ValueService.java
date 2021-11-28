package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import org.springframework.stereotype.Service;

@Service
public class ValueService {

    private final ValueRepository valueRepository;

    public ValueService(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public Iterable<Value> getValues() {
        return valueRepository.findAll();
    }

    public Value getValue(Long id) {
        return (valueRepository.findById(id).orElse(null));
    }

    public void updateValue(Value value) {
        valueRepository.save(value);
    }

    public void deleteValue(Long id) {
        valueRepository.deleteById(id);
    }
}
