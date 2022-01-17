package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.ObjectDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ObjectRepository extends CrudRepository<ObjectDB, Long> {
    List<ObjectDB> findAllByParentObjectId(Long id);
    List<ObjectDB> findAllByObjectTypeId(Long id);
    boolean existsByName(String name);

}
