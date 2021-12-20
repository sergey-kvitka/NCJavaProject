package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValueRepository extends CrudRepository<Value, Long> {
    List<Value> findAllByObjectId(Long id);
    void deleteByAttributeId(Long id);
    void deleteByObjectIdAndAttributeId(Long objectId, Long attributeId);
}
