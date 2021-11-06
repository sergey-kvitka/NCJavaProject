package com.example.ncjavaproject.models;

import com.example.ncjavaproject.repositories.DBComponent;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "object_type")
public class ObjectType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "parent_object_type_id")
    private Long parentObjectTypeId = null;

    public ObjectType(){}

    public ObjectType(String name) {
        this.name = name;
    }

    public ObjectType(@NotNull String name, @NotNull ObjectType parentObjectType) {
        this.name = name;
        parentObjectTypeId = parentObjectType.getId();
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

    public Long getParentObjectTypeId() {
        return parentObjectTypeId;
    }

    public void setParentObjectTypeId(Long parentObjectTypeId) {
        this.parentObjectTypeId = parentObjectTypeId;
    }

    public DBComponent DBComponent() {
        return new DBComponent();
    }

//    public List<Attribute> getAttributes() {
//        return DBComponent().attributeRepository.findAllByObjectTypeId(id);
//    }
//
//    public List<Attribute> getAllInheritedAttributes() {
//        List<Attribute> attributes = this.getAttributes();
//        DBComponent().objectTypeRepository.findById(parentObjectTypeId).ifPresent(
//                 parentObjectType -> attributes.addAll(parentObjectType.getAllInheritedAttributes())
//        );
//        return attributes;
//    }
//
//    public void addAttributes(List<Attribute> attributes, LightAttribute...lightAttributes) {
//        List<LightAttribute> currentAttributes = LightAttribute.attributesToLight(this.getAttributes());
//        List<LightAttribute> newAttributes = (attributes == null
//                ? new ArrayList<>()
//                : LightAttribute.attributesToLight(attributes));
//        if (lightAttributes.length != 0) {
//            newAttributes.addAll(Arrays.asList(lightAttributes));
//        }
//
//        List<ObjectType> children;
//
//        for (LightAttribute newLightAttribute : newAttributes) {
//            if (!currentAttributes.contains(newLightAttribute)) {
//                DBComponent().attributeRepository.save(new Attribute(newLightAttribute, this.id));
//                children = DBComponent().objectTypeRepository.findAllByParentObjectTypeId(this.id);
//                for (ObjectType child : children) {
//                    child.addAttributes(null, newLightAttribute);
//                }
//            }
//        }
//    }
//
//    public void addChildObjectType(String childObjectTypeName) {
//        ObjectType child = DBComponent().objectTypeRepository.save(
//                new ObjectType(childObjectTypeName, this));
//
//    }


 }
