package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.Attribute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeRepository extends CrudRepository<Attribute, Long> {

    List<Attribute> findAllByObjectTypeId(Long objectTypeId);
    void deleteAllByObjectTypeId(Long id);
}
