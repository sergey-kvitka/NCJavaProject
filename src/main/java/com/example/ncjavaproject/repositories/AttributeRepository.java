package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.Attribute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeRepository extends CrudRepository<Attribute, Long> {

    List<Attribute> findAllByObjectTypeId(Long objectTypeId);


}
