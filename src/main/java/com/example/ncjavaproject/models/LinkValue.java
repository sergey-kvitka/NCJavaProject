package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class LinkValue implements DBValue {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column(nullable = false, name = "value_object_id")
    private Long valueObjectId;

    public LinkValue(){}

    public LinkValue(@NotNull Long objectId, @NotNull Long attributeId, @NotNull Long valueObjectId) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        this.valueObjectId = valueObjectId;
    }

    public LinkValue(@NotNull ObjectDB object, @NotNull Attribute attribute, @NotNull ObjectDB valueObject) {
        objectId = object.getId();
        attributeId = attribute.getId();
        valueObjectId = valueObject.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getValueObjectId() {
        return valueObjectId;
    }

    public void setValueObjectId(Long valueObjectId) {
        this.valueObjectId = valueObjectId;
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.LINK;
    }
}
