package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Value implements DBValue {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column
    private String value;

    @Column(name = "date_value")
    private Date dateValue;

    public Value(){}

    public Value(Long objectId, Long attributeId, String value, Date dateValue) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        if (value != null)
            this.value = value;
        else
            this.dateValue = dateValue;
    }

    public Value(@NotNull ObjectDB object, @NotNull Attribute attribute, String value) {
        objectId = object.getId();
        attributeId = attribute.getId();
        this.value = value;
    }

    public Value(@NotNull ObjectDB object, @NotNull Attribute attribute, @NotNull Date dateValue) {
        objectId = object.getId();
        attributeId = attribute.getId();
        this.dateValue = dateValue;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.TEXT;
    }
}
