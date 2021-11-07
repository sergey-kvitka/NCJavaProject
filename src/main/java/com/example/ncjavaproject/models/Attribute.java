package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@CrossOrigin
public class Attribute {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "attribute_type_id")
    private Long attributeTypeId;

    @Column(nullable = false, name = "object_type_id")
    private Long objectTypeId;

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

//    public AttributeType.Type getAttributeType() {
//        AttributeType attributeType = DBComponent().attributeTypeRepository.findById(attributeTypeId).orElseThrow();
//        return AttributeType.Type.valueOf(attributeType.getName());
//    }
}
