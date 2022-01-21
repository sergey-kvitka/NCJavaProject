package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.LinkValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkValueRepository extends CrudRepository<LinkValue, Long> {
    List<LinkValue> findAllByObjectId(Long id);
    void deleteByAttributeId(Long id);
    void deleteByObjectIdAndAttributeId(Long objectId, Long attributeId);
    LinkValue findByObjectIdAndAttributeId(Long objectId, Long attributeId);
}
