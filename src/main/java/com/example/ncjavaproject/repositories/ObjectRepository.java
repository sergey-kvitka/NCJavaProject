package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.ObjectDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ObjectRepository extends CrudRepository<ObjectDB, Integer> {}
