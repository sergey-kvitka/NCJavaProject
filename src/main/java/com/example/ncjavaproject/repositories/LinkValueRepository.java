package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.LinkValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkValueRepository extends CrudRepository<LinkValue, Long> {
    List<LinkValue> findAllByObjectId(Long id);
    void deleteByAttributeId(Long id);
    void deleteByObjectIdAndAttributeId(Long objectId, Long attributeId);
}
