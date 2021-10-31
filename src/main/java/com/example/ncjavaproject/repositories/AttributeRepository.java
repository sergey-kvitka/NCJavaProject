package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface AttributeRepository
        extends CrudRepository<Attribute, Integer> {
}
