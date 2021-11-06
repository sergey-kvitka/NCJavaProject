package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.ObjectType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjectTypeRepository extends CrudRepository<ObjectType, Long> {
    List<ObjectType> findAllByParentObjectTypeId(Long id);

}
