package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.repositories.AttributeRepository;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final ObjectTypeRepository objectTypeRepository;

    public AttributeService(
            AttributeRepository attributeRepository,
            ObjectTypeRepository objectTypeRepository
    ) {
        this.attributeRepository = attributeRepository;
        this.objectTypeRepository = objectTypeRepository;
    }

    public Iterable<Attribute> getAttributes() {
        return attributeRepository.findAll();
    }

    public Attribute getAttribute(Long id) {
        return (attributeRepository.findById(id).orElse(null));
    }

    public void updateAttribute(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public void deleteAttribute(Long id) {
        attributeRepository.deleteById(id);
    }

    public void deleteAllByObjectTypeId(Long id) {
        attributeRepository.deleteAllByObjectTypeId(id);
    }

    /**
     * Принимает ID объектного типа и возвращает все его атрибуты, не включая атрибуты родителей
     */
    public List<Attribute> getAttributesByObjectTypeId(Long objectTypeId) {
        return attributeRepository.findAllByObjectTypeId(objectTypeId);
    }

    /**
     * Принимает ID объектного типа и возвращает все его атрибуты,
     * а также все атрибуты родителей этого объектного типа
     */
    public List<Attribute> getAllAttributesIncludingParents(Long objectTypeId) {
        List<Attribute> attributes = new ArrayList<>(getAttributesByObjectTypeId(objectTypeId));

        objectTypeRepository.findById(objectTypeId).ifPresent(
            objectType -> {
                Long parentObjectTypeId = objectType.getParentObjectTypeId();
                if  (parentObjectTypeId != null) {
                    objectTypeRepository.findById(parentObjectTypeId).ifPresent(parentObjectType ->
                        attributes.addAll(0,
                                getAllAttributesIncludingParents(parentObjectType.getId())
                        )
                    );
                }
            }
        ); return attributes;
    }

    /**
     * Принимает имя атрибута и ID объектного типа и проверяет, существует ли
     * атрибут с таким же именем среди атрибутов объектного типа и атрибутов
     * его родителей. Возвращает {@code true}, если такой атрибут можно занести
     * в базу данных с данным ID объектного типа
     */
    public boolean validate(String attributeName, Long objectTypeId) {
        if (!objectTypeRepository.existsById(objectTypeId)) return false;
        Attribute attribute = new Attribute(); attribute.setName(attributeName);
        return ! getAllAttributesIncludingParents(objectTypeId).contains(attribute);
    }

    @Deprecated
    public String isAvailableAttribute(String name, Long objectTypeId) {
        name = name.trim();
        List<Attribute> attributes = getAllAttributesIncludingParents(objectTypeId);
        for (Attribute attribute : attributes) {
            if (name.equals(attribute.getName())) {
                return "false";
            }
        }
        return "true";
    }



}

