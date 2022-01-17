package com.example.ncjavaproject.repositories;

import com.example.ncjavaproject.entities.ObjectType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjectTypeRepository extends CrudRepository<ObjectType, Long> {
    List<ObjectType> findAllByParentObjectTypeId(Long id);
    boolean existsByName(String name);
    @Query(nativeQuery = true, value =
        "select id, name, parent_object_type_id from select_obj_type_with_children(:rootId)"
    )
    List<ObjectType> findObjectTypeWithParents(@Param("rootId") Long rootId);
}
