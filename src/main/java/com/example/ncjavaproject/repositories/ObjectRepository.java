package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.ObjectDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ObjectRepository extends CrudRepository<ObjectDB, Long> {

    String PRODUCT_ROOT_TYPE = "merchandise";

    List<ObjectDB> findAllByParentObjectId(Long id);
    List<ObjectDB> findAllByObjectTypeId(Long id);
    boolean existsByName(String name);

    @Query(nativeQuery = true, value =
            " select id, name, object_type_id, parent_object_id from object" +
            " where name_of_root_type(id) = '" + PRODUCT_ROOT_TYPE + "'")
    List<ObjectDB> findAllProducts();
}
