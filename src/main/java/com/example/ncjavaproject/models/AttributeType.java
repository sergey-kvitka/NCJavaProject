package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "attribute_type")
public class AttributeType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public AttributeType(){
    }

    public AttributeType(@NotNull Type attributeTypeName) {
        name = attributeTypeName.name();
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

    public enum Type {
        TEXT, LINK
    }
}
