package com.example.ncjavaproject.models;

import com.example.ncjavaproject.repositories.DBComponent;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Attribute {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "attribute_type_id")
    private Long attributeTypeId;

    @Column(nullable = false, name = "object_type_id")
    private Long objectTypeId;

    public Attribute(){}

    public Attribute(String name, @NotNull AttributeType attributeType, @NotNull ObjectType objectType) {
        this.name = name;
        attributeTypeId = attributeType.getId();
        objectTypeId = objectType.getId();
    }

    public Attribute(String name, Long attributeTypeId, Long objectTypeId) {
        this.name = name;
        this.attributeTypeId = attributeTypeId;
        this.objectTypeId = objectTypeId;
    }

    public Attribute(@NotNull LightAttribute lightAttribute, @NotNull ObjectType objectType) {
        objectTypeId = objectType.getId();
        this.name = lightAttribute.getName();
        attributeTypeId = lightAttribute.getAttributeTypeId();
    }

    public Attribute(@NotNull LightAttribute lightAttribute, @NotNull Long objectTypeId) {
        this.objectTypeId = objectTypeId;
        this.name = lightAttribute.getName();
        attributeTypeId = lightAttribute.getAttributeTypeId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttributeTypeId() {
        return attributeTypeId;
    }

    public void setAttributeTypeId(Long attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public DBComponent DBComponent() {
        return new DBComponent();
    }

//    public AttributeType.Type getAttributeType() {
//        AttributeType attributeType = DBComponent().attributeTypeRepository.findById(attributeTypeId).orElseThrow();
//        return AttributeType.Type.valueOf(attributeType.getName());
//    }
}
