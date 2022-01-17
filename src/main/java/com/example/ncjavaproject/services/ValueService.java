package com.example.ncjavaproject.services;

import com.example.ncjavaproject.entities.Value;
import com.example.ncjavaproject.repositories.ValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Value getValueByObjectIdAndAttributeId(Long objectId, Long attributeId) {
        return valueRepository.findByObjectIdAndAttributeId(objectId, attributeId);
    }

    public void updateValue(Value value) {
        valueRepository.save(value);
    }

    public void deleteValue(Long id) {
        valueRepository.deleteById(id);
    }

    public List<Value> getValuesByObjectId(Long id) {
        return valueRepository.findAllByObjectId(id);
    }

    public void deleteByAttributeId(Long attributeId) {
        valueRepository.deleteByAttributeId(attributeId);
    }

    public void deleteByObjectIdAndAttributeId( Long objectId, Long attributeId) {
        valueRepository.deleteByObjectIdAndAttributeId(objectId, attributeId);
    }
}
