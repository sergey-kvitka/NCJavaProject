package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.models.ObjectDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ObjectRepository extends CrudRepository<ObjectDB, Long> {
    List<ObjectDB> findAllByParentObjectId(Long id);
}
