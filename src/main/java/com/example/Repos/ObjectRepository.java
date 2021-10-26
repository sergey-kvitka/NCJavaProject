package com.example.Repos;

import com.example.Models.ObjectDB;
import org.springframework.data.repository.CrudRepository;

public interface ObjectRepository extends CrudRepository<ObjectDB, Integer> {

    ObjectDB findByName(String car);
}
