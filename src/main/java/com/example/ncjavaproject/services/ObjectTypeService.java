package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.repositories.AttributeRepository;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import lombok.experimental.Wither;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObjectTypeService {

    private final ObjectTypeRepository objectTypeRepository;
    private final AttributeRepository attributeRepository;

    public ObjectTypeService(
            ObjectTypeRepository objectTypeRepository,
            AttributeRepository attributeRepository)
    {
        this.objectTypeRepository = objectTypeRepository;
        this.attributeRepository = attributeRepository;
    }

    public Iterable<ObjectType> getObjectTypes() {
        return objectTypeRepository.findAll();
    }

    public ObjectType getObjectType(Long id) {
        return (objectTypeRepository.findById(id).orElse(null));
    }

    public void updateObjectType(ObjectType objectType) {
        objectTypeRepository.save(objectType);
    }

    public void deleteObjectType(Long id) {
        objectTypeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return objectTypeRepository.existsById(id);
    }

    /**
     * Принимает ID объектного типа и возвращает его прямых потомков
     */
    public List<ObjectType> getObjectTypesByParentId(Long id) {
        return objectTypeRepository.findAllByParentObjectTypeId(id);
    }

    /**
     * Принимает ID объектного типа и возвращает этот объектный тип и всех
     * его потомков (в том числе и потомков потомков). Первым элементом
     * в возвращаемом листе будет объектный тип с тем ID, который был
     * передан в качестве параметра
     */
    public List<ObjectType> getObjectTypeAndAllChildren(Long objectTypeId) {
        List<ObjectType> objectTypes = getObjectTypesByParentId(objectTypeId);
        List<ObjectType> objectTypesChildren = new ArrayList<>();

        objectTypes.forEach(
            objectType -> objectTypesChildren.addAll(getObjectTypeAndAllChildren(objectType.getId()))
        );

        objectTypes.add(0, getObjectType(objectTypeId));
        objectTypes.addAll(objectTypesChildren);
        return objectTypes;
    }

    /**
     * Принимает ID объектного типа и удаляет всех прямых и непрямых потомков, все
     * их атрибуты и сам объектный тип с ID, переданным в качестве параметра
     */
    public void deleteAllWithRootId(Long rootObjectTypeId) {
        attributeRepository.deleteAllByObjectTypeId(rootObjectTypeId);

        objectTypeRepository.findAllByParentObjectTypeId(rootObjectTypeId)
        .forEach(
            objectType -> deleteAllWithRootId(objectType.getId())
        );

        objectTypeRepository.deleteById(rootObjectTypeId);
    }
}
