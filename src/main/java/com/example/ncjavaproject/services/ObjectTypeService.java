package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectTypeService {

    private final ObjectTypeRepository objectTypeRepository;
    private final AttributeService attributeService;

    public ObjectTypeService(
            ObjectTypeRepository objectTypeRepository,
            AttributeService attributeService)
    {
        this.objectTypeRepository = objectTypeRepository;
        this.attributeService = attributeService;
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

    public boolean validate(ObjectType objectType) {
        return ! objectTypeRepository.existsByName(objectType.getName());
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
        attributeService.getAttributesByObjectTypeId(rootObjectTypeId).forEach(
                attribute -> attributeService.deleteAttribute(attribute.getId())
        );

        objectTypeRepository.findAllByParentObjectTypeId(rootObjectTypeId)
        .forEach(
            objectType -> deleteAllWithRootId(objectType.getId())
        );
    }
}
