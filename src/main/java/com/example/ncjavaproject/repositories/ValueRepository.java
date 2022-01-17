package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.Value;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ValueRepository extends CrudRepository<Value, Long> {
    List<Value> findAllByObjectId(Long id);
    void deleteByAttributeId(Long id);
    void deleteByObjectIdAndAttributeId(Long objectId, Long attributeId);
    Value findByObjectIdAndAttributeId(Long objectId, Long attributeId);
}
